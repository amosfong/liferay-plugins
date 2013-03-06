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

import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link SamlIdpSpSession}.
 * </p>
 *
 * @author    Mika Koivisto
 * @see       SamlIdpSpSession
 * @generated
 */
public class SamlIdpSpSessionWrapper implements SamlIdpSpSession,
	ModelWrapper<SamlIdpSpSession> {
	public SamlIdpSpSessionWrapper(SamlIdpSpSession samlIdpSpSession) {
		_samlIdpSpSession = samlIdpSpSession;
	}

	public Class<?> getModelClass() {
		return SamlIdpSpSession.class;
	}

	public String getModelClassName() {
		return SamlIdpSpSession.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("samlIdpSpSessionId", getSamlIdpSpSessionId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("samlIdpSsoSessionId", getSamlIdpSsoSessionId());
		attributes.put("samlSpEntityId", getSamlSpEntityId());
		attributes.put("nameIdFormat", getNameIdFormat());
		attributes.put("nameIdValue", getNameIdValue());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long samlIdpSpSessionId = (Long)attributes.get("samlIdpSpSessionId");

		if (samlIdpSpSessionId != null) {
			setSamlIdpSpSessionId(samlIdpSpSessionId);
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

		Long samlIdpSsoSessionId = (Long)attributes.get("samlIdpSsoSessionId");

		if (samlIdpSsoSessionId != null) {
			setSamlIdpSsoSessionId(samlIdpSsoSessionId);
		}

		String samlSpEntityId = (String)attributes.get("samlSpEntityId");

		if (samlSpEntityId != null) {
			setSamlSpEntityId(samlSpEntityId);
		}

		String nameIdFormat = (String)attributes.get("nameIdFormat");

		if (nameIdFormat != null) {
			setNameIdFormat(nameIdFormat);
		}

		String nameIdValue = (String)attributes.get("nameIdValue");

		if (nameIdValue != null) {
			setNameIdValue(nameIdValue);
		}
	}

	/**
	* Returns the primary key of this saml idp sp session.
	*
	* @return the primary key of this saml idp sp session
	*/
	public long getPrimaryKey() {
		return _samlIdpSpSession.getPrimaryKey();
	}

	/**
	* Sets the primary key of this saml idp sp session.
	*
	* @param primaryKey the primary key of this saml idp sp session
	*/
	public void setPrimaryKey(long primaryKey) {
		_samlIdpSpSession.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the saml idp sp session ID of this saml idp sp session.
	*
	* @return the saml idp sp session ID of this saml idp sp session
	*/
	public long getSamlIdpSpSessionId() {
		return _samlIdpSpSession.getSamlIdpSpSessionId();
	}

	/**
	* Sets the saml idp sp session ID of this saml idp sp session.
	*
	* @param samlIdpSpSessionId the saml idp sp session ID of this saml idp sp session
	*/
	public void setSamlIdpSpSessionId(long samlIdpSpSessionId) {
		_samlIdpSpSession.setSamlIdpSpSessionId(samlIdpSpSessionId);
	}

	/**
	* Returns the company ID of this saml idp sp session.
	*
	* @return the company ID of this saml idp sp session
	*/
	public long getCompanyId() {
		return _samlIdpSpSession.getCompanyId();
	}

	/**
	* Sets the company ID of this saml idp sp session.
	*
	* @param companyId the company ID of this saml idp sp session
	*/
	public void setCompanyId(long companyId) {
		_samlIdpSpSession.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this saml idp sp session.
	*
	* @return the user ID of this saml idp sp session
	*/
	public long getUserId() {
		return _samlIdpSpSession.getUserId();
	}

	/**
	* Sets the user ID of this saml idp sp session.
	*
	* @param userId the user ID of this saml idp sp session
	*/
	public void setUserId(long userId) {
		_samlIdpSpSession.setUserId(userId);
	}

	/**
	* Returns the user uuid of this saml idp sp session.
	*
	* @return the user uuid of this saml idp sp session
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _samlIdpSpSession.getUserUuid();
	}

	/**
	* Sets the user uuid of this saml idp sp session.
	*
	* @param userUuid the user uuid of this saml idp sp session
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_samlIdpSpSession.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this saml idp sp session.
	*
	* @return the user name of this saml idp sp session
	*/
	public java.lang.String getUserName() {
		return _samlIdpSpSession.getUserName();
	}

	/**
	* Sets the user name of this saml idp sp session.
	*
	* @param userName the user name of this saml idp sp session
	*/
	public void setUserName(java.lang.String userName) {
		_samlIdpSpSession.setUserName(userName);
	}

	/**
	* Returns the create date of this saml idp sp session.
	*
	* @return the create date of this saml idp sp session
	*/
	public java.util.Date getCreateDate() {
		return _samlIdpSpSession.getCreateDate();
	}

	/**
	* Sets the create date of this saml idp sp session.
	*
	* @param createDate the create date of this saml idp sp session
	*/
	public void setCreateDate(java.util.Date createDate) {
		_samlIdpSpSession.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this saml idp sp session.
	*
	* @return the modified date of this saml idp sp session
	*/
	public java.util.Date getModifiedDate() {
		return _samlIdpSpSession.getModifiedDate();
	}

	/**
	* Sets the modified date of this saml idp sp session.
	*
	* @param modifiedDate the modified date of this saml idp sp session
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_samlIdpSpSession.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the saml idp sso session ID of this saml idp sp session.
	*
	* @return the saml idp sso session ID of this saml idp sp session
	*/
	public long getSamlIdpSsoSessionId() {
		return _samlIdpSpSession.getSamlIdpSsoSessionId();
	}

	/**
	* Sets the saml idp sso session ID of this saml idp sp session.
	*
	* @param samlIdpSsoSessionId the saml idp sso session ID of this saml idp sp session
	*/
	public void setSamlIdpSsoSessionId(long samlIdpSsoSessionId) {
		_samlIdpSpSession.setSamlIdpSsoSessionId(samlIdpSsoSessionId);
	}

	/**
	* Returns the saml sp entity ID of this saml idp sp session.
	*
	* @return the saml sp entity ID of this saml idp sp session
	*/
	public java.lang.String getSamlSpEntityId() {
		return _samlIdpSpSession.getSamlSpEntityId();
	}

	/**
	* Sets the saml sp entity ID of this saml idp sp session.
	*
	* @param samlSpEntityId the saml sp entity ID of this saml idp sp session
	*/
	public void setSamlSpEntityId(java.lang.String samlSpEntityId) {
		_samlIdpSpSession.setSamlSpEntityId(samlSpEntityId);
	}

	/**
	* Returns the name ID format of this saml idp sp session.
	*
	* @return the name ID format of this saml idp sp session
	*/
	public java.lang.String getNameIdFormat() {
		return _samlIdpSpSession.getNameIdFormat();
	}

	/**
	* Sets the name ID format of this saml idp sp session.
	*
	* @param nameIdFormat the name ID format of this saml idp sp session
	*/
	public void setNameIdFormat(java.lang.String nameIdFormat) {
		_samlIdpSpSession.setNameIdFormat(nameIdFormat);
	}

	/**
	* Returns the name ID value of this saml idp sp session.
	*
	* @return the name ID value of this saml idp sp session
	*/
	public java.lang.String getNameIdValue() {
		return _samlIdpSpSession.getNameIdValue();
	}

	/**
	* Sets the name ID value of this saml idp sp session.
	*
	* @param nameIdValue the name ID value of this saml idp sp session
	*/
	public void setNameIdValue(java.lang.String nameIdValue) {
		_samlIdpSpSession.setNameIdValue(nameIdValue);
	}

	public boolean isNew() {
		return _samlIdpSpSession.isNew();
	}

	public void setNew(boolean n) {
		_samlIdpSpSession.setNew(n);
	}

	public boolean isCachedModel() {
		return _samlIdpSpSession.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_samlIdpSpSession.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _samlIdpSpSession.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _samlIdpSpSession.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_samlIdpSpSession.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _samlIdpSpSession.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_samlIdpSpSession.setExpandoBridgeAttributes(baseModel);
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_samlIdpSpSession.setExpandoBridgeAttributes(expandoBridge);
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_samlIdpSpSession.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new SamlIdpSpSessionWrapper((SamlIdpSpSession)_samlIdpSpSession.clone());
	}

	public int compareTo(
		com.liferay.saml.model.SamlIdpSpSession samlIdpSpSession) {
		return _samlIdpSpSession.compareTo(samlIdpSpSession);
	}

	@Override
	public int hashCode() {
		return _samlIdpSpSession.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.saml.model.SamlIdpSpSession> toCacheModel() {
		return _samlIdpSpSession.toCacheModel();
	}

	public com.liferay.saml.model.SamlIdpSpSession toEscapedModel() {
		return new SamlIdpSpSessionWrapper(_samlIdpSpSession.toEscapedModel());
	}

	public com.liferay.saml.model.SamlIdpSpSession toUnescapedModel() {
		return new SamlIdpSpSessionWrapper(_samlIdpSpSession.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _samlIdpSpSession.toString();
	}

	public java.lang.String toXmlString() {
		return _samlIdpSpSession.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_samlIdpSpSession.persist();
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public SamlIdpSpSession getWrappedSamlIdpSpSession() {
		return _samlIdpSpSession;
	}

	public SamlIdpSpSession getWrappedModel() {
		return _samlIdpSpSession;
	}

	public void resetOriginalValues() {
		_samlIdpSpSession.resetOriginalValues();
	}

	private SamlIdpSpSession _samlIdpSpSession;
}