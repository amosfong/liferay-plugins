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

import com.liferay.portal.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link WebExSite}.
 * </p>
 *
 * @author Anant Singh
 * @see WebExSite
 * @generated
 */
public class WebExSiteWrapper implements WebExSite, ModelWrapper<WebExSite> {
	public WebExSiteWrapper(WebExSite webExSite) {
		_webExSite = webExSite;
	}

	@Override
	public Class<?> getModelClass() {
		return WebExSite.class;
	}

	@Override
	public String getModelClassName() {
		return WebExSite.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("webExSiteId", getWebExSiteId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("apiURL", getApiURL());
		attributes.put("login", getLogin());
		attributes.put("password", getPassword());
		attributes.put("partnerKey", getPartnerKey());
		attributes.put("siteKey", getSiteKey());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long webExSiteId = (Long)attributes.get("webExSiteId");

		if (webExSiteId != null) {
			setWebExSiteId(webExSiteId);
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

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String apiURL = (String)attributes.get("apiURL");

		if (apiURL != null) {
			setApiURL(apiURL);
		}

		String login = (String)attributes.get("login");

		if (login != null) {
			setLogin(login);
		}

		String password = (String)attributes.get("password");

		if (password != null) {
			setPassword(password);
		}

		String partnerKey = (String)attributes.get("partnerKey");

		if (partnerKey != null) {
			setPartnerKey(partnerKey);
		}

		Long siteKey = (Long)attributes.get("siteKey");

		if (siteKey != null) {
			setSiteKey(siteKey);
		}
	}

	/**
	* Returns the primary key of this web ex site.
	*
	* @return the primary key of this web ex site
	*/
	@Override
	public long getPrimaryKey() {
		return _webExSite.getPrimaryKey();
	}

	/**
	* Sets the primary key of this web ex site.
	*
	* @param primaryKey the primary key of this web ex site
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_webExSite.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this web ex site.
	*
	* @return the uuid of this web ex site
	*/
	@Override
	public java.lang.String getUuid() {
		return _webExSite.getUuid();
	}

	/**
	* Sets the uuid of this web ex site.
	*
	* @param uuid the uuid of this web ex site
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_webExSite.setUuid(uuid);
	}

	/**
	* Returns the web ex site ID of this web ex site.
	*
	* @return the web ex site ID of this web ex site
	*/
	@Override
	public long getWebExSiteId() {
		return _webExSite.getWebExSiteId();
	}

	/**
	* Sets the web ex site ID of this web ex site.
	*
	* @param webExSiteId the web ex site ID of this web ex site
	*/
	@Override
	public void setWebExSiteId(long webExSiteId) {
		_webExSite.setWebExSiteId(webExSiteId);
	}

	/**
	* Returns the group ID of this web ex site.
	*
	* @return the group ID of this web ex site
	*/
	@Override
	public long getGroupId() {
		return _webExSite.getGroupId();
	}

	/**
	* Sets the group ID of this web ex site.
	*
	* @param groupId the group ID of this web ex site
	*/
	@Override
	public void setGroupId(long groupId) {
		_webExSite.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this web ex site.
	*
	* @return the company ID of this web ex site
	*/
	@Override
	public long getCompanyId() {
		return _webExSite.getCompanyId();
	}

	/**
	* Sets the company ID of this web ex site.
	*
	* @param companyId the company ID of this web ex site
	*/
	@Override
	public void setCompanyId(long companyId) {
		_webExSite.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this web ex site.
	*
	* @return the user ID of this web ex site
	*/
	@Override
	public long getUserId() {
		return _webExSite.getUserId();
	}

	/**
	* Sets the user ID of this web ex site.
	*
	* @param userId the user ID of this web ex site
	*/
	@Override
	public void setUserId(long userId) {
		_webExSite.setUserId(userId);
	}

	/**
	* Returns the user uuid of this web ex site.
	*
	* @return the user uuid of this web ex site
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _webExSite.getUserUuid();
	}

	/**
	* Sets the user uuid of this web ex site.
	*
	* @param userUuid the user uuid of this web ex site
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_webExSite.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this web ex site.
	*
	* @return the user name of this web ex site
	*/
	@Override
	public java.lang.String getUserName() {
		return _webExSite.getUserName();
	}

	/**
	* Sets the user name of this web ex site.
	*
	* @param userName the user name of this web ex site
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_webExSite.setUserName(userName);
	}

	/**
	* Returns the create date of this web ex site.
	*
	* @return the create date of this web ex site
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _webExSite.getCreateDate();
	}

	/**
	* Sets the create date of this web ex site.
	*
	* @param createDate the create date of this web ex site
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_webExSite.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this web ex site.
	*
	* @return the modified date of this web ex site
	*/
	@Override
	public java.util.Date getModifiedDate() {
		return _webExSite.getModifiedDate();
	}

	/**
	* Sets the modified date of this web ex site.
	*
	* @param modifiedDate the modified date of this web ex site
	*/
	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_webExSite.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the name of this web ex site.
	*
	* @return the name of this web ex site
	*/
	@Override
	public java.lang.String getName() {
		return _webExSite.getName();
	}

	/**
	* Sets the name of this web ex site.
	*
	* @param name the name of this web ex site
	*/
	@Override
	public void setName(java.lang.String name) {
		_webExSite.setName(name);
	}

	/**
	* Returns the api u r l of this web ex site.
	*
	* @return the api u r l of this web ex site
	*/
	@Override
	public java.lang.String getApiURL() {
		return _webExSite.getApiURL();
	}

	/**
	* Sets the api u r l of this web ex site.
	*
	* @param apiURL the api u r l of this web ex site
	*/
	@Override
	public void setApiURL(java.lang.String apiURL) {
		_webExSite.setApiURL(apiURL);
	}

	/**
	* Returns the login of this web ex site.
	*
	* @return the login of this web ex site
	*/
	@Override
	public java.lang.String getLogin() {
		return _webExSite.getLogin();
	}

	/**
	* Sets the login of this web ex site.
	*
	* @param login the login of this web ex site
	*/
	@Override
	public void setLogin(java.lang.String login) {
		_webExSite.setLogin(login);
	}

	/**
	* Returns the password of this web ex site.
	*
	* @return the password of this web ex site
	*/
	@Override
	public java.lang.String getPassword() {
		return _webExSite.getPassword();
	}

	/**
	* Sets the password of this web ex site.
	*
	* @param password the password of this web ex site
	*/
	@Override
	public void setPassword(java.lang.String password) {
		_webExSite.setPassword(password);
	}

	/**
	* Returns the partner key of this web ex site.
	*
	* @return the partner key of this web ex site
	*/
	@Override
	public java.lang.String getPartnerKey() {
		return _webExSite.getPartnerKey();
	}

	/**
	* Sets the partner key of this web ex site.
	*
	* @param partnerKey the partner key of this web ex site
	*/
	@Override
	public void setPartnerKey(java.lang.String partnerKey) {
		_webExSite.setPartnerKey(partnerKey);
	}

	/**
	* Returns the site key of this web ex site.
	*
	* @return the site key of this web ex site
	*/
	@Override
	public long getSiteKey() {
		return _webExSite.getSiteKey();
	}

	/**
	* Sets the site key of this web ex site.
	*
	* @param siteKey the site key of this web ex site
	*/
	@Override
	public void setSiteKey(long siteKey) {
		_webExSite.setSiteKey(siteKey);
	}

	@Override
	public boolean isNew() {
		return _webExSite.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_webExSite.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _webExSite.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_webExSite.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _webExSite.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _webExSite.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_webExSite.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _webExSite.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_webExSite.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_webExSite.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_webExSite.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new WebExSiteWrapper((WebExSite)_webExSite.clone());
	}

	@Override
	public int compareTo(com.liferay.meeting.webex.model.WebExSite webExSite) {
		return _webExSite.compareTo(webExSite);
	}

	@Override
	public int hashCode() {
		return _webExSite.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.meeting.webex.model.WebExSite> toCacheModel() {
		return _webExSite.toCacheModel();
	}

	@Override
	public com.liferay.meeting.webex.model.WebExSite toEscapedModel() {
		return new WebExSiteWrapper(_webExSite.toEscapedModel());
	}

	@Override
	public com.liferay.meeting.webex.model.WebExSite toUnescapedModel() {
		return new WebExSiteWrapper(_webExSite.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _webExSite.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _webExSite.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_webExSite.persist();
	}

	@Override
	public java.util.List<com.liferay.meeting.webex.model.WebExAccount> getWebExAccounts()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _webExSite.getWebExAccounts();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WebExSiteWrapper)) {
			return false;
		}

		WebExSiteWrapper webExSiteWrapper = (WebExSiteWrapper)obj;

		if (Validator.equals(_webExSite, webExSiteWrapper._webExSite)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _webExSite.getStagedModelType();
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public WebExSite getWrappedWebExSite() {
		return _webExSite;
	}

	@Override
	public WebExSite getWrappedModel() {
		return _webExSite;
	}

	@Override
	public void resetOriginalValues() {
		_webExSite.resetOriginalValues();
	}

	private WebExSite _webExSite;
}