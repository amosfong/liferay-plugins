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
 * This class is a wrapper for {@link OAuthApplication}.
 * </p>
 *
 * @author Ivica Cardic
 * @see OAuthApplication
 * @generated
 */
public class OAuthApplicationWrapper implements OAuthApplication,
	ModelWrapper<OAuthApplication> {
	public OAuthApplicationWrapper(OAuthApplication oAuthApplication) {
		_oAuthApplication = oAuthApplication;
	}

	@Override
	public Class<?> getModelClass() {
		return OAuthApplication.class;
	}

	@Override
	public String getModelClassName() {
		return OAuthApplication.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("oAuthApplicationId", getOAuthApplicationId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("consumerKey", getConsumerKey());
		attributes.put("consumerSecret", getConsumerSecret());
		attributes.put("accessLevel", getAccessLevel());
		attributes.put("logoId", getLogoId());
		attributes.put("shareableAccessToken", getShareableAccessToken());
		attributes.put("callbackURI", getCallbackURI());
		attributes.put("websiteURL", getWebsiteURL());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long oAuthApplicationId = (Long)attributes.get("oAuthApplicationId");

		if (oAuthApplicationId != null) {
			setOAuthApplicationId(oAuthApplicationId);
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

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String consumerKey = (String)attributes.get("consumerKey");

		if (consumerKey != null) {
			setConsumerKey(consumerKey);
		}

		String consumerSecret = (String)attributes.get("consumerSecret");

		if (consumerSecret != null) {
			setConsumerSecret(consumerSecret);
		}

		Integer accessLevel = (Integer)attributes.get("accessLevel");

		if (accessLevel != null) {
			setAccessLevel(accessLevel);
		}

		Long logoId = (Long)attributes.get("logoId");

		if (logoId != null) {
			setLogoId(logoId);
		}

		Boolean shareableAccessToken = (Boolean)attributes.get(
				"shareableAccessToken");

		if (shareableAccessToken != null) {
			setShareableAccessToken(shareableAccessToken);
		}

		String callbackURI = (String)attributes.get("callbackURI");

		if (callbackURI != null) {
			setCallbackURI(callbackURI);
		}

		String websiteURL = (String)attributes.get("websiteURL");

		if (websiteURL != null) {
			setWebsiteURL(websiteURL);
		}
	}

	/**
	* Returns the primary key of this o auth application.
	*
	* @return the primary key of this o auth application
	*/
	@Override
	public long getPrimaryKey() {
		return _oAuthApplication.getPrimaryKey();
	}

	/**
	* Sets the primary key of this o auth application.
	*
	* @param primaryKey the primary key of this o auth application
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_oAuthApplication.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the o auth application ID of this o auth application.
	*
	* @return the o auth application ID of this o auth application
	*/
	@Override
	public long getOAuthApplicationId() {
		return _oAuthApplication.getOAuthApplicationId();
	}

	/**
	* Sets the o auth application ID of this o auth application.
	*
	* @param oAuthApplicationId the o auth application ID of this o auth application
	*/
	@Override
	public void setOAuthApplicationId(long oAuthApplicationId) {
		_oAuthApplication.setOAuthApplicationId(oAuthApplicationId);
	}

	/**
	* Returns the company ID of this o auth application.
	*
	* @return the company ID of this o auth application
	*/
	@Override
	public long getCompanyId() {
		return _oAuthApplication.getCompanyId();
	}

	/**
	* Sets the company ID of this o auth application.
	*
	* @param companyId the company ID of this o auth application
	*/
	@Override
	public void setCompanyId(long companyId) {
		_oAuthApplication.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this o auth application.
	*
	* @return the user ID of this o auth application
	*/
	@Override
	public long getUserId() {
		return _oAuthApplication.getUserId();
	}

	/**
	* Sets the user ID of this o auth application.
	*
	* @param userId the user ID of this o auth application
	*/
	@Override
	public void setUserId(long userId) {
		_oAuthApplication.setUserId(userId);
	}

	/**
	* Returns the user uuid of this o auth application.
	*
	* @return the user uuid of this o auth application
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _oAuthApplication.getUserUuid();
	}

	/**
	* Sets the user uuid of this o auth application.
	*
	* @param userUuid the user uuid of this o auth application
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_oAuthApplication.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this o auth application.
	*
	* @return the user name of this o auth application
	*/
	@Override
	public java.lang.String getUserName() {
		return _oAuthApplication.getUserName();
	}

	/**
	* Sets the user name of this o auth application.
	*
	* @param userName the user name of this o auth application
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_oAuthApplication.setUserName(userName);
	}

	/**
	* Returns the create date of this o auth application.
	*
	* @return the create date of this o auth application
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _oAuthApplication.getCreateDate();
	}

	/**
	* Sets the create date of this o auth application.
	*
	* @param createDate the create date of this o auth application
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_oAuthApplication.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this o auth application.
	*
	* @return the modified date of this o auth application
	*/
	@Override
	public java.util.Date getModifiedDate() {
		return _oAuthApplication.getModifiedDate();
	}

	/**
	* Sets the modified date of this o auth application.
	*
	* @param modifiedDate the modified date of this o auth application
	*/
	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_oAuthApplication.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the name of this o auth application.
	*
	* @return the name of this o auth application
	*/
	@Override
	public java.lang.String getName() {
		return _oAuthApplication.getName();
	}

	/**
	* Sets the name of this o auth application.
	*
	* @param name the name of this o auth application
	*/
	@Override
	public void setName(java.lang.String name) {
		_oAuthApplication.setName(name);
	}

	/**
	* Returns the description of this o auth application.
	*
	* @return the description of this o auth application
	*/
	@Override
	public java.lang.String getDescription() {
		return _oAuthApplication.getDescription();
	}

	/**
	* Sets the description of this o auth application.
	*
	* @param description the description of this o auth application
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_oAuthApplication.setDescription(description);
	}

	/**
	* Returns the consumer key of this o auth application.
	*
	* @return the consumer key of this o auth application
	*/
	@Override
	public java.lang.String getConsumerKey() {
		return _oAuthApplication.getConsumerKey();
	}

	/**
	* Sets the consumer key of this o auth application.
	*
	* @param consumerKey the consumer key of this o auth application
	*/
	@Override
	public void setConsumerKey(java.lang.String consumerKey) {
		_oAuthApplication.setConsumerKey(consumerKey);
	}

	/**
	* Returns the consumer secret of this o auth application.
	*
	* @return the consumer secret of this o auth application
	*/
	@Override
	public java.lang.String getConsumerSecret() {
		return _oAuthApplication.getConsumerSecret();
	}

	/**
	* Sets the consumer secret of this o auth application.
	*
	* @param consumerSecret the consumer secret of this o auth application
	*/
	@Override
	public void setConsumerSecret(java.lang.String consumerSecret) {
		_oAuthApplication.setConsumerSecret(consumerSecret);
	}

	/**
	* Returns the access level of this o auth application.
	*
	* @return the access level of this o auth application
	*/
	@Override
	public int getAccessLevel() {
		return _oAuthApplication.getAccessLevel();
	}

	/**
	* Sets the access level of this o auth application.
	*
	* @param accessLevel the access level of this o auth application
	*/
	@Override
	public void setAccessLevel(int accessLevel) {
		_oAuthApplication.setAccessLevel(accessLevel);
	}

	/**
	* Returns the logo ID of this o auth application.
	*
	* @return the logo ID of this o auth application
	*/
	@Override
	public long getLogoId() {
		return _oAuthApplication.getLogoId();
	}

	/**
	* Sets the logo ID of this o auth application.
	*
	* @param logoId the logo ID of this o auth application
	*/
	@Override
	public void setLogoId(long logoId) {
		_oAuthApplication.setLogoId(logoId);
	}

	/**
	* Returns the shareable access token of this o auth application.
	*
	* @return the shareable access token of this o auth application
	*/
	@Override
	public boolean getShareableAccessToken() {
		return _oAuthApplication.getShareableAccessToken();
	}

	/**
	* Returns <code>true</code> if this o auth application is shareable access token.
	*
	* @return <code>true</code> if this o auth application is shareable access token; <code>false</code> otherwise
	*/
	@Override
	public boolean isShareableAccessToken() {
		return _oAuthApplication.isShareableAccessToken();
	}

	/**
	* Sets whether this o auth application is shareable access token.
	*
	* @param shareableAccessToken the shareable access token of this o auth application
	*/
	@Override
	public void setShareableAccessToken(boolean shareableAccessToken) {
		_oAuthApplication.setShareableAccessToken(shareableAccessToken);
	}

	/**
	* Returns the callback u r i of this o auth application.
	*
	* @return the callback u r i of this o auth application
	*/
	@Override
	public java.lang.String getCallbackURI() {
		return _oAuthApplication.getCallbackURI();
	}

	/**
	* Sets the callback u r i of this o auth application.
	*
	* @param callbackURI the callback u r i of this o auth application
	*/
	@Override
	public void setCallbackURI(java.lang.String callbackURI) {
		_oAuthApplication.setCallbackURI(callbackURI);
	}

	/**
	* Returns the website u r l of this o auth application.
	*
	* @return the website u r l of this o auth application
	*/
	@Override
	public java.lang.String getWebsiteURL() {
		return _oAuthApplication.getWebsiteURL();
	}

	/**
	* Sets the website u r l of this o auth application.
	*
	* @param websiteURL the website u r l of this o auth application
	*/
	@Override
	public void setWebsiteURL(java.lang.String websiteURL) {
		_oAuthApplication.setWebsiteURL(websiteURL);
	}

	@Override
	public boolean isNew() {
		return _oAuthApplication.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_oAuthApplication.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _oAuthApplication.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_oAuthApplication.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _oAuthApplication.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _oAuthApplication.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_oAuthApplication.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _oAuthApplication.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_oAuthApplication.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_oAuthApplication.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_oAuthApplication.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new OAuthApplicationWrapper((OAuthApplication)_oAuthApplication.clone());
	}

	@Override
	public int compareTo(
		com.liferay.oauth.model.OAuthApplication oAuthApplication) {
		return _oAuthApplication.compareTo(oAuthApplication);
	}

	@Override
	public int hashCode() {
		return _oAuthApplication.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.oauth.model.OAuthApplication> toCacheModel() {
		return _oAuthApplication.toCacheModel();
	}

	@Override
	public com.liferay.oauth.model.OAuthApplication toEscapedModel() {
		return new OAuthApplicationWrapper(_oAuthApplication.toEscapedModel());
	}

	@Override
	public com.liferay.oauth.model.OAuthApplication toUnescapedModel() {
		return new OAuthApplicationWrapper(_oAuthApplication.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _oAuthApplication.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _oAuthApplication.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_oAuthApplication.persist();
	}

	@Override
	public java.lang.String getAccessLevelLabel() {
		return _oAuthApplication.getAccessLevelLabel();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof OAuthApplicationWrapper)) {
			return false;
		}

		OAuthApplicationWrapper oAuthApplicationWrapper = (OAuthApplicationWrapper)obj;

		if (Validator.equals(_oAuthApplication,
					oAuthApplicationWrapper._oAuthApplication)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public OAuthApplication getWrappedOAuthApplication() {
		return _oAuthApplication;
	}

	@Override
	public OAuthApplication getWrappedModel() {
		return _oAuthApplication;
	}

	@Override
	public void resetOriginalValues() {
		_oAuthApplication.resetOriginalValues();
	}

	private OAuthApplication _oAuthApplication;
}