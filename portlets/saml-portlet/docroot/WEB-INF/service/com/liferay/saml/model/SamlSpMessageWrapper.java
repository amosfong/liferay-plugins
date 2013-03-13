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

import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link SamlSpMessage}.
 * </p>
 *
 * @author    Mika Koivisto
 * @see       SamlSpMessage
 * @generated
 */
public class SamlSpMessageWrapper implements SamlSpMessage,
	ModelWrapper<SamlSpMessage> {
	public SamlSpMessageWrapper(SamlSpMessage samlSpMessage) {
		_samlSpMessage = samlSpMessage;
	}

	public Class<?> getModelClass() {
		return SamlSpMessage.class;
	}

	public String getModelClassName() {
		return SamlSpMessage.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("samlSpMessageId", getSamlSpMessageId());
		attributes.put("companyId", getCompanyId());
		attributes.put("createDate", getCreateDate());
		attributes.put("samlIdpEntityId", getSamlIdpEntityId());
		attributes.put("samlIdpResponseKey", getSamlIdpResponseKey());
		attributes.put("expirationDate", getExpirationDate());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long samlSpMessageId = (Long)attributes.get("samlSpMessageId");

		if (samlSpMessageId != null) {
			setSamlSpMessageId(samlSpMessageId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		String samlIdpEntityId = (String)attributes.get("samlIdpEntityId");

		if (samlIdpEntityId != null) {
			setSamlIdpEntityId(samlIdpEntityId);
		}

		String samlIdpResponseKey = (String)attributes.get("samlIdpResponseKey");

		if (samlIdpResponseKey != null) {
			setSamlIdpResponseKey(samlIdpResponseKey);
		}

		Date expirationDate = (Date)attributes.get("expirationDate");

		if (expirationDate != null) {
			setExpirationDate(expirationDate);
		}
	}

	/**
	* Returns the primary key of this saml sp message.
	*
	* @return the primary key of this saml sp message
	*/
	public long getPrimaryKey() {
		return _samlSpMessage.getPrimaryKey();
	}

	/**
	* Sets the primary key of this saml sp message.
	*
	* @param primaryKey the primary key of this saml sp message
	*/
	public void setPrimaryKey(long primaryKey) {
		_samlSpMessage.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the saml sp message ID of this saml sp message.
	*
	* @return the saml sp message ID of this saml sp message
	*/
	public long getSamlSpMessageId() {
		return _samlSpMessage.getSamlSpMessageId();
	}

	/**
	* Sets the saml sp message ID of this saml sp message.
	*
	* @param samlSpMessageId the saml sp message ID of this saml sp message
	*/
	public void setSamlSpMessageId(long samlSpMessageId) {
		_samlSpMessage.setSamlSpMessageId(samlSpMessageId);
	}

	/**
	* Returns the company ID of this saml sp message.
	*
	* @return the company ID of this saml sp message
	*/
	public long getCompanyId() {
		return _samlSpMessage.getCompanyId();
	}

	/**
	* Sets the company ID of this saml sp message.
	*
	* @param companyId the company ID of this saml sp message
	*/
	public void setCompanyId(long companyId) {
		_samlSpMessage.setCompanyId(companyId);
	}

	/**
	* Returns the create date of this saml sp message.
	*
	* @return the create date of this saml sp message
	*/
	public java.util.Date getCreateDate() {
		return _samlSpMessage.getCreateDate();
	}

	/**
	* Sets the create date of this saml sp message.
	*
	* @param createDate the create date of this saml sp message
	*/
	public void setCreateDate(java.util.Date createDate) {
		_samlSpMessage.setCreateDate(createDate);
	}

	/**
	* Returns the saml idp entity ID of this saml sp message.
	*
	* @return the saml idp entity ID of this saml sp message
	*/
	public java.lang.String getSamlIdpEntityId() {
		return _samlSpMessage.getSamlIdpEntityId();
	}

	/**
	* Sets the saml idp entity ID of this saml sp message.
	*
	* @param samlIdpEntityId the saml idp entity ID of this saml sp message
	*/
	public void setSamlIdpEntityId(java.lang.String samlIdpEntityId) {
		_samlSpMessage.setSamlIdpEntityId(samlIdpEntityId);
	}

	/**
	* Returns the saml idp response key of this saml sp message.
	*
	* @return the saml idp response key of this saml sp message
	*/
	public java.lang.String getSamlIdpResponseKey() {
		return _samlSpMessage.getSamlIdpResponseKey();
	}

	/**
	* Sets the saml idp response key of this saml sp message.
	*
	* @param samlIdpResponseKey the saml idp response key of this saml sp message
	*/
	public void setSamlIdpResponseKey(java.lang.String samlIdpResponseKey) {
		_samlSpMessage.setSamlIdpResponseKey(samlIdpResponseKey);
	}

	/**
	* Returns the expiration date of this saml sp message.
	*
	* @return the expiration date of this saml sp message
	*/
	public java.util.Date getExpirationDate() {
		return _samlSpMessage.getExpirationDate();
	}

	/**
	* Sets the expiration date of this saml sp message.
	*
	* @param expirationDate the expiration date of this saml sp message
	*/
	public void setExpirationDate(java.util.Date expirationDate) {
		_samlSpMessage.setExpirationDate(expirationDate);
	}

	public boolean isNew() {
		return _samlSpMessage.isNew();
	}

	public void setNew(boolean n) {
		_samlSpMessage.setNew(n);
	}

	public boolean isCachedModel() {
		return _samlSpMessage.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_samlSpMessage.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _samlSpMessage.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _samlSpMessage.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_samlSpMessage.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _samlSpMessage.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_samlSpMessage.setExpandoBridgeAttributes(baseModel);
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_samlSpMessage.setExpandoBridgeAttributes(expandoBridge);
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_samlSpMessage.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new SamlSpMessageWrapper((SamlSpMessage)_samlSpMessage.clone());
	}

	public int compareTo(com.liferay.saml.model.SamlSpMessage samlSpMessage) {
		return _samlSpMessage.compareTo(samlSpMessage);
	}

	@Override
	public int hashCode() {
		return _samlSpMessage.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.saml.model.SamlSpMessage> toCacheModel() {
		return _samlSpMessage.toCacheModel();
	}

	public com.liferay.saml.model.SamlSpMessage toEscapedModel() {
		return new SamlSpMessageWrapper(_samlSpMessage.toEscapedModel());
	}

	public com.liferay.saml.model.SamlSpMessage toUnescapedModel() {
		return new SamlSpMessageWrapper(_samlSpMessage.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _samlSpMessage.toString();
	}

	public java.lang.String toXmlString() {
		return _samlSpMessage.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_samlSpMessage.persist();
	}

	public boolean isExpired() {
		return _samlSpMessage.isExpired();
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public SamlSpMessage getWrappedSamlSpMessage() {
		return _samlSpMessage;
	}

	public SamlSpMessage getWrappedModel() {
		return _samlSpMessage;
	}

	public void resetOriginalValues() {
		_samlSpMessage.resetOriginalValues();
	}

	private SamlSpMessage _samlSpMessage;
}