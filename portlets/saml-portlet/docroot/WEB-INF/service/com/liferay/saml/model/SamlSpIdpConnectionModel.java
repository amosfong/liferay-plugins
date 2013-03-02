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

package com.liferay.saml.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.AuditedModel;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the SamlSpIdpConnection service. Represents a row in the &quot;SamlSpIdpConnection&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.saml.model.impl.SamlSpIdpConnectionModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.saml.model.impl.SamlSpIdpConnectionImpl}.
 * </p>
 *
 * @author Mika Koivisto
 * @see SamlSpIdpConnection
 * @see com.liferay.saml.model.impl.SamlSpIdpConnectionImpl
 * @see com.liferay.saml.model.impl.SamlSpIdpConnectionModelImpl
 * @generated
 */
public interface SamlSpIdpConnectionModel extends AuditedModel,
	BaseModel<SamlSpIdpConnection> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a saml sp idp connection model instance should use the {@link SamlSpIdpConnection} interface instead.
	 */

	/**
	 * Returns the primary key of this saml sp idp connection.
	 *
	 * @return the primary key of this saml sp idp connection
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this saml sp idp connection.
	 *
	 * @param primaryKey the primary key of this saml sp idp connection
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the saml sp idp connection ID of this saml sp idp connection.
	 *
	 * @return the saml sp idp connection ID of this saml sp idp connection
	 */
	public long getSamlSpIdpConnectionId();

	/**
	 * Sets the saml sp idp connection ID of this saml sp idp connection.
	 *
	 * @param samlSpIdpConnectionId the saml sp idp connection ID of this saml sp idp connection
	 */
	public void setSamlSpIdpConnectionId(long samlSpIdpConnectionId);

	/**
	 * Returns the company ID of this saml sp idp connection.
	 *
	 * @return the company ID of this saml sp idp connection
	 */
	public long getCompanyId();

	/**
	 * Sets the company ID of this saml sp idp connection.
	 *
	 * @param companyId the company ID of this saml sp idp connection
	 */
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this saml sp idp connection.
	 *
	 * @return the user ID of this saml sp idp connection
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this saml sp idp connection.
	 *
	 * @param userId the user ID of this saml sp idp connection
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this saml sp idp connection.
	 *
	 * @return the user uuid of this saml sp idp connection
	 * @throws SystemException if a system exception occurred
	 */
	public String getUserUuid() throws SystemException;

	/**
	 * Sets the user uuid of this saml sp idp connection.
	 *
	 * @param userUuid the user uuid of this saml sp idp connection
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this saml sp idp connection.
	 *
	 * @return the user name of this saml sp idp connection
	 */
	@AutoEscape
	public String getUserName();

	/**
	 * Sets the user name of this saml sp idp connection.
	 *
	 * @param userName the user name of this saml sp idp connection
	 */
	public void setUserName(String userName);

	/**
	 * Returns the create date of this saml sp idp connection.
	 *
	 * @return the create date of this saml sp idp connection
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this saml sp idp connection.
	 *
	 * @param createDate the create date of this saml sp idp connection
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this saml sp idp connection.
	 *
	 * @return the modified date of this saml sp idp connection
	 */
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this saml sp idp connection.
	 *
	 * @param modifiedDate the modified date of this saml sp idp connection
	 */
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the saml idp entity ID of this saml sp idp connection.
	 *
	 * @return the saml idp entity ID of this saml sp idp connection
	 */
	@AutoEscape
	public String getSamlIdpEntityId();

	/**
	 * Sets the saml idp entity ID of this saml sp idp connection.
	 *
	 * @param samlIdpEntityId the saml idp entity ID of this saml sp idp connection
	 */
	public void setSamlIdpEntityId(String samlIdpEntityId);

	/**
	 * Returns the assertion signature required of this saml sp idp connection.
	 *
	 * @return the assertion signature required of this saml sp idp connection
	 */
	public boolean getAssertionSignatureRequired();

	/**
	 * Returns <code>true</code> if this saml sp idp connection is assertion signature required.
	 *
	 * @return <code>true</code> if this saml sp idp connection is assertion signature required; <code>false</code> otherwise
	 */
	public boolean isAssertionSignatureRequired();

	/**
	 * Sets whether this saml sp idp connection is assertion signature required.
	 *
	 * @param assertionSignatureRequired the assertion signature required of this saml sp idp connection
	 */
	public void setAssertionSignatureRequired(
		boolean assertionSignatureRequired);

	/**
	 * Returns the clock skew of this saml sp idp connection.
	 *
	 * @return the clock skew of this saml sp idp connection
	 */
	public long getClockSkew();

	/**
	 * Sets the clock skew of this saml sp idp connection.
	 *
	 * @param clockSkew the clock skew of this saml sp idp connection
	 */
	public void setClockSkew(long clockSkew);

	/**
	 * Returns the enabled of this saml sp idp connection.
	 *
	 * @return the enabled of this saml sp idp connection
	 */
	public boolean getEnabled();

	/**
	 * Returns <code>true</code> if this saml sp idp connection is enabled.
	 *
	 * @return <code>true</code> if this saml sp idp connection is enabled; <code>false</code> otherwise
	 */
	public boolean isEnabled();

	/**
	 * Sets whether this saml sp idp connection is enabled.
	 *
	 * @param enabled the enabled of this saml sp idp connection
	 */
	public void setEnabled(boolean enabled);

	/**
	 * Returns the ldap import enabled of this saml sp idp connection.
	 *
	 * @return the ldap import enabled of this saml sp idp connection
	 */
	public boolean getLdapImportEnabled();

	/**
	 * Returns <code>true</code> if this saml sp idp connection is ldap import enabled.
	 *
	 * @return <code>true</code> if this saml sp idp connection is ldap import enabled; <code>false</code> otherwise
	 */
	public boolean isLdapImportEnabled();

	/**
	 * Sets whether this saml sp idp connection is ldap import enabled.
	 *
	 * @param ldapImportEnabled the ldap import enabled of this saml sp idp connection
	 */
	public void setLdapImportEnabled(boolean ldapImportEnabled);

	/**
	 * Returns the metadata url of this saml sp idp connection.
	 *
	 * @return the metadata url of this saml sp idp connection
	 */
	@AutoEscape
	public String getMetadataUrl();

	/**
	 * Sets the metadata url of this saml sp idp connection.
	 *
	 * @param metadataUrl the metadata url of this saml sp idp connection
	 */
	public void setMetadataUrl(String metadataUrl);

	/**
	 * Returns the metadata xml of this saml sp idp connection.
	 *
	 * @return the metadata xml of this saml sp idp connection
	 */
	@AutoEscape
	public String getMetadataXml();

	/**
	 * Sets the metadata xml of this saml sp idp connection.
	 *
	 * @param metadataXml the metadata xml of this saml sp idp connection
	 */
	public void setMetadataXml(String metadataXml);

	/**
	 * Returns the metadata updated date of this saml sp idp connection.
	 *
	 * @return the metadata updated date of this saml sp idp connection
	 */
	public Date getMetadataUpdatedDate();

	/**
	 * Sets the metadata updated date of this saml sp idp connection.
	 *
	 * @param metadataUpdatedDate the metadata updated date of this saml sp idp connection
	 */
	public void setMetadataUpdatedDate(Date metadataUpdatedDate);

	/**
	 * Returns the name of this saml sp idp connection.
	 *
	 * @return the name of this saml sp idp connection
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this saml sp idp connection.
	 *
	 * @param name the name of this saml sp idp connection
	 */
	public void setName(String name);

	/**
	 * Returns the name ID format of this saml sp idp connection.
	 *
	 * @return the name ID format of this saml sp idp connection
	 */
	@AutoEscape
	public String getNameIdFormat();

	/**
	 * Sets the name ID format of this saml sp idp connection.
	 *
	 * @param nameIdFormat the name ID format of this saml sp idp connection
	 */
	public void setNameIdFormat(String nameIdFormat);

	/**
	 * Returns the sign authn request of this saml sp idp connection.
	 *
	 * @return the sign authn request of this saml sp idp connection
	 */
	public boolean getSignAuthnRequest();

	/**
	 * Returns <code>true</code> if this saml sp idp connection is sign authn request.
	 *
	 * @return <code>true</code> if this saml sp idp connection is sign authn request; <code>false</code> otherwise
	 */
	public boolean isSignAuthnRequest();

	/**
	 * Sets whether this saml sp idp connection is sign authn request.
	 *
	 * @param signAuthnRequest the sign authn request of this saml sp idp connection
	 */
	public void setSignAuthnRequest(boolean signAuthnRequest);

	/**
	 * Returns the user attribute mappings of this saml sp idp connection.
	 *
	 * @return the user attribute mappings of this saml sp idp connection
	 */
	@AutoEscape
	public String getUserAttributeMappings();

	/**
	 * Sets the user attribute mappings of this saml sp idp connection.
	 *
	 * @param userAttributeMappings the user attribute mappings of this saml sp idp connection
	 */
	public void setUserAttributeMappings(String userAttributeMappings);

	public boolean isNew();

	public void setNew(boolean n);

	public boolean isCachedModel();

	public void setCachedModel(boolean cachedModel);

	public boolean isEscapedModel();

	public Serializable getPrimaryKeyObj();

	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	public ExpandoBridge getExpandoBridge();

	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	public Object clone();

	public int compareTo(SamlSpIdpConnection samlSpIdpConnection);

	public int hashCode();

	public CacheModel<SamlSpIdpConnection> toCacheModel();

	public SamlSpIdpConnection toEscapedModel();

	public SamlSpIdpConnection toUnescapedModel();

	public String toString();

	public String toXmlString();
}