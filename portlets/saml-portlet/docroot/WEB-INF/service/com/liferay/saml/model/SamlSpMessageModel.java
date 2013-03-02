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

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the SamlSpMessage service. Represents a row in the &quot;SamlSpMessage&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.saml.model.impl.SamlSpMessageModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.saml.model.impl.SamlSpMessageImpl}.
 * </p>
 *
 * @author Mika Koivisto
 * @see SamlSpMessage
 * @see com.liferay.saml.model.impl.SamlSpMessageImpl
 * @see com.liferay.saml.model.impl.SamlSpMessageModelImpl
 * @generated
 */
public interface SamlSpMessageModel extends BaseModel<SamlSpMessage> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a saml sp message model instance should use the {@link SamlSpMessage} interface instead.
	 */

	/**
	 * Returns the primary key of this saml sp message.
	 *
	 * @return the primary key of this saml sp message
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this saml sp message.
	 *
	 * @param primaryKey the primary key of this saml sp message
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the saml sp message ID of this saml sp message.
	 *
	 * @return the saml sp message ID of this saml sp message
	 */
	public long getSamlSpMessageId();

	/**
	 * Sets the saml sp message ID of this saml sp message.
	 *
	 * @param samlSpMessageId the saml sp message ID of this saml sp message
	 */
	public void setSamlSpMessageId(long samlSpMessageId);

	/**
	 * Returns the company ID of this saml sp message.
	 *
	 * @return the company ID of this saml sp message
	 */
	public long getCompanyId();

	/**
	 * Sets the company ID of this saml sp message.
	 *
	 * @param companyId the company ID of this saml sp message
	 */
	public void setCompanyId(long companyId);

	/**
	 * Returns the create date of this saml sp message.
	 *
	 * @return the create date of this saml sp message
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this saml sp message.
	 *
	 * @param createDate the create date of this saml sp message
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Returns the saml idp entity ID of this saml sp message.
	 *
	 * @return the saml idp entity ID of this saml sp message
	 */
	@AutoEscape
	public String getSamlIdpEntityId();

	/**
	 * Sets the saml idp entity ID of this saml sp message.
	 *
	 * @param samlIdpEntityId the saml idp entity ID of this saml sp message
	 */
	public void setSamlIdpEntityId(String samlIdpEntityId);

	/**
	 * Returns the saml idp response key of this saml sp message.
	 *
	 * @return the saml idp response key of this saml sp message
	 */
	@AutoEscape
	public String getSamlIdpResponseKey();

	/**
	 * Sets the saml idp response key of this saml sp message.
	 *
	 * @param samlIdpResponseKey the saml idp response key of this saml sp message
	 */
	public void setSamlIdpResponseKey(String samlIdpResponseKey);

	/**
	 * Returns the expiration date of this saml sp message.
	 *
	 * @return the expiration date of this saml sp message
	 */
	public Date getExpirationDate();

	/**
	 * Sets the expiration date of this saml sp message.
	 *
	 * @param expirationDate the expiration date of this saml sp message
	 */
	public void setExpirationDate(Date expirationDate);

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

	public int compareTo(SamlSpMessage samlSpMessage);

	public int hashCode();

	public CacheModel<SamlSpMessage> toCacheModel();

	public SamlSpMessage toEscapedModel();

	public SamlSpMessage toUnescapedModel();

	public String toString();

	public String toXmlString();
}