/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.saml.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link SamlIdpSpConnection}.
 * </p>
 *
 * @author Mika Koivisto
 * @see SamlIdpSpConnection
 * @generated
 */
public class SamlIdpSpConnectionWrapper implements SamlIdpSpConnection,
	ModelWrapper<SamlIdpSpConnection> {
	public SamlIdpSpConnectionWrapper(SamlIdpSpConnection samlIdpSpConnection) {
		_samlIdpSpConnection = samlIdpSpConnection;
	}

	@Override
	public Class<?> getModelClass() {
		return SamlIdpSpConnection.class;
	}

	@Override
	public String getModelClassName() {
		return SamlIdpSpConnection.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("samlIdpSpConnectionId", getSamlIdpSpConnectionId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("samlSpEntityId", getSamlSpEntityId());
		attributes.put("assertionLifetime", getAssertionLifetime());
		attributes.put("attributeNames", getAttributeNames());
		attributes.put("attributesEnabled", getAttributesEnabled());
		attributes.put("attributesNamespaceEnabled",
			getAttributesNamespaceEnabled());
		attributes.put("enabled", getEnabled());
		attributes.put("metadataUrl", getMetadataUrl());
		attributes.put("metadataXml", getMetadataXml());
		attributes.put("metadataUpdatedDate", getMetadataUpdatedDate());
		attributes.put("name", getName());
		attributes.put("nameIdAttribute", getNameIdAttribute());
		attributes.put("nameIdFormat", getNameIdFormat());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long samlIdpSpConnectionId = (Long)attributes.get(
				"samlIdpSpConnectionId");

		if (samlIdpSpConnectionId != null) {
			setSamlIdpSpConnectionId(samlIdpSpConnectionId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String samlSpEntityId = (String)attributes.get("samlSpEntityId");

		if (samlSpEntityId != null) {
			setSamlSpEntityId(samlSpEntityId);
		}

		Integer assertionLifetime = (Integer)attributes.get("assertionLifetime");

		if (assertionLifetime != null) {
			setAssertionLifetime(assertionLifetime);
		}

		String attributeNames = (String)attributes.get("attributeNames");

		if (attributeNames != null) {
			setAttributeNames(attributeNames);
		}

		Boolean attributesEnabled = (Boolean)attributes.get("attributesEnabled");

		if (attributesEnabled != null) {
			setAttributesEnabled(attributesEnabled);
		}

		Boolean attributesNamespaceEnabled = (Boolean)attributes.get(
				"attributesNamespaceEnabled");

		if (attributesNamespaceEnabled != null) {
			setAttributesNamespaceEnabled(attributesNamespaceEnabled);
		}

		Boolean enabled = (Boolean)attributes.get("enabled");

		if (enabled != null) {
			setEnabled(enabled);
		}

		String metadataUrl = (String)attributes.get("metadataUrl");

		if (metadataUrl != null) {
			setMetadataUrl(metadataUrl);
		}

		String metadataXml = (String)attributes.get("metadataXml");

		if (metadataXml != null) {
			setMetadataXml(metadataXml);
		}

		Date metadataUpdatedDate = (Date)attributes.get("metadataUpdatedDate");

		if (metadataUpdatedDate != null) {
			setMetadataUpdatedDate(metadataUpdatedDate);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String nameIdAttribute = (String)attributes.get("nameIdAttribute");

		if (nameIdAttribute != null) {
			setNameIdAttribute(nameIdAttribute);
		}

		String nameIdFormat = (String)attributes.get("nameIdFormat");

		if (nameIdFormat != null) {
			setNameIdFormat(nameIdFormat);
		}
	}

	/**
	* Returns the primary key of this saml idp sp connection.
	*
	* @return the primary key of this saml idp sp connection
	*/
	@Override
	public long getPrimaryKey() {
		return _samlIdpSpConnection.getPrimaryKey();
	}

	/**
	* Sets the primary key of this saml idp sp connection.
	*
	* @param primaryKey the primary key of this saml idp sp connection
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_samlIdpSpConnection.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the saml idp sp connection ID of this saml idp sp connection.
	*
	* @return the saml idp sp connection ID of this saml idp sp connection
	*/
	@Override
	public long getSamlIdpSpConnectionId() {
		return _samlIdpSpConnection.getSamlIdpSpConnectionId();
	}

	/**
	* Sets the saml idp sp connection ID of this saml idp sp connection.
	*
	* @param samlIdpSpConnectionId the saml idp sp connection ID of this saml idp sp connection
	*/
	@Override
	public void setSamlIdpSpConnectionId(long samlIdpSpConnectionId) {
		_samlIdpSpConnection.setSamlIdpSpConnectionId(samlIdpSpConnectionId);
	}

	/**
	* Returns the company ID of this saml idp sp connection.
	*
	* @return the company ID of this saml idp sp connection
	*/
	@Override
	public long getCompanyId() {
		return _samlIdpSpConnection.getCompanyId();
	}

	/**
	* Sets the company ID of this saml idp sp connection.
	*
	* @param companyId the company ID of this saml idp sp connection
	*/
	@Override
	public void setCompanyId(long companyId) {
		_samlIdpSpConnection.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this saml idp sp connection.
	*
	* @return the user ID of this saml idp sp connection
	*/
	@Override
	public long getUserId() {
		return _samlIdpSpConnection.getUserId();
	}

	/**
	* Sets the user ID of this saml idp sp connection.
	*
	* @param userId the user ID of this saml idp sp connection
	*/
	@Override
	public void setUserId(long userId) {
		_samlIdpSpConnection.setUserId(userId);
	}

	/**
	* Returns the user uuid of this saml idp sp connection.
	*
	* @return the user uuid of this saml idp sp connection
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _samlIdpSpConnection.getUserUuid();
	}

	/**
	* Sets the user uuid of this saml idp sp connection.
	*
	* @param userUuid the user uuid of this saml idp sp connection
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_samlIdpSpConnection.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this saml idp sp connection.
	*
	* @return the user name of this saml idp sp connection
	*/
	@Override
	public java.lang.String getUserName() {
		return _samlIdpSpConnection.getUserName();
	}

	/**
	* Sets the user name of this saml idp sp connection.
	*
	* @param userName the user name of this saml idp sp connection
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_samlIdpSpConnection.setUserName(userName);
	}

	/**
	* Returns the create date of this saml idp sp connection.
	*
	* @return the create date of this saml idp sp connection
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _samlIdpSpConnection.getCreateDate();
	}

	/**
	* Sets the create date of this saml idp sp connection.
	*
	* @param createDate the create date of this saml idp sp connection
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_samlIdpSpConnection.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this saml idp sp connection.
	*
	* @return the modified date of this saml idp sp connection
	*/
	@Override
	public java.util.Date getModifiedDate() {
		return _samlIdpSpConnection.getModifiedDate();
	}

	/**
	* Sets the modified date of this saml idp sp connection.
	*
	* @param modifiedDate the modified date of this saml idp sp connection
	*/
	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_samlIdpSpConnection.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the saml sp entity ID of this saml idp sp connection.
	*
	* @return the saml sp entity ID of this saml idp sp connection
	*/
	@Override
	public java.lang.String getSamlSpEntityId() {
		return _samlIdpSpConnection.getSamlSpEntityId();
	}

	/**
	* Sets the saml sp entity ID of this saml idp sp connection.
	*
	* @param samlSpEntityId the saml sp entity ID of this saml idp sp connection
	*/
	@Override
	public void setSamlSpEntityId(java.lang.String samlSpEntityId) {
		_samlIdpSpConnection.setSamlSpEntityId(samlSpEntityId);
	}

	/**
	* Returns the assertion lifetime of this saml idp sp connection.
	*
	* @return the assertion lifetime of this saml idp sp connection
	*/
	@Override
	public int getAssertionLifetime() {
		return _samlIdpSpConnection.getAssertionLifetime();
	}

	/**
	* Sets the assertion lifetime of this saml idp sp connection.
	*
	* @param assertionLifetime the assertion lifetime of this saml idp sp connection
	*/
	@Override
	public void setAssertionLifetime(int assertionLifetime) {
		_samlIdpSpConnection.setAssertionLifetime(assertionLifetime);
	}

	/**
	* Returns the attribute names of this saml idp sp connection.
	*
	* @return the attribute names of this saml idp sp connection
	*/
	@Override
	public java.lang.String getAttributeNames() {
		return _samlIdpSpConnection.getAttributeNames();
	}

	/**
	* Sets the attribute names of this saml idp sp connection.
	*
	* @param attributeNames the attribute names of this saml idp sp connection
	*/
	@Override
	public void setAttributeNames(java.lang.String attributeNames) {
		_samlIdpSpConnection.setAttributeNames(attributeNames);
	}

	/**
	* Returns the attributes enabled of this saml idp sp connection.
	*
	* @return the attributes enabled of this saml idp sp connection
	*/
	@Override
	public boolean getAttributesEnabled() {
		return _samlIdpSpConnection.getAttributesEnabled();
	}

	/**
	* Returns <code>true</code> if this saml idp sp connection is attributes enabled.
	*
	* @return <code>true</code> if this saml idp sp connection is attributes enabled; <code>false</code> otherwise
	*/
	@Override
	public boolean isAttributesEnabled() {
		return _samlIdpSpConnection.isAttributesEnabled();
	}

	/**
	* Sets whether this saml idp sp connection is attributes enabled.
	*
	* @param attributesEnabled the attributes enabled of this saml idp sp connection
	*/
	@Override
	public void setAttributesEnabled(boolean attributesEnabled) {
		_samlIdpSpConnection.setAttributesEnabled(attributesEnabled);
	}

	/**
	* Returns the attributes namespace enabled of this saml idp sp connection.
	*
	* @return the attributes namespace enabled of this saml idp sp connection
	*/
	@Override
	public boolean getAttributesNamespaceEnabled() {
		return _samlIdpSpConnection.getAttributesNamespaceEnabled();
	}

	/**
	* Returns <code>true</code> if this saml idp sp connection is attributes namespace enabled.
	*
	* @return <code>true</code> if this saml idp sp connection is attributes namespace enabled; <code>false</code> otherwise
	*/
	@Override
	public boolean isAttributesNamespaceEnabled() {
		return _samlIdpSpConnection.isAttributesNamespaceEnabled();
	}

	/**
	* Sets whether this saml idp sp connection is attributes namespace enabled.
	*
	* @param attributesNamespaceEnabled the attributes namespace enabled of this saml idp sp connection
	*/
	@Override
	public void setAttributesNamespaceEnabled(
		boolean attributesNamespaceEnabled) {
		_samlIdpSpConnection.setAttributesNamespaceEnabled(attributesNamespaceEnabled);
	}

	/**
	* Returns the enabled of this saml idp sp connection.
	*
	* @return the enabled of this saml idp sp connection
	*/
	@Override
	public boolean getEnabled() {
		return _samlIdpSpConnection.getEnabled();
	}

	/**
	* Returns <code>true</code> if this saml idp sp connection is enabled.
	*
	* @return <code>true</code> if this saml idp sp connection is enabled; <code>false</code> otherwise
	*/
	@Override
	public boolean isEnabled() {
		return _samlIdpSpConnection.isEnabled();
	}

	/**
	* Sets whether this saml idp sp connection is enabled.
	*
	* @param enabled the enabled of this saml idp sp connection
	*/
	@Override
	public void setEnabled(boolean enabled) {
		_samlIdpSpConnection.setEnabled(enabled);
	}

	/**
	* Returns the metadata url of this saml idp sp connection.
	*
	* @return the metadata url of this saml idp sp connection
	*/
	@Override
	public java.lang.String getMetadataUrl() {
		return _samlIdpSpConnection.getMetadataUrl();
	}

	/**
	* Sets the metadata url of this saml idp sp connection.
	*
	* @param metadataUrl the metadata url of this saml idp sp connection
	*/
	@Override
	public void setMetadataUrl(java.lang.String metadataUrl) {
		_samlIdpSpConnection.setMetadataUrl(metadataUrl);
	}

	/**
	* Returns the metadata xml of this saml idp sp connection.
	*
	* @return the metadata xml of this saml idp sp connection
	*/
	@Override
	public java.lang.String getMetadataXml() {
		return _samlIdpSpConnection.getMetadataXml();
	}

	/**
	* Sets the metadata xml of this saml idp sp connection.
	*
	* @param metadataXml the metadata xml of this saml idp sp connection
	*/
	@Override
	public void setMetadataXml(java.lang.String metadataXml) {
		_samlIdpSpConnection.setMetadataXml(metadataXml);
	}

	/**
	* Returns the metadata updated date of this saml idp sp connection.
	*
	* @return the metadata updated date of this saml idp sp connection
	*/
	@Override
	public java.util.Date getMetadataUpdatedDate() {
		return _samlIdpSpConnection.getMetadataUpdatedDate();
	}

	/**
	* Sets the metadata updated date of this saml idp sp connection.
	*
	* @param metadataUpdatedDate the metadata updated date of this saml idp sp connection
	*/
	@Override
	public void setMetadataUpdatedDate(java.util.Date metadataUpdatedDate) {
		_samlIdpSpConnection.setMetadataUpdatedDate(metadataUpdatedDate);
	}

	/**
	* Returns the name of this saml idp sp connection.
	*
	* @return the name of this saml idp sp connection
	*/
	@Override
	public java.lang.String getName() {
		return _samlIdpSpConnection.getName();
	}

	/**
	* Sets the name of this saml idp sp connection.
	*
	* @param name the name of this saml idp sp connection
	*/
	@Override
	public void setName(java.lang.String name) {
		_samlIdpSpConnection.setName(name);
	}

	/**
	* Returns the name ID attribute of this saml idp sp connection.
	*
	* @return the name ID attribute of this saml idp sp connection
	*/
	@Override
	public java.lang.String getNameIdAttribute() {
		return _samlIdpSpConnection.getNameIdAttribute();
	}

	/**
	* Sets the name ID attribute of this saml idp sp connection.
	*
	* @param nameIdAttribute the name ID attribute of this saml idp sp connection
	*/
	@Override
	public void setNameIdAttribute(java.lang.String nameIdAttribute) {
		_samlIdpSpConnection.setNameIdAttribute(nameIdAttribute);
	}

	/**
	* Returns the name ID format of this saml idp sp connection.
	*
	* @return the name ID format of this saml idp sp connection
	*/
	@Override
	public java.lang.String getNameIdFormat() {
		return _samlIdpSpConnection.getNameIdFormat();
	}

	/**
	* Sets the name ID format of this saml idp sp connection.
	*
	* @param nameIdFormat the name ID format of this saml idp sp connection
	*/
	@Override
	public void setNameIdFormat(java.lang.String nameIdFormat) {
		_samlIdpSpConnection.setNameIdFormat(nameIdFormat);
	}

	@Override
	public boolean isNew() {
		return _samlIdpSpConnection.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_samlIdpSpConnection.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _samlIdpSpConnection.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_samlIdpSpConnection.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _samlIdpSpConnection.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _samlIdpSpConnection.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_samlIdpSpConnection.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _samlIdpSpConnection.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_samlIdpSpConnection.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_samlIdpSpConnection.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_samlIdpSpConnection.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new SamlIdpSpConnectionWrapper((SamlIdpSpConnection)_samlIdpSpConnection.clone());
	}

	@Override
	public int compareTo(
		com.liferay.saml.model.SamlIdpSpConnection samlIdpSpConnection) {
		return _samlIdpSpConnection.compareTo(samlIdpSpConnection);
	}

	@Override
	public int hashCode() {
		return _samlIdpSpConnection.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.saml.model.SamlIdpSpConnection> toCacheModel() {
		return _samlIdpSpConnection.toCacheModel();
	}

	@Override
	public com.liferay.saml.model.SamlIdpSpConnection toEscapedModel() {
		return new SamlIdpSpConnectionWrapper(_samlIdpSpConnection.toEscapedModel());
	}

	@Override
	public com.liferay.saml.model.SamlIdpSpConnection toUnescapedModel() {
		return new SamlIdpSpConnectionWrapper(_samlIdpSpConnection.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _samlIdpSpConnection.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _samlIdpSpConnection.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_samlIdpSpConnection.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SamlIdpSpConnectionWrapper)) {
			return false;
		}

		SamlIdpSpConnectionWrapper samlIdpSpConnectionWrapper = (SamlIdpSpConnectionWrapper)obj;

		if (Validator.equals(_samlIdpSpConnection,
					samlIdpSpConnectionWrapper._samlIdpSpConnection)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public SamlIdpSpConnection getWrappedSamlIdpSpConnection() {
		return _samlIdpSpConnection;
	}

	@Override
	public SamlIdpSpConnection getWrappedModel() {
		return _samlIdpSpConnection;
	}

	@Override
	public void resetOriginalValues() {
		_samlIdpSpConnection.resetOriginalValues();
	}

	private SamlIdpSpConnection _samlIdpSpConnection;
}