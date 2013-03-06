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
 * This class is a wrapper for {@link SamlSpSession}.
 * </p>
 *
 * @author    Mika Koivisto
 * @see       SamlSpSession
 * @generated
 */
public class SamlSpSessionWrapper implements SamlSpSession,
	ModelWrapper<SamlSpSession> {
	public SamlSpSessionWrapper(SamlSpSession samlSpSession) {
		_samlSpSession = samlSpSession;
	}

	public Class<?> getModelClass() {
		return SamlSpSession.class;
	}

	public String getModelClassName() {
		return SamlSpSession.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("samlSpSessionId", getSamlSpSessionId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("jSessionId", getJSessionId());
		attributes.put("nameIdFormat", getNameIdFormat());
		attributes.put("nameIdValue", getNameIdValue());
		attributes.put("terminated", getTerminated());

		return attributes;
	}

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
	public long getPrimaryKey() {
		return _samlSpSession.getPrimaryKey();
	}

	/**
	* Sets the primary key of this saml sp session.
	*
	* @param primaryKey the primary key of this saml sp session
	*/
	public void setPrimaryKey(long primaryKey) {
		_samlSpSession.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the saml sp session ID of this saml sp session.
	*
	* @return the saml sp session ID of this saml sp session
	*/
	public long getSamlSpSessionId() {
		return _samlSpSession.getSamlSpSessionId();
	}

	/**
	* Sets the saml sp session ID of this saml sp session.
	*
	* @param samlSpSessionId the saml sp session ID of this saml sp session
	*/
	public void setSamlSpSessionId(long samlSpSessionId) {
		_samlSpSession.setSamlSpSessionId(samlSpSessionId);
	}

	/**
	* Returns the company ID of this saml sp session.
	*
	* @return the company ID of this saml sp session
	*/
	public long getCompanyId() {
		return _samlSpSession.getCompanyId();
	}

	/**
	* Sets the company ID of this saml sp session.
	*
	* @param companyId the company ID of this saml sp session
	*/
	public void setCompanyId(long companyId) {
		_samlSpSession.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this saml sp session.
	*
	* @return the user ID of this saml sp session
	*/
	public long getUserId() {
		return _samlSpSession.getUserId();
	}

	/**
	* Sets the user ID of this saml sp session.
	*
	* @param userId the user ID of this saml sp session
	*/
	public void setUserId(long userId) {
		_samlSpSession.setUserId(userId);
	}

	/**
	* Returns the user uuid of this saml sp session.
	*
	* @return the user uuid of this saml sp session
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _samlSpSession.getUserUuid();
	}

	/**
	* Sets the user uuid of this saml sp session.
	*
	* @param userUuid the user uuid of this saml sp session
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_samlSpSession.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this saml sp session.
	*
	* @return the user name of this saml sp session
	*/
	public java.lang.String getUserName() {
		return _samlSpSession.getUserName();
	}

	/**
	* Sets the user name of this saml sp session.
	*
	* @param userName the user name of this saml sp session
	*/
	public void setUserName(java.lang.String userName) {
		_samlSpSession.setUserName(userName);
	}

	/**
	* Returns the create date of this saml sp session.
	*
	* @return the create date of this saml sp session
	*/
	public java.util.Date getCreateDate() {
		return _samlSpSession.getCreateDate();
	}

	/**
	* Sets the create date of this saml sp session.
	*
	* @param createDate the create date of this saml sp session
	*/
	public void setCreateDate(java.util.Date createDate) {
		_samlSpSession.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this saml sp session.
	*
	* @return the modified date of this saml sp session
	*/
	public java.util.Date getModifiedDate() {
		return _samlSpSession.getModifiedDate();
	}

	/**
	* Sets the modified date of this saml sp session.
	*
	* @param modifiedDate the modified date of this saml sp session
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_samlSpSession.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the j session ID of this saml sp session.
	*
	* @return the j session ID of this saml sp session
	*/
	public java.lang.String getJSessionId() {
		return _samlSpSession.getJSessionId();
	}

	/**
	* Sets the j session ID of this saml sp session.
	*
	* @param jSessionId the j session ID of this saml sp session
	*/
	public void setJSessionId(java.lang.String jSessionId) {
		_samlSpSession.setJSessionId(jSessionId);
	}

	/**
	* Returns the name ID format of this saml sp session.
	*
	* @return the name ID format of this saml sp session
	*/
	public java.lang.String getNameIdFormat() {
		return _samlSpSession.getNameIdFormat();
	}

	/**
	* Sets the name ID format of this saml sp session.
	*
	* @param nameIdFormat the name ID format of this saml sp session
	*/
	public void setNameIdFormat(java.lang.String nameIdFormat) {
		_samlSpSession.setNameIdFormat(nameIdFormat);
	}

	/**
	* Returns the name ID value of this saml sp session.
	*
	* @return the name ID value of this saml sp session
	*/
	public java.lang.String getNameIdValue() {
		return _samlSpSession.getNameIdValue();
	}

	/**
	* Sets the name ID value of this saml sp session.
	*
	* @param nameIdValue the name ID value of this saml sp session
	*/
	public void setNameIdValue(java.lang.String nameIdValue) {
		_samlSpSession.setNameIdValue(nameIdValue);
	}

	/**
	* Returns the terminated of this saml sp session.
	*
	* @return the terminated of this saml sp session
	*/
	public boolean getTerminated() {
		return _samlSpSession.getTerminated();
	}

	/**
	* Returns <code>true</code> if this saml sp session is terminated.
	*
	* @return <code>true</code> if this saml sp session is terminated; <code>false</code> otherwise
	*/
	public boolean isTerminated() {
		return _samlSpSession.isTerminated();
	}

	/**
	* Sets whether this saml sp session is terminated.
	*
	* @param terminated the terminated of this saml sp session
	*/
	public void setTerminated(boolean terminated) {
		_samlSpSession.setTerminated(terminated);
	}

	public boolean isNew() {
		return _samlSpSession.isNew();
	}

	public void setNew(boolean n) {
		_samlSpSession.setNew(n);
	}

	public boolean isCachedModel() {
		return _samlSpSession.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_samlSpSession.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _samlSpSession.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _samlSpSession.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_samlSpSession.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _samlSpSession.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_samlSpSession.setExpandoBridgeAttributes(baseModel);
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_samlSpSession.setExpandoBridgeAttributes(expandoBridge);
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_samlSpSession.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new SamlSpSessionWrapper((SamlSpSession)_samlSpSession.clone());
	}

	public int compareTo(com.liferay.saml.model.SamlSpSession samlSpSession) {
		return _samlSpSession.compareTo(samlSpSession);
	}

	@Override
	public int hashCode() {
		return _samlSpSession.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.saml.model.SamlSpSession> toCacheModel() {
		return _samlSpSession.toCacheModel();
	}

	public com.liferay.saml.model.SamlSpSession toEscapedModel() {
		return new SamlSpSessionWrapper(_samlSpSession.toEscapedModel());
	}

	public com.liferay.saml.model.SamlSpSession toUnescapedModel() {
		return new SamlSpSessionWrapper(_samlSpSession.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _samlSpSession.toString();
	}

	public java.lang.String toXmlString() {
		return _samlSpSession.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_samlSpSession.persist();
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public SamlSpSession getWrappedSamlSpSession() {
		return _samlSpSession;
	}

	public SamlSpSession getWrappedModel() {
		return _samlSpSession;
	}

	public void resetOriginalValues() {
		_samlSpSession.resetOriginalValues();
	}

	private SamlSpSession _samlSpSession;
}