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
import com.liferay.portal.model.StagedGroupedModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the WebExSite service. Represents a row in the &quot;WebEx_WebExSite&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.meeting.webex.model.impl.WebExSiteModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.meeting.webex.model.impl.WebExSiteImpl}.
 * </p>
 *
 * @author Anant Singh
 * @see WebExSite
 * @see com.liferay.meeting.webex.model.impl.WebExSiteImpl
 * @see com.liferay.meeting.webex.model.impl.WebExSiteModelImpl
 * @generated
 */
public interface WebExSiteModel extends BaseModel<WebExSite>, StagedGroupedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a web ex site model instance should use the {@link WebExSite} interface instead.
	 */

	/**
	 * Returns the primary key of this web ex site.
	 *
	 * @return the primary key of this web ex site
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this web ex site.
	 *
	 * @param primaryKey the primary key of this web ex site
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this web ex site.
	 *
	 * @return the uuid of this web ex site
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this web ex site.
	 *
	 * @param uuid the uuid of this web ex site
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the web ex site ID of this web ex site.
	 *
	 * @return the web ex site ID of this web ex site
	 */
	public long getWebExSiteId();

	/**
	 * Sets the web ex site ID of this web ex site.
	 *
	 * @param webExSiteId the web ex site ID of this web ex site
	 */
	public void setWebExSiteId(long webExSiteId);

	/**
	 * Returns the group ID of this web ex site.
	 *
	 * @return the group ID of this web ex site
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this web ex site.
	 *
	 * @param groupId the group ID of this web ex site
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this web ex site.
	 *
	 * @return the company ID of this web ex site
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this web ex site.
	 *
	 * @param companyId the company ID of this web ex site
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this web ex site.
	 *
	 * @return the user ID of this web ex site
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this web ex site.
	 *
	 * @param userId the user ID of this web ex site
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this web ex site.
	 *
	 * @return the user uuid of this web ex site
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public String getUserUuid() throws SystemException;

	/**
	 * Sets the user uuid of this web ex site.
	 *
	 * @param userUuid the user uuid of this web ex site
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this web ex site.
	 *
	 * @return the user name of this web ex site
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this web ex site.
	 *
	 * @param userName the user name of this web ex site
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this web ex site.
	 *
	 * @return the create date of this web ex site
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this web ex site.
	 *
	 * @param createDate the create date of this web ex site
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this web ex site.
	 *
	 * @return the modified date of this web ex site
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this web ex site.
	 *
	 * @param modifiedDate the modified date of this web ex site
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the name of this web ex site.
	 *
	 * @return the name of this web ex site
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this web ex site.
	 *
	 * @param name the name of this web ex site
	 */
	public void setName(String name);

	/**
	 * Returns the api u r l of this web ex site.
	 *
	 * @return the api u r l of this web ex site
	 */
	@AutoEscape
	public String getApiURL();

	/**
	 * Sets the api u r l of this web ex site.
	 *
	 * @param apiURL the api u r l of this web ex site
	 */
	public void setApiURL(String apiURL);

	/**
	 * Returns the login of this web ex site.
	 *
	 * @return the login of this web ex site
	 */
	@AutoEscape
	public String getLogin();

	/**
	 * Sets the login of this web ex site.
	 *
	 * @param login the login of this web ex site
	 */
	public void setLogin(String login);

	/**
	 * Returns the password of this web ex site.
	 *
	 * @return the password of this web ex site
	 */
	@AutoEscape
	public String getPassword();

	/**
	 * Sets the password of this web ex site.
	 *
	 * @param password the password of this web ex site
	 */
	public void setPassword(String password);

	/**
	 * Returns the partner key of this web ex site.
	 *
	 * @return the partner key of this web ex site
	 */
	@AutoEscape
	public String getPartnerKey();

	/**
	 * Sets the partner key of this web ex site.
	 *
	 * @param partnerKey the partner key of this web ex site
	 */
	public void setPartnerKey(String partnerKey);

	/**
	 * Returns the site key of this web ex site.
	 *
	 * @return the site key of this web ex site
	 */
	public long getSiteKey();

	/**
	 * Sets the site key of this web ex site.
	 *
	 * @param siteKey the site key of this web ex site
	 */
	public void setSiteKey(long siteKey);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(WebExSite webExSite);

	@Override
	public int hashCode();

	@Override
	public CacheModel<WebExSite> toCacheModel();

	@Override
	public WebExSite toEscapedModel();

	@Override
	public WebExSite toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}