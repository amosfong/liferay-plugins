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

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.AuditedModel;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the SamlSpSession service. Represents a row in the &quot;SamlSpSession&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.saml.model.impl.SamlSpSessionModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.saml.model.impl.SamlSpSessionImpl}.
 * </p>
 *
 * @author Mika Koivisto
 * @see SamlSpSession
 * @see com.liferay.saml.model.impl.SamlSpSessionImpl
 * @see com.liferay.saml.model.impl.SamlSpSessionModelImpl
 * @generated
 */
public interface SamlSpSessionModel extends AuditedModel,
	BaseModel<SamlSpSession> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a saml sp session model instance should use the {@link SamlSpSession} interface instead.
	 */

	/**
	 * Returns the primary key of this saml sp session.
	 *
	 * @return the primary key of this saml sp session
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this saml sp session.
	 *
	 * @param primaryKey the primary key of this saml sp session
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the saml sp session ID of this saml sp session.
	 *
	 * @return the saml sp session ID of this saml sp session
	 */
	public long getSamlSpSessionId();

	/**
	 * Sets the saml sp session ID of this saml sp session.
	 *
	 * @param samlSpSessionId the saml sp session ID of this saml sp session
	 */
	public void setSamlSpSessionId(long samlSpSessionId);

	/**
	 * Returns the company ID of this saml sp session.
	 *
	 * @return the company ID of this saml sp session
	 */
	public long getCompanyId();

	/**
	 * Sets the company ID of this saml sp session.
	 *
	 * @param companyId the company ID of this saml sp session
	 */
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this saml sp session.
	 *
	 * @return the user ID of this saml sp session
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this saml sp session.
	 *
	 * @param userId the user ID of this saml sp session
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this saml sp session.
	 *
	 * @return the user uuid of this saml sp session
	 * @throws SystemException if a system exception occurred
	 */
	public String getUserUuid() throws SystemException;

	/**
	 * Sets the user uuid of this saml sp session.
	 *
	 * @param userUuid the user uuid of this saml sp session
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this saml sp session.
	 *
	 * @return the user name of this saml sp session
	 */
	@AutoEscape
	public String getUserName();

	/**
	 * Sets the user name of this saml sp session.
	 *
	 * @param userName the user name of this saml sp session
	 */
	public void setUserName(String userName);

	/**
	 * Returns the create date of this saml sp session.
	 *
	 * @return the create date of this saml sp session
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this saml sp session.
	 *
	 * @param createDate the create date of this saml sp session
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this saml sp session.
	 *
	 * @return the modified date of this saml sp session
	 */
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this saml sp session.
	 *
	 * @param modifiedDate the modified date of this saml sp session
	 */
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the saml sp session key of this saml sp session.
	 *
	 * @return the saml sp session key of this saml sp session
	 */
	@AutoEscape
	public String getSamlSpSessionKey();

	/**
	 * Sets the saml sp session key of this saml sp session.
	 *
	 * @param samlSpSessionKey the saml sp session key of this saml sp session
	 */
	public void setSamlSpSessionKey(String samlSpSessionKey);

	/**
	 * Returns the assertion xml of this saml sp session.
	 *
	 * @return the assertion xml of this saml sp session
	 */
	@AutoEscape
	public String getAssertionXml();

	/**
	 * Sets the assertion xml of this saml sp session.
	 *
	 * @param assertionXml the assertion xml of this saml sp session
	 */
	public void setAssertionXml(String assertionXml);

	/**
	 * Returns the j session ID of this saml sp session.
	 *
	 * @return the j session ID of this saml sp session
	 */
	@AutoEscape
	public String getJSessionId();

	/**
	 * Sets the j session ID of this saml sp session.
	 *
	 * @param jSessionId the j session ID of this saml sp session
	 */
	public void setJSessionId(String jSessionId);

	/**
	 * Returns the name ID format of this saml sp session.
	 *
	 * @return the name ID format of this saml sp session
	 */
	@AutoEscape
	public String getNameIdFormat();

	/**
	 * Sets the name ID format of this saml sp session.
	 *
	 * @param nameIdFormat the name ID format of this saml sp session
	 */
	public void setNameIdFormat(String nameIdFormat);

	/**
	 * Returns the name ID value of this saml sp session.
	 *
	 * @return the name ID value of this saml sp session
	 */
	@AutoEscape
	public String getNameIdValue();

	/**
	 * Sets the name ID value of this saml sp session.
	 *
	 * @param nameIdValue the name ID value of this saml sp session
	 */
	public void setNameIdValue(String nameIdValue);

	/**
	 * Returns the session index of this saml sp session.
	 *
	 * @return the session index of this saml sp session
	 */
	@AutoEscape
	public String getSessionIndex();

	/**
	 * Sets the session index of this saml sp session.
	 *
	 * @param sessionIndex the session index of this saml sp session
	 */
	public void setSessionIndex(String sessionIndex);

	/**
	 * Returns the terminated of this saml sp session.
	 *
	 * @return the terminated of this saml sp session
	 */
	public boolean getTerminated();

	/**
	 * Returns <code>true</code> if this saml sp session is terminated.
	 *
	 * @return <code>true</code> if this saml sp session is terminated; <code>false</code> otherwise
	 */
	public boolean isTerminated();

	/**
	 * Sets whether this saml sp session is terminated.
	 *
	 * @param terminated the terminated of this saml sp session
	 */
	public void setTerminated(boolean terminated);

	public boolean isNew();

	public void setNew(boolean n);

	public boolean isCachedModel();

	public void setCachedModel(boolean cachedModel);

	public boolean isEscapedModel();

	public Serializable getPrimaryKeyObj();

	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	public ExpandoBridge getExpandoBridge();

	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	public Object clone();

	public int compareTo(SamlSpSession samlSpSession);

	public int hashCode();

	public CacheModel<SamlSpSession> toCacheModel();

	public SamlSpSession toEscapedModel();

	public SamlSpSession toUnescapedModel();

	public String toString();

	public String toXmlString();
}