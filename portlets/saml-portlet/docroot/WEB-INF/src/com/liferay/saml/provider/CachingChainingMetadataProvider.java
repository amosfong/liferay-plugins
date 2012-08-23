/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.saml.provider;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.xml.namespace.QName;

import org.joda.time.DateTime;

import org.opensaml.saml2.common.Extensions;
import org.opensaml.saml2.metadata.EntitiesDescriptor;
import org.opensaml.saml2.metadata.EntityDescriptor;
import org.opensaml.saml2.metadata.RoleDescriptor;
import org.opensaml.saml2.metadata.provider.BaseMetadataProvider;
import org.opensaml.saml2.metadata.provider.MetadataProvider;
import org.opensaml.saml2.metadata.provider.MetadataProviderException;
import org.opensaml.xml.Namespace;
import org.opensaml.xml.NamespaceManager;
import org.opensaml.xml.XMLObject;
import org.opensaml.xml.schema.XSBooleanValue;
import org.opensaml.xml.signature.Signature;
import org.opensaml.xml.util.IDIndex;
import org.opensaml.xml.util.LazySet;
import org.opensaml.xml.validation.Validator;

import org.w3c.dom.Element;

/**
 * @author Mika Koivisto
 */
public class CachingChainingMetadataProvider extends BaseMetadataProvider {

	public void addMetadataProvider(MetadataProvider metadataProvider) {
		Lock lock = _readWriteLock.writeLock();

		lock.lock();

		try {
			if (metadataProvider != null) {
				metadataProvider.setRequireValidMetadata(
					requireValidMetadata());

				_metadataProviders.add(metadataProvider);
			}
		}
		finally {
			lock.unlock();
		}
	}

	public EntitiesDescriptor getEntitiesDescriptor(String name) {
		Lock lock = _readWriteLock.readLock();

		lock.lock();

		try {
			for (MetadataProvider metadataProvider : _metadataProviders) {
				try {
					EntitiesDescriptor entitiesDescriptor =
						metadataProvider.getEntitiesDescriptor(name);

					if (entitiesDescriptor != null) {
						return entitiesDescriptor;
					}
				}
				catch (MetadataProviderException mpe) {
					if (_log.isWarnEnabled()) {
						_log.warn(
							"Unable to retrieve metadata from provider " +
								metadataProvider.getClass(),
							mpe);
					}
				}
			}

			return null;
		}
		finally {
			lock.unlock();
		}
	}

	public EntityDescriptor getEntityDescriptor(String entityId) {
		Lock lock = _readWriteLock.readLock();

		lock.lock();

		try {
			MetadataProvider metadataProvider = _metadataProvidersMap.get(
				entityId);

			if (metadataProvider != null) {
				try {
					EntityDescriptor entityDescriptor =
						metadataProvider.getEntityDescriptor(entityId);

					if (entityDescriptor != null) {
						return entityDescriptor;
					}
				}
				catch (MetadataProviderException mpe) {
				}

				_metadataProvidersMap.remove(entityId);
			}

			for (int i = 0; i < _metadataProviders.size(); i++) {
				metadataProvider = _metadataProviders.get(i);

				try {
					EntityDescriptor entityDescriptor =
						metadataProvider.getEntityDescriptor(entityId);

					if (entityDescriptor != null) {
						_metadataProvidersMap.put(entityId, metadataProvider);

						return entityDescriptor;
					}
				}
				catch (MetadataProviderException mpe) {
					if (_log.isWarnEnabled()) {
						_log.warn(
							"Unable to retrieve metadata from provider " +
								metadataProvider.getClass(),
							mpe);
					}
				}
			}

			return null;
		}
		finally {
			lock.unlock();
		}
	}

	public XMLObject getMetadata() {
		return new ChainingEntitiesDescriptor();
	}

	public List<MetadataProvider> getProviders() {
		return Collections.unmodifiableList(_metadataProviders);
	}

	public List<RoleDescriptor> getRole(String entityId, QName qName) {
		Lock lock = _readWriteLock.readLock();

		lock.lock();

		try {
			MetadataProvider metadataProvider = _metadataProvidersMap.get(
				entityId);

			if (metadataProvider != null) {
				try {
					List<RoleDescriptor> roleDescriptors =
						metadataProvider.getRole(entityId, qName);

					if (roleDescriptors != null) {
						return roleDescriptors;
					}
				}
				catch (MetadataProviderException mpe) {
				}

				_metadataProvidersMap.remove(entityId);
			}

			for (int i = 0; i < _metadataProviders.size(); i++) {
				metadataProvider = _metadataProviders.get(i);

				try {
					List<RoleDescriptor>roleDescriptors =
						metadataProvider.getRole(entityId, qName);

					if ((roleDescriptors != null) &&
						!roleDescriptors.isEmpty()) {

						_metadataProvidersMap.put(entityId, metadataProvider);

						return roleDescriptors;
					}
				}
				catch (MetadataProviderException mpe) {
					if (_log.isWarnEnabled()) {
						_log.warn(
							"Unable to retrieve metadata from provider " +
								metadataProvider.getClass(),
							mpe);
					}
				}
			}

			return null;
		}
		finally {
			lock.unlock();
		}
	}

	public RoleDescriptor getRole(
		String entityId, QName qName, String supportedProtocol) {

		Lock lock = _readWriteLock.readLock();

		lock.lock();

		try {
			MetadataProvider metadataProvider = _metadataProvidersMap.get(
				entityId);

			if (metadataProvider != null) {
				try {
					RoleDescriptor roleDescriptor = metadataProvider.getRole(
						entityId, qName, supportedProtocol);

					if (roleDescriptor != null) {
						return roleDescriptor;
					}
				}
				catch (MetadataProviderException mpe) {
				}

				_metadataProvidersMap.remove(entityId);
			}

			for (int i = 0; i < _metadataProviders.size(); i++) {
				metadataProvider = _metadataProviders.get(i);

				try {
					RoleDescriptor roleDescriptor = metadataProvider.getRole(
						entityId, qName, supportedProtocol);

					if (roleDescriptor != null) {
						_metadataProvidersMap.put(entityId, metadataProvider);

						return roleDescriptor;
					}
				}
				catch (MetadataProviderException mpe) {
					if (_log.isWarnEnabled()) {
						_log.warn(
							"Unable to retrieve metadata from provider " +
								metadataProvider.getClass(),
							mpe);
					}
				}
			}

			return null;
		}
		finally {
			lock.unlock();
		}
	}

	public void removeMetadataProvider(MetadataProvider metadataProvider) {
		Lock lock = _readWriteLock.writeLock();

		lock.lock();

		try {
			_metadataProvidersMap.clear();

			_metadataProviders.remove(metadataProvider);
		}
		finally {
			lock.unlock();
		}
	}

	@Override
	public void setRequireValidMetadata(boolean requireValidMetadata) {
		super.setRequireValidMetadata(requireValidMetadata);

		Lock lock = _readWriteLock.writeLock();

		lock.lock();

		try {
			for (MetadataProvider metadataProvider : _metadataProviders) {
				metadataProvider.setRequireValidMetadata(requireValidMetadata);
			}
		}
		finally {
			lock.unlock();
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		CachingChainingMetadataProvider.class);

	private List<MetadataProvider> _metadataProviders =
		new CopyOnWriteArrayList<MetadataProvider>();
	private Map<String, MetadataProvider> _metadataProvidersMap =
		new ConcurrentHashMap<String, MetadataProvider>();
	private ReadWriteLock _readWriteLock = new ReentrantReadWriteLock(true);

	private class ChainingEntitiesDescriptor implements EntitiesDescriptor {

		public ChainingEntitiesDescriptor() {
			_xmlObjects = new ArrayList<XMLObject>();

			Lock lock = _readWriteLock.readLock();

			lock.lock();

			try {
				for (MetadataProvider metadataProvider : _metadataProviders) {
					_xmlObjects.add(metadataProvider.getMetadata());
				}
			}
			catch (MetadataProviderException mpe) {
				_log.error(
					"Unable to get metadata from metadata provider", mpe);
			}
			finally {
				lock.unlock();
			}
		}

		public void addNamespace(Namespace namespace) {
		}

		@SuppressWarnings("rawtypes")
		public void deregisterValidator(Validator validator) {
		}

		public void detach() {
		}

		public Long getCacheDuration() {
			return null;
		}

		public Element getDOM() {
			return null;
		}

		public QName getElementQName() {
			return EntitiesDescriptor.DEFAULT_ELEMENT_NAME;
		}

		public List<EntitiesDescriptor> getEntitiesDescriptors() {
			List<EntitiesDescriptor> entitiesDescriptors =
				new ArrayList<EntitiesDescriptor>();

			for (XMLObject xmlObject : _xmlObjects) {
				if (xmlObject instanceof EntitiesDescriptor) {
					EntitiesDescriptor entitiesDescriptor =
						(EntitiesDescriptor)xmlObject;

					entitiesDescriptors.add(entitiesDescriptor);
				}
			}

			return entitiesDescriptors;
		}

		public List<EntityDescriptor> getEntityDescriptors() {
			List<EntityDescriptor> entityDescriptors =
				new ArrayList<EntityDescriptor>();

			for (XMLObject xmlObject : _xmlObjects) {
				if (xmlObject instanceof EntityDescriptor) {
					EntityDescriptor entityDescriptor =
						(EntityDescriptor)xmlObject;

					entityDescriptors.add(entityDescriptor);
				}
			}

			return entityDescriptors;
		}

		public Extensions getExtensions() {
			return null;
		}

		public String getID() {
			return null;
		}

		public IDIndex getIDIndex() {
			return null;
		}

		public String getName() {
			return null;
		}

		public NamespaceManager getNamespaceManager() {
			return null;
		}

		public Set<Namespace> getNamespaces() {
			return new LazySet<Namespace>();
		}

		public String getNoNamespaceSchemaLocation() {
			return null;
		}

		public List<XMLObject> getOrderedChildren() {
			List<XMLObject> xmlObjects = new ArrayList<XMLObject>();

			try {
				for (MetadataProvider metadataProvider : _metadataProviders) {
					XMLObject xmlObject = metadataProvider.getMetadata();

					xmlObjects.add(xmlObject);
				}
			}
			catch (MetadataProviderException mpe) {
				_log.error(
					"Unable to build a list of metadata descriptors", mpe);
			}

			return xmlObjects;
		}

		public XMLObject getParent() {
			return null;
		}

		public String getSchemaLocation() {
			return null;
		}

		public QName getSchemaType() {
			return EntitiesDescriptor.TYPE_NAME;
		}

		public Signature getSignature() {
			return null;
		}

		public String getSignatureReferenceID() {
			return null;
		}

		@SuppressWarnings("rawtypes")
		public List<Validator> getValidators() {
			return new ArrayList<Validator>();
		}

		public DateTime getValidUntil() {
			return null;
		}

		public boolean hasChildren() {
			List<XMLObject> xmlObjects = getOrderedChildren();

			return !xmlObjects.isEmpty();
		}

		public boolean hasParent() {
			return false;
		}

		public Boolean isNil() {
			return Boolean.FALSE;
		}

		public XSBooleanValue isNilXSBoolean() {
			return new XSBooleanValue(Boolean.FALSE, false);
		}

		public boolean isSigned() {
			return false;
		}

		public boolean isValid() {
			return true;
		}

		@SuppressWarnings("rawtypes")
		public void registerValidator(Validator validator) {
		}

		public void releaseChildrenDOM(boolean propagateRelease) {
		}

		public void releaseDOM() {
		}

		public void releaseParentDOM(boolean propagateRelease) {
		}

		public void removeNamespace(Namespace namespace) {
		}

		public XMLObject resolveID(String id) {
			return null;
		}

		public XMLObject resolveIDFromRoot(String id) {
			return null;
		}

		public void setCacheDuration(Long duration) {
		}

		public void setDOM(Element element) {
		}

		public void setExtensions(Extensions extensions) {
		}

		public void setID(String id) {
		}

		public void setName(String name) {
		}

		public void setNil(Boolean value) {
		}

		public void setNil(XSBooleanValue value) {
		}

		public void setNoNamespaceSchemaLocation(String location) {
		}

		public void setParent(XMLObject xmlObject) {
		}

		public void setSchemaLocation(String location) {
		}

		public void setSignature(Signature signature) {
		}

		public void setValidUntil(DateTime validUntil) {
		}

		public void validate(boolean validateDescendants) {
		}

		private List<XMLObject> _xmlObjects;

	}

}