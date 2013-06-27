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
 * This class is a wrapper for {@link SamlSpAuthRequest}.
 * </p>
 *
 * @author Mika Koivisto
 * @see SamlSpAuthRequest
 * @generated
 */
public class SamlSpAuthRequestWrapper implements SamlSpAuthRequest,
	ModelWrapper<SamlSpAuthRequest> {
	public SamlSpAuthRequestWrapper(SamlSpAuthRequest samlSpAuthRequest) {
		_samlSpAuthRequest = samlSpAuthRequest;
	}

	@Override
	public Class<?> getModelClass() {
		return SamlSpAuthRequest.class;
	}

	@Override
	public String getModelClassName() {
		return SamlSpAuthRequest.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("samlSpAuthnRequestId", getSamlSpAuthnRequestId());
		attributes.put("companyId", getCompanyId());
		attributes.put("createDate", getCreateDate());
		attributes.put("samlIdpEntityId", getSamlIdpEntityId());
		attributes.put("samlSpAuthRequestKey", getSamlSpAuthRequestKey());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long samlSpAuthnRequestId = (Long)attributes.get("samlSpAuthnRequestId");

		if (samlSpAuthnRequestId != null) {
			setSamlSpAuthnRequestId(samlSpAuthnRequestId);
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

		String samlSpAuthRequestKey = (String)attributes.get(
				"samlSpAuthRequestKey");

		if (samlSpAuthRequestKey != null) {
			setSamlSpAuthRequestKey(samlSpAuthRequestKey);
		}
	}

	/**
	* Returns the primary key of this saml sp auth request.
	*
	* @return the primary key of this saml sp auth request
	*/
	@Override
	public long getPrimaryKey() {
		return _samlSpAuthRequest.getPrimaryKey();
	}

	/**
	* Sets the primary key of this saml sp auth request.
	*
	* @param primaryKey the primary key of this saml sp auth request
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_samlSpAuthRequest.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the saml sp authn request ID of this saml sp auth request.
	*
	* @return the saml sp authn request ID of this saml sp auth request
	*/
	@Override
	public long getSamlSpAuthnRequestId() {
		return _samlSpAuthRequest.getSamlSpAuthnRequestId();
	}

	/**
	* Sets the saml sp authn request ID of this saml sp auth request.
	*
	* @param samlSpAuthnRequestId the saml sp authn request ID of this saml sp auth request
	*/
	@Override
	public void setSamlSpAuthnRequestId(long samlSpAuthnRequestId) {
		_samlSpAuthRequest.setSamlSpAuthnRequestId(samlSpAuthnRequestId);
	}

	/**
	* Returns the company ID of this saml sp auth request.
	*
	* @return the company ID of this saml sp auth request
	*/
	@Override
	public long getCompanyId() {
		return _samlSpAuthRequest.getCompanyId();
	}

	/**
	* Sets the company ID of this saml sp auth request.
	*
	* @param companyId the company ID of this saml sp auth request
	*/
	@Override
	public void setCompanyId(long companyId) {
		_samlSpAuthRequest.setCompanyId(companyId);
	}

	/**
	* Returns the create date of this saml sp auth request.
	*
	* @return the create date of this saml sp auth request
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _samlSpAuthRequest.getCreateDate();
	}

	/**
	* Sets the create date of this saml sp auth request.
	*
	* @param createDate the create date of this saml sp auth request
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_samlSpAuthRequest.setCreateDate(createDate);
	}

	/**
	* Returns the saml idp entity ID of this saml sp auth request.
	*
	* @return the saml idp entity ID of this saml sp auth request
	*/
	@Override
	public java.lang.String getSamlIdpEntityId() {
		return _samlSpAuthRequest.getSamlIdpEntityId();
	}

	/**
	* Sets the saml idp entity ID of this saml sp auth request.
	*
	* @param samlIdpEntityId the saml idp entity ID of this saml sp auth request
	*/
	@Override
	public void setSamlIdpEntityId(java.lang.String samlIdpEntityId) {
		_samlSpAuthRequest.setSamlIdpEntityId(samlIdpEntityId);
	}

	/**
	* Returns the saml sp auth request key of this saml sp auth request.
	*
	* @return the saml sp auth request key of this saml sp auth request
	*/
	@Override
	public java.lang.String getSamlSpAuthRequestKey() {
		return _samlSpAuthRequest.getSamlSpAuthRequestKey();
	}

	/**
	* Sets the saml sp auth request key of this saml sp auth request.
	*
	* @param samlSpAuthRequestKey the saml sp auth request key of this saml sp auth request
	*/
	@Override
	public void setSamlSpAuthRequestKey(java.lang.String samlSpAuthRequestKey) {
		_samlSpAuthRequest.setSamlSpAuthRequestKey(samlSpAuthRequestKey);
	}

	@Override
	public boolean isNew() {
		return _samlSpAuthRequest.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_samlSpAuthRequest.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _samlSpAuthRequest.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_samlSpAuthRequest.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _samlSpAuthRequest.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _samlSpAuthRequest.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_samlSpAuthRequest.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _samlSpAuthRequest.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_samlSpAuthRequest.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_samlSpAuthRequest.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_samlSpAuthRequest.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new SamlSpAuthRequestWrapper((SamlSpAuthRequest)_samlSpAuthRequest.clone());
	}

	@Override
	public int compareTo(
		com.liferay.saml.model.SamlSpAuthRequest samlSpAuthRequest) {
		return _samlSpAuthRequest.compareTo(samlSpAuthRequest);
	}

	@Override
	public int hashCode() {
		return _samlSpAuthRequest.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.saml.model.SamlSpAuthRequest> toCacheModel() {
		return _samlSpAuthRequest.toCacheModel();
	}

	@Override
	public com.liferay.saml.model.SamlSpAuthRequest toEscapedModel() {
		return new SamlSpAuthRequestWrapper(_samlSpAuthRequest.toEscapedModel());
	}

	@Override
	public com.liferay.saml.model.SamlSpAuthRequest toUnescapedModel() {
		return new SamlSpAuthRequestWrapper(_samlSpAuthRequest.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _samlSpAuthRequest.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _samlSpAuthRequest.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_samlSpAuthRequest.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SamlSpAuthRequestWrapper)) {
			return false;
		}

		SamlSpAuthRequestWrapper samlSpAuthRequestWrapper = (SamlSpAuthRequestWrapper)obj;

		if (Validator.equals(_samlSpAuthRequest,
					samlSpAuthRequestWrapper._samlSpAuthRequest)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public SamlSpAuthRequest getWrappedSamlSpAuthRequest() {
		return _samlSpAuthRequest;
	}

	@Override
	public SamlSpAuthRequest getWrappedModel() {
		return _samlSpAuthRequest;
	}

	@Override
	public void resetOriginalValues() {
		_samlSpAuthRequest.resetOriginalValues();
	}

	private SamlSpAuthRequest _samlSpAuthRequest;
}