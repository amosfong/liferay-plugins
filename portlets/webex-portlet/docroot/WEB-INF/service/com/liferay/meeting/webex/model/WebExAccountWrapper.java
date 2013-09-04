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

package com.liferay.meeting.webex.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link WebExAccount}.
 * </p>
 *
 * @author    Anant Singh
 * @see       WebExAccount
 * @generated
 */
public class WebExAccountWrapper implements WebExAccount,
	ModelWrapper<WebExAccount> {
	public WebExAccountWrapper(WebExAccount webExAccount) {
		_webExAccount = webExAccount;
	}

	public Class<?> getModelClass() {
		return WebExAccount.class;
	}

	public String getModelClassName() {
		return WebExAccount.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("webExAccountId", getWebExAccountId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("webExSiteId", getWebExSiteId());
		attributes.put("login", getLogin());
		attributes.put("password", getPassword());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long webExAccountId = (Long)attributes.get("webExAccountId");

		if (webExAccountId != null) {
			setWebExAccountId(webExAccountId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
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

		Long webExSiteId = (Long)attributes.get("webExSiteId");

		if (webExSiteId != null) {
			setWebExSiteId(webExSiteId);
		}

		String login = (String)attributes.get("login");

		if (login != null) {
			setLogin(login);
		}

		String password = (String)attributes.get("password");

		if (password != null) {
			setPassword(password);
		}
	}

	/**
	* Returns the primary key of this web ex account.
	*
	* @return the primary key of this web ex account
	*/
	public long getPrimaryKey() {
		return _webExAccount.getPrimaryKey();
	}

	/**
	* Sets the primary key of this web ex account.
	*
	* @param primaryKey the primary key of this web ex account
	*/
	public void setPrimaryKey(long primaryKey) {
		_webExAccount.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this web ex account.
	*
	* @return the uuid of this web ex account
	*/
	public java.lang.String getUuid() {
		return _webExAccount.getUuid();
	}

	/**
	* Sets the uuid of this web ex account.
	*
	* @param uuid the uuid of this web ex account
	*/
	public void setUuid(java.lang.String uuid) {
		_webExAccount.setUuid(uuid);
	}

	/**
	* Returns the web ex account ID of this web ex account.
	*
	* @return the web ex account ID of this web ex account
	*/
	public long getWebExAccountId() {
		return _webExAccount.getWebExAccountId();
	}

	/**
	* Sets the web ex account ID of this web ex account.
	*
	* @param webExAccountId the web ex account ID of this web ex account
	*/
	public void setWebExAccountId(long webExAccountId) {
		_webExAccount.setWebExAccountId(webExAccountId);
	}

	/**
	* Returns the group ID of this web ex account.
	*
	* @return the group ID of this web ex account
	*/
	public long getGroupId() {
		return _webExAccount.getGroupId();
	}

	/**
	* Sets the group ID of this web ex account.
	*
	* @param groupId the group ID of this web ex account
	*/
	public void setGroupId(long groupId) {
		_webExAccount.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this web ex account.
	*
	* @return the company ID of this web ex account
	*/
	public long getCompanyId() {
		return _webExAccount.getCompanyId();
	}

	/**
	* Sets the company ID of this web ex account.
	*
	* @param companyId the company ID of this web ex account
	*/
	public void setCompanyId(long companyId) {
		_webExAccount.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this web ex account.
	*
	* @return the user ID of this web ex account
	*/
	public long getUserId() {
		return _webExAccount.getUserId();
	}

	/**
	* Sets the user ID of this web ex account.
	*
	* @param userId the user ID of this web ex account
	*/
	public void setUserId(long userId) {
		_webExAccount.setUserId(userId);
	}

	/**
	* Returns the user uuid of this web ex account.
	*
	* @return the user uuid of this web ex account
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _webExAccount.getUserUuid();
	}

	/**
	* Sets the user uuid of this web ex account.
	*
	* @param userUuid the user uuid of this web ex account
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_webExAccount.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this web ex account.
	*
	* @return the user name of this web ex account
	*/
	public java.lang.String getUserName() {
		return _webExAccount.getUserName();
	}

	/**
	* Sets the user name of this web ex account.
	*
	* @param userName the user name of this web ex account
	*/
	public void setUserName(java.lang.String userName) {
		_webExAccount.setUserName(userName);
	}

	/**
	* Returns the create date of this web ex account.
	*
	* @return the create date of this web ex account
	*/
	public java.util.Date getCreateDate() {
		return _webExAccount.getCreateDate();
	}

	/**
	* Sets the create date of this web ex account.
	*
	* @param createDate the create date of this web ex account
	*/
	public void setCreateDate(java.util.Date createDate) {
		_webExAccount.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this web ex account.
	*
	* @return the modified date of this web ex account
	*/
	public java.util.Date getModifiedDate() {
		return _webExAccount.getModifiedDate();
	}

	/**
	* Sets the modified date of this web ex account.
	*
	* @param modifiedDate the modified date of this web ex account
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_webExAccount.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the web ex site ID of this web ex account.
	*
	* @return the web ex site ID of this web ex account
	*/
	public long getWebExSiteId() {
		return _webExAccount.getWebExSiteId();
	}

	/**
	* Sets the web ex site ID of this web ex account.
	*
	* @param webExSiteId the web ex site ID of this web ex account
	*/
	public void setWebExSiteId(long webExSiteId) {
		_webExAccount.setWebExSiteId(webExSiteId);
	}

	/**
	* Returns the login of this web ex account.
	*
	* @return the login of this web ex account
	*/
	public java.lang.String getLogin() {
		return _webExAccount.getLogin();
	}

	/**
	* Sets the login of this web ex account.
	*
	* @param login the login of this web ex account
	*/
	public void setLogin(java.lang.String login) {
		_webExAccount.setLogin(login);
	}

	/**
	* Returns the password of this web ex account.
	*
	* @return the password of this web ex account
	*/
	public java.lang.String getPassword() {
		return _webExAccount.getPassword();
	}

	/**
	* Sets the password of this web ex account.
	*
	* @param password the password of this web ex account
	*/
	public void setPassword(java.lang.String password) {
		_webExAccount.setPassword(password);
	}

	public boolean isNew() {
		return _webExAccount.isNew();
	}

	public void setNew(boolean n) {
		_webExAccount.setNew(n);
	}

	public boolean isCachedModel() {
		return _webExAccount.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_webExAccount.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _webExAccount.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _webExAccount.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_webExAccount.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _webExAccount.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_webExAccount.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new WebExAccountWrapper((WebExAccount)_webExAccount.clone());
	}

	public int compareTo(
		com.liferay.meeting.webex.model.WebExAccount webExAccount) {
		return _webExAccount.compareTo(webExAccount);
	}

	@Override
	public int hashCode() {
		return _webExAccount.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.meeting.webex.model.WebExAccount> toCacheModel() {
		return _webExAccount.toCacheModel();
	}

	public com.liferay.meeting.webex.model.WebExAccount toEscapedModel() {
		return new WebExAccountWrapper(_webExAccount.toEscapedModel());
	}

	public com.liferay.meeting.webex.model.WebExAccount toUnescapedModel() {
		return new WebExAccountWrapper(_webExAccount.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _webExAccount.toString();
	}

	public java.lang.String toXmlString() {
		return _webExAccount.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_webExAccount.persist();
	}

	public com.liferay.meeting.MeetingContext getMeetingContext()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _webExAccount.getMeetingContext();
	}

	public com.liferay.meeting.webex.model.WebExSite getWebExSite()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _webExAccount.getWebExSite();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WebExAccountWrapper)) {
			return false;
		}

		WebExAccountWrapper webExAccountWrapper = (WebExAccountWrapper)obj;

		if (Validator.equals(_webExAccount, webExAccountWrapper._webExAccount)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public WebExAccount getWrappedWebExAccount() {
		return _webExAccount;
	}

	public WebExAccount getWrappedModel() {
		return _webExAccount;
	}

	public void resetOriginalValues() {
		_webExAccount.resetOriginalValues();
	}

	private WebExAccount _webExAccount;
}