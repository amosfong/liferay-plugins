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

package com.liferay.oauth.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link OAuthUser}.
 * </p>
 *
 * @author Ivica Cardic
 * @see OAuthUser
 * @generated
 */
public class OAuthUserWrapper implements OAuthUser, ModelWrapper<OAuthUser> {
	public OAuthUserWrapper(OAuthUser oAuthUser) {
		_oAuthUser = oAuthUser;
	}

	@Override
	public Class<?> getModelClass() {
		return OAuthUser.class;
	}

	@Override
	public String getModelClassName() {
		return OAuthUser.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("oAuthUserId", getOAuthUserId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("oAuthApplicationId", getOAuthApplicationId());
		attributes.put("accessToken", getAccessToken());
		attributes.put("accessSecret", getAccessSecret());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long oAuthUserId = (Long)attributes.get("oAuthUserId");

		if (oAuthUserId != null) {
			setOAuthUserId(oAuthUserId);
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

		Long oAuthApplicationId = (Long)attributes.get("oAuthApplicationId");

		if (oAuthApplicationId != null) {
			setOAuthApplicationId(oAuthApplicationId);
		}

		String accessToken = (String)attributes.get("accessToken");

		if (accessToken != null) {
			setAccessToken(accessToken);
		}

		String accessSecret = (String)attributes.get("accessSecret");

		if (accessSecret != null) {
			setAccessSecret(accessSecret);
		}
	}

	/**
	* Returns the primary key of this o auth user.
	*
	* @return the primary key of this o auth user
	*/
	@Override
	public long getPrimaryKey() {
		return _oAuthUser.getPrimaryKey();
	}

	/**
	* Sets the primary key of this o auth user.
	*
	* @param primaryKey the primary key of this o auth user
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_oAuthUser.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the o auth user ID of this o auth user.
	*
	* @return the o auth user ID of this o auth user
	*/
	@Override
	public long getOAuthUserId() {
		return _oAuthUser.getOAuthUserId();
	}

	/**
	* Sets the o auth user ID of this o auth user.
	*
	* @param oAuthUserId the o auth user ID of this o auth user
	*/
	@Override
	public void setOAuthUserId(long oAuthUserId) {
		_oAuthUser.setOAuthUserId(oAuthUserId);
	}

	/**
	* Returns the o auth user uuid of this o auth user.
	*
	* @return the o auth user uuid of this o auth user
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getOAuthUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _oAuthUser.getOAuthUserUuid();
	}

	/**
	* Sets the o auth user uuid of this o auth user.
	*
	* @param oAuthUserUuid the o auth user uuid of this o auth user
	*/
	@Override
	public void setOAuthUserUuid(java.lang.String oAuthUserUuid) {
		_oAuthUser.setOAuthUserUuid(oAuthUserUuid);
	}

	/**
	* Returns the company ID of this o auth user.
	*
	* @return the company ID of this o auth user
	*/
	@Override
	public long getCompanyId() {
		return _oAuthUser.getCompanyId();
	}

	/**
	* Sets the company ID of this o auth user.
	*
	* @param companyId the company ID of this o auth user
	*/
	@Override
	public void setCompanyId(long companyId) {
		_oAuthUser.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this o auth user.
	*
	* @return the user ID of this o auth user
	*/
	@Override
	public long getUserId() {
		return _oAuthUser.getUserId();
	}

	/**
	* Sets the user ID of this o auth user.
	*
	* @param userId the user ID of this o auth user
	*/
	@Override
	public void setUserId(long userId) {
		_oAuthUser.setUserId(userId);
	}

	/**
	* Returns the user uuid of this o auth user.
	*
	* @return the user uuid of this o auth user
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _oAuthUser.getUserUuid();
	}

	/**
	* Sets the user uuid of this o auth user.
	*
	* @param userUuid the user uuid of this o auth user
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_oAuthUser.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this o auth user.
	*
	* @return the user name of this o auth user
	*/
	@Override
	public java.lang.String getUserName() {
		return _oAuthUser.getUserName();
	}

	/**
	* Sets the user name of this o auth user.
	*
	* @param userName the user name of this o auth user
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_oAuthUser.setUserName(userName);
	}

	/**
	* Returns the create date of this o auth user.
	*
	* @return the create date of this o auth user
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _oAuthUser.getCreateDate();
	}

	/**
	* Sets the create date of this o auth user.
	*
	* @param createDate the create date of this o auth user
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_oAuthUser.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this o auth user.
	*
	* @return the modified date of this o auth user
	*/
	@Override
	public java.util.Date getModifiedDate() {
		return _oAuthUser.getModifiedDate();
	}

	/**
	* Sets the modified date of this o auth user.
	*
	* @param modifiedDate the modified date of this o auth user
	*/
	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_oAuthUser.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the o auth application ID of this o auth user.
	*
	* @return the o auth application ID of this o auth user
	*/
	@Override
	public long getOAuthApplicationId() {
		return _oAuthUser.getOAuthApplicationId();
	}

	/**
	* Sets the o auth application ID of this o auth user.
	*
	* @param oAuthApplicationId the o auth application ID of this o auth user
	*/
	@Override
	public void setOAuthApplicationId(long oAuthApplicationId) {
		_oAuthUser.setOAuthApplicationId(oAuthApplicationId);
	}

	/**
	* Returns the access token of this o auth user.
	*
	* @return the access token of this o auth user
	*/
	@Override
	public java.lang.String getAccessToken() {
		return _oAuthUser.getAccessToken();
	}

	/**
	* Sets the access token of this o auth user.
	*
	* @param accessToken the access token of this o auth user
	*/
	@Override
	public void setAccessToken(java.lang.String accessToken) {
		_oAuthUser.setAccessToken(accessToken);
	}

	/**
	* Returns the access secret of this o auth user.
	*
	* @return the access secret of this o auth user
	*/
	@Override
	public java.lang.String getAccessSecret() {
		return _oAuthUser.getAccessSecret();
	}

	/**
	* Sets the access secret of this o auth user.
	*
	* @param accessSecret the access secret of this o auth user
	*/
	@Override
	public void setAccessSecret(java.lang.String accessSecret) {
		_oAuthUser.setAccessSecret(accessSecret);
	}

	@Override
	public boolean isNew() {
		return _oAuthUser.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_oAuthUser.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _oAuthUser.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_oAuthUser.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _oAuthUser.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _oAuthUser.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_oAuthUser.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _oAuthUser.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_oAuthUser.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_oAuthUser.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_oAuthUser.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new OAuthUserWrapper((OAuthUser)_oAuthUser.clone());
	}

	@Override
	public int compareTo(com.liferay.oauth.model.OAuthUser oAuthUser) {
		return _oAuthUser.compareTo(oAuthUser);
	}

	@Override
	public int hashCode() {
		return _oAuthUser.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.oauth.model.OAuthUser> toCacheModel() {
		return _oAuthUser.toCacheModel();
	}

	@Override
	public com.liferay.oauth.model.OAuthUser toEscapedModel() {
		return new OAuthUserWrapper(_oAuthUser.toEscapedModel());
	}

	@Override
	public com.liferay.oauth.model.OAuthUser toUnescapedModel() {
		return new OAuthUserWrapper(_oAuthUser.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _oAuthUser.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _oAuthUser.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_oAuthUser.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof OAuthUserWrapper)) {
			return false;
		}

		OAuthUserWrapper oAuthUserWrapper = (OAuthUserWrapper)obj;

		if (Validator.equals(_oAuthUser, oAuthUserWrapper._oAuthUser)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public OAuthUser getWrappedOAuthUser() {
		return _oAuthUser;
	}

	@Override
	public OAuthUser getWrappedModel() {
		return _oAuthUser;
	}

	@Override
	public void resetOriginalValues() {
		_oAuthUser.resetOriginalValues();
	}

	private OAuthUser _oAuthUser;
}