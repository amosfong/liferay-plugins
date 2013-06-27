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
 * This class is a wrapper for {@link SamlSpSession}.
 * </p>
 *
 * @author Mika Koivisto
 * @see SamlSpSession
 * @generated
 */
public class SamlSpSessionWrapper implements SamlSpSession,
	ModelWrapper<SamlSpSession> {
	public SamlSpSessionWrapper(SamlSpSession samlSpSession) {
		_samlSpSession = samlSpSession;
	}

	@Override
	public Class<?> getModelClass() {
		return SamlSpSession.class;
	}

	@Override
	public String getModelClassName() {
		return SamlSpSession.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("samlSpSessionId", getSamlSpSessionId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("samlSpSessionKey", getSamlSpSessionKey());
		attributes.put("assertionXml", getAssertionXml());
		attributes.put("jSessionId", getJSessionId());
		attributes.put("nameIdFormat", getNameIdFormat());
		attributes.put("nameIdValue", getNameIdValue());
		attributes.put("sessionIndex", getSessionIndex());
		attributes.put("terminated", getTerminated());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long samlSpSessionId = (Long)attributes.get("samlSpSessionId");

		if (samlSpSessionId != null) {
			setSamlSpSessionId(samlSpSessionId);
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

		String samlSpSessionKey = (String)attributes.get("samlSpSessionKey");

		if (samlSpSessionKey != null) {
			setSamlSpSessionKey(samlSpSessionKey);
		}

		String assertionXml = (String)attributes.get("assertionXml");

		if (assertionXml != null) {
			setAssertionXml(assertionXml);
		}

		String jSessionId = (String)attributes.get("jSessionId");

		if (jSessionId != null) {
			setJSessionId(jSessionId);
		}

		String nameIdFormat = (String)attributes.get("nameIdFormat");

		if (nameIdFormat != null) {
			setNameIdFormat(nameIdFormat);
		}

		String nameIdValue = (String)attributes.get("nameIdValue");

		if (nameIdValue != null) {
			setNameIdValue(nameIdValue);
		}

		String sessionIndex = (String)attributes.get("sessionIndex");

		if (sessionIndex != null) {
			setSessionIndex(sessionIndex);
		}

		Boolean terminated = (Boolean)attributes.get("terminated");

		if (terminated != null) {
			setTerminated(terminated);
		}
	}

	/**
	* Returns the primary key of this saml sp session.
	*
	* @return the primary key of this saml sp session
	*/
	@Override
	public long getPrimaryKey() {
		return _samlSpSession.getPrimaryKey();
	}

	/**
	* Sets the primary key of this saml sp session.
	*
	* @param primaryKey the primary key of this saml sp session
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_samlSpSession.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the saml sp session ID of this saml sp session.
	*
	* @return the saml sp session ID of this saml sp session
	*/
	@Override
	public long getSamlSpSessionId() {
		return _samlSpSession.getSamlSpSessionId();
	}

	/**
	* Sets the saml sp session ID of this saml sp session.
	*
	* @param samlSpSessionId the saml sp session ID of this saml sp session
	*/
	@Override
	public void setSamlSpSessionId(long samlSpSessionId) {
		_samlSpSession.setSamlSpSessionId(samlSpSessionId);
	}

	/**
	* Returns the company ID of this saml sp session.
	*
	* @return the company ID of this saml sp session
	*/
	@Override
	public long getCompanyId() {
		return _samlSpSession.getCompanyId();
	}

	/**
	* Sets the company ID of this saml sp session.
	*
	* @param companyId the company ID of this saml sp session
	*/
	@Override
	public void setCompanyId(long companyId) {
		_samlSpSession.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this saml sp session.
	*
	* @return the user ID of this saml sp session
	*/
	@Override
	public long getUserId() {
		return _samlSpSession.getUserId();
	}

	/**
	* Sets the user ID of this saml sp session.
	*
	* @param userId the user ID of this saml sp session
	*/
	@Override
	public void setUserId(long userId) {
		_samlSpSession.setUserId(userId);
	}

	/**
	* Returns the user uuid of this saml sp session.
	*
	* @return the user uuid of this saml sp session
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _samlSpSession.getUserUuid();
	}

	/**
	* Sets the user uuid of this saml sp session.
	*
	* @param userUuid the user uuid of this saml sp session
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_samlSpSession.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this saml sp session.
	*
	* @return the user name of this saml sp session
	*/
	@Override
	public java.lang.String getUserName() {
		return _samlSpSession.getUserName();
	}

	/**
	* Sets the user name of this saml sp session.
	*
	* @param userName the user name of this saml sp session
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_samlSpSession.setUserName(userName);
	}

	/**
	* Returns the create date of this saml sp session.
	*
	* @return the create date of this saml sp session
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _samlSpSession.getCreateDate();
	}

	/**
	* Sets the create date of this saml sp session.
	*
	* @param createDate the create date of this saml sp session
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_samlSpSession.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this saml sp session.
	*
	* @return the modified date of this saml sp session
	*/
	@Override
	public java.util.Date getModifiedDate() {
		return _samlSpSession.getModifiedDate();
	}

	/**
	* Sets the modified date of this saml sp session.
	*
	* @param modifiedDate the modified date of this saml sp session
	*/
	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_samlSpSession.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the saml sp session key of this saml sp session.
	*
	* @return the saml sp session key of this saml sp session
	*/
	@Override
	public java.lang.String getSamlSpSessionKey() {
		return _samlSpSession.getSamlSpSessionKey();
	}

	/**
	* Sets the saml sp session key of this saml sp session.
	*
	* @param samlSpSessionKey the saml sp session key of this saml sp session
	*/
	@Override
	public void setSamlSpSessionKey(java.lang.String samlSpSessionKey) {
		_samlSpSession.setSamlSpSessionKey(samlSpSessionKey);
	}

	/**
	* Returns the assertion xml of this saml sp session.
	*
	* @return the assertion xml of this saml sp session
	*/
	@Override
	public java.lang.String getAssertionXml() {
		return _samlSpSession.getAssertionXml();
	}

	/**
	* Sets the assertion xml of this saml sp session.
	*
	* @param assertionXml the assertion xml of this saml sp session
	*/
	@Override
	public void setAssertionXml(java.lang.String assertionXml) {
		_samlSpSession.setAssertionXml(assertionXml);
	}

	/**
	* Returns the j session ID of this saml sp session.
	*
	* @return the j session ID of this saml sp session
	*/
	@Override
	public java.lang.String getJSessionId() {
		return _samlSpSession.getJSessionId();
	}

	/**
	* Sets the j session ID of this saml sp session.
	*
	* @param jSessionId the j session ID of this saml sp session
	*/
	@Override
	public void setJSessionId(java.lang.String jSessionId) {
		_samlSpSession.setJSessionId(jSessionId);
	}

	/**
	* Returns the name ID format of this saml sp session.
	*
	* @return the name ID format of this saml sp session
	*/
	@Override
	public java.lang.String getNameIdFormat() {
		return _samlSpSession.getNameIdFormat();
	}

	/**
	* Sets the name ID format of this saml sp session.
	*
	* @param nameIdFormat the name ID format of this saml sp session
	*/
	@Override
	public void setNameIdFormat(java.lang.String nameIdFormat) {
		_samlSpSession.setNameIdFormat(nameIdFormat);
	}

	/**
	* Returns the name ID value of this saml sp session.
	*
	* @return the name ID value of this saml sp session
	*/
	@Override
	public java.lang.String getNameIdValue() {
		return _samlSpSession.getNameIdValue();
	}

	/**
	* Sets the name ID value of this saml sp session.
	*
	* @param nameIdValue the name ID value of this saml sp session
	*/
	@Override
	public void setNameIdValue(java.lang.String nameIdValue) {
		_samlSpSession.setNameIdValue(nameIdValue);
	}

	/**
	* Returns the session index of this saml sp session.
	*
	* @return the session index of this saml sp session
	*/
	@Override
	public java.lang.String getSessionIndex() {
		return _samlSpSession.getSessionIndex();
	}

	/**
	* Sets the session index of this saml sp session.
	*
	* @param sessionIndex the session index of this saml sp session
	*/
	@Override
	public void setSessionIndex(java.lang.String sessionIndex) {
		_samlSpSession.setSessionIndex(sessionIndex);
	}

	/**
	* Returns the terminated of this saml sp session.
	*
	* @return the terminated of this saml sp session
	*/
	@Override
	public boolean getTerminated() {
		return _samlSpSession.getTerminated();
	}

	/**
	* Returns <code>true</code> if this saml sp session is terminated.
	*
	* @return <code>true</code> if this saml sp session is terminated; <code>false</code> otherwise
	*/
	@Override
	public boolean isTerminated() {
		return _samlSpSession.isTerminated();
	}

	/**
	* Sets whether this saml sp session is terminated.
	*
	* @param terminated the terminated of this saml sp session
	*/
	@Override
	public void setTerminated(boolean terminated) {
		_samlSpSession.setTerminated(terminated);
	}

	@Override
	public boolean isNew() {
		return _samlSpSession.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_samlSpSession.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _samlSpSession.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_samlSpSession.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _samlSpSession.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _samlSpSession.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_samlSpSession.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _samlSpSession.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_samlSpSession.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_samlSpSession.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_samlSpSession.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new SamlSpSessionWrapper((SamlSpSession)_samlSpSession.clone());
	}

	@Override
	public int compareTo(com.liferay.saml.model.SamlSpSession samlSpSession) {
		return _samlSpSession.compareTo(samlSpSession);
	}

	@Override
	public int hashCode() {
		return _samlSpSession.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.saml.model.SamlSpSession> toCacheModel() {
		return _samlSpSession.toCacheModel();
	}

	@Override
	public com.liferay.saml.model.SamlSpSession toEscapedModel() {
		return new SamlSpSessionWrapper(_samlSpSession.toEscapedModel());
	}

	@Override
	public com.liferay.saml.model.SamlSpSession toUnescapedModel() {
		return new SamlSpSessionWrapper(_samlSpSession.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _samlSpSession.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _samlSpSession.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_samlSpSession.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SamlSpSessionWrapper)) {
			return false;
		}

		SamlSpSessionWrapper samlSpSessionWrapper = (SamlSpSessionWrapper)obj;

		if (Validator.equals(_samlSpSession, samlSpSessionWrapper._samlSpSession)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public SamlSpSession getWrappedSamlSpSession() {
		return _samlSpSession;
	}

	@Override
	public SamlSpSession getWrappedModel() {
		return _samlSpSession;
	}

	@Override
	public void resetOriginalValues() {
		_samlSpSession.resetOriginalValues();
	}

	private SamlSpSession _samlSpSession;
}