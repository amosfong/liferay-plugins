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

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.GroupedModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the WebExAccount service. Represents a row in the &quot;WebEx_WebExAccount&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.meeting.webex.model.impl.WebExAccountModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.meeting.webex.model.impl.WebExAccountImpl}.
 * </p>
 *
 * @author Anant Singh
 * @see WebExAccount
 * @see com.liferay.meeting.webex.model.impl.WebExAccountImpl
 * @see com.liferay.meeting.webex.model.impl.WebExAccountModelImpl
 * @generated
 */
public interface WebExAccountModel extends BaseModel<WebExAccount>, GroupedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a web ex account model instance should use the {@link WebExAccount} interface instead.
	 */

	/**
	 * Returns the primary key of this web ex account.
	 *
	 * @return the primary key of this web ex account
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this web ex account.
	 *
	 * @param primaryKey the primary key of this web ex account
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this web ex account.
	 *
	 * @return the uuid of this web ex account
	 */
	@AutoEscape
	public String getUuid();

	/**
	 * Sets the uuid of this web ex account.
	 *
	 * @param uuid the uuid of this web ex account
	 */
	public void setUuid(String uuid);

	/**
	 * Returns the web ex account ID of this web ex account.
	 *
	 * @return the web ex account ID of this web ex account
	 */
	public long getWebExAccountId();

	/**
	 * Sets the web ex account ID of this web ex account.
	 *
	 * @param webExAccountId the web ex account ID of this web ex account
	 */
	public void setWebExAccountId(long webExAccountId);

	/**
	 * Returns the group ID of this web ex account.
	 *
	 * @return the group ID of this web ex account
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this web ex account.
	 *
	 * @param groupId the group ID of this web ex account
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this web ex account.
	 *
	 * @return the company ID of this web ex account
	 */
	public long getCompanyId();

	/**
	 * Sets the company ID of this web ex account.
	 *
	 * @param companyId the company ID of this web ex account
	 */
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this web ex account.
	 *
	 * @return the user ID of this web ex account
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this web ex account.
	 *
	 * @param userId the user ID of this web ex account
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this web ex account.
	 *
	 * @return the user uuid of this web ex account
	 * @throws SystemException if a system exception occurred
	 */
	public String getUserUuid() throws SystemException;

	/**
	 * Sets the user uuid of this web ex account.
	 *
	 * @param userUuid the user uuid of this web ex account
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this web ex account.
	 *
	 * @return the user name of this web ex account
	 */
	@AutoEscape
	public String getUserName();

	/**
	 * Sets the user name of this web ex account.
	 *
	 * @param userName the user name of this web ex account
	 */
	public void setUserName(String userName);

	/**
	 * Returns the create date of this web ex account.
	 *
	 * @return the create date of this web ex account
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this web ex account.
	 *
	 * @param createDate the create date of this web ex account
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this web ex account.
	 *
	 * @return the modified date of this web ex account
	 */
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this web ex account.
	 *
	 * @param modifiedDate the modified date of this web ex account
	 */
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the web ex site ID of this web ex account.
	 *
	 * @return the web ex site ID of this web ex account
	 */
	public long getWebExSiteId();

	/**
	 * Sets the web ex site ID of this web ex account.
	 *
	 * @param webExSiteId the web ex site ID of this web ex account
	 */
	public void setWebExSiteId(long webExSiteId);

	/**
	 * Returns the login of this web ex account.
	 *
	 * @return the login of this web ex account
	 */
	@AutoEscape
	public String getLogin();

	/**
	 * Sets the login of this web ex account.
	 *
	 * @param login the login of this web ex account
	 */
	public void setLogin(String login);

	/**
	 * Returns the password of this web ex account.
	 *
	 * @return the password of this web ex account
	 */
	@AutoEscape
	public String getPassword();

	/**
	 * Sets the password of this web ex account.
	 *
	 * @param password the password of this web ex account
	 */
	public void setPassword(String password);

	public boolean isNew();

	public void setNew(boolean n);

	public boolean isCachedModel();

	public void setCachedModel(boolean cachedModel);

	public boolean isEscapedModel();

	public Serializable getPrimaryKeyObj();

	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	public ExpandoBridge getExpandoBridge();

	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	public Object clone();

	public int compareTo(WebExAccount webExAccount);

	public int hashCode();

	public CacheModel<WebExAccount> toCacheModel();

	public WebExAccount toEscapedModel();

	public WebExAccount toUnescapedModel();

	public String toString();

	public String toXmlString();
}