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

package com.liferay.portal.resiliency.spi.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link SPIDefinition}.
 * </p>
 *
 * @author Michael C. Han
 * @see SPIDefinition
 * @generated
 */
public class SPIDefinitionWrapper implements SPIDefinition,
	ModelWrapper<SPIDefinition> {
	public SPIDefinitionWrapper(SPIDefinition spiDefinition) {
		_spiDefinition = spiDefinition;
	}

	@Override
	public Class<?> getModelClass() {
		return SPIDefinition.class;
	}

	@Override
	public String getModelClassName() {
		return SPIDefinition.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("spiDefinitionId", getSpiDefinitionId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("applications", getApplications());
		attributes.put("jvmArguments", getJvmArguments());
		attributes.put("typeSettings", getTypeSettings());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long spiDefinitionId = (Long)attributes.get("spiDefinitionId");

		if (spiDefinitionId != null) {
			setSpiDefinitionId(spiDefinitionId);
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

		String applications = (String)attributes.get("applications");

		if (applications != null) {
			setApplications(applications);
		}

		String jvmArguments = (String)attributes.get("jvmArguments");

		if (jvmArguments != null) {
			setJvmArguments(jvmArguments);
		}

		String typeSettings = (String)attributes.get("typeSettings");

		if (typeSettings != null) {
			setTypeSettings(typeSettings);
		}
	}

	/**
	* Returns the primary key of this s p i definition.
	*
	* @return the primary key of this s p i definition
	*/
	@Override
	public long getPrimaryKey() {
		return _spiDefinition.getPrimaryKey();
	}

	/**
	* Sets the primary key of this s p i definition.
	*
	* @param primaryKey the primary key of this s p i definition
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_spiDefinition.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the spi definition ID of this s p i definition.
	*
	* @return the spi definition ID of this s p i definition
	*/
	@Override
	public long getSpiDefinitionId() {
		return _spiDefinition.getSpiDefinitionId();
	}

	/**
	* Sets the spi definition ID of this s p i definition.
	*
	* @param spiDefinitionId the spi definition ID of this s p i definition
	*/
	@Override
	public void setSpiDefinitionId(long spiDefinitionId) {
		_spiDefinition.setSpiDefinitionId(spiDefinitionId);
	}

	/**
	* Returns the company ID of this s p i definition.
	*
	* @return the company ID of this s p i definition
	*/
	@Override
	public long getCompanyId() {
		return _spiDefinition.getCompanyId();
	}

	/**
	* Sets the company ID of this s p i definition.
	*
	* @param companyId the company ID of this s p i definition
	*/
	@Override
	public void setCompanyId(long companyId) {
		_spiDefinition.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this s p i definition.
	*
	* @return the user ID of this s p i definition
	*/
	@Override
	public long getUserId() {
		return _spiDefinition.getUserId();
	}

	/**
	* Sets the user ID of this s p i definition.
	*
	* @param userId the user ID of this s p i definition
	*/
	@Override
	public void setUserId(long userId) {
		_spiDefinition.setUserId(userId);
	}

	/**
	* Returns the user uuid of this s p i definition.
	*
	* @return the user uuid of this s p i definition
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _spiDefinition.getUserUuid();
	}

	/**
	* Sets the user uuid of this s p i definition.
	*
	* @param userUuid the user uuid of this s p i definition
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_spiDefinition.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this s p i definition.
	*
	* @return the user name of this s p i definition
	*/
	@Override
	public java.lang.String getUserName() {
		return _spiDefinition.getUserName();
	}

	/**
	* Sets the user name of this s p i definition.
	*
	* @param userName the user name of this s p i definition
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_spiDefinition.setUserName(userName);
	}

	/**
	* Returns the create date of this s p i definition.
	*
	* @return the create date of this s p i definition
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _spiDefinition.getCreateDate();
	}

	/**
	* Sets the create date of this s p i definition.
	*
	* @param createDate the create date of this s p i definition
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_spiDefinition.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this s p i definition.
	*
	* @return the modified date of this s p i definition
	*/
	@Override
	public java.util.Date getModifiedDate() {
		return _spiDefinition.getModifiedDate();
	}

	/**
	* Sets the modified date of this s p i definition.
	*
	* @param modifiedDate the modified date of this s p i definition
	*/
	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_spiDefinition.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the name of this s p i definition.
	*
	* @return the name of this s p i definition
	*/
	@Override
	public java.lang.String getName() {
		return _spiDefinition.getName();
	}

	/**
	* Sets the name of this s p i definition.
	*
	* @param name the name of this s p i definition
	*/
	@Override
	public void setName(java.lang.String name) {
		_spiDefinition.setName(name);
	}

	/**
	* Returns the description of this s p i definition.
	*
	* @return the description of this s p i definition
	*/
	@Override
	public java.lang.String getDescription() {
		return _spiDefinition.getDescription();
	}

	/**
	* Sets the description of this s p i definition.
	*
	* @param description the description of this s p i definition
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_spiDefinition.setDescription(description);
	}

	/**
	* Returns the applications of this s p i definition.
	*
	* @return the applications of this s p i definition
	*/
	@Override
	public java.lang.String getApplications() {
		return _spiDefinition.getApplications();
	}

	/**
	* Sets the applications of this s p i definition.
	*
	* @param applications the applications of this s p i definition
	*/
	@Override
	public void setApplications(java.lang.String applications) {
		_spiDefinition.setApplications(applications);
	}

	/**
	* Returns the jvm arguments of this s p i definition.
	*
	* @return the jvm arguments of this s p i definition
	*/
	@Override
	public java.lang.String getJvmArguments() {
		return _spiDefinition.getJvmArguments();
	}

	/**
	* Sets the jvm arguments of this s p i definition.
	*
	* @param jvmArguments the jvm arguments of this s p i definition
	*/
	@Override
	public void setJvmArguments(java.lang.String jvmArguments) {
		_spiDefinition.setJvmArguments(jvmArguments);
	}

	/**
	* Returns the type settings of this s p i definition.
	*
	* @return the type settings of this s p i definition
	*/
	@Override
	public java.lang.String getTypeSettings() {
		return _spiDefinition.getTypeSettings();
	}

	/**
	* Sets the type settings of this s p i definition.
	*
	* @param typeSettings the type settings of this s p i definition
	*/
	@Override
	public void setTypeSettings(java.lang.String typeSettings) {
		_spiDefinition.setTypeSettings(typeSettings);
	}

	@Override
	public boolean isNew() {
		return _spiDefinition.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_spiDefinition.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _spiDefinition.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_spiDefinition.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _spiDefinition.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _spiDefinition.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_spiDefinition.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _spiDefinition.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_spiDefinition.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_spiDefinition.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_spiDefinition.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new SPIDefinitionWrapper((SPIDefinition)_spiDefinition.clone());
	}

	@Override
	public int compareTo(
		com.liferay.portal.resiliency.spi.model.SPIDefinition spiDefinition) {
		return _spiDefinition.compareTo(spiDefinition);
	}

	@Override
	public int hashCode() {
		return _spiDefinition.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.portal.resiliency.spi.model.SPIDefinition> toCacheModel() {
		return _spiDefinition.toCacheModel();
	}

	@Override
	public com.liferay.portal.resiliency.spi.model.SPIDefinition toEscapedModel() {
		return new SPIDefinitionWrapper(_spiDefinition.toEscapedModel());
	}

	@Override
	public com.liferay.portal.resiliency.spi.model.SPIDefinition toUnescapedModel() {
		return new SPIDefinitionWrapper(_spiDefinition.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _spiDefinition.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _spiDefinition.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_spiDefinition.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SPIDefinitionWrapper)) {
			return false;
		}

		SPIDefinitionWrapper spiDefinitionWrapper = (SPIDefinitionWrapper)obj;

		if (Validator.equals(_spiDefinition, spiDefinitionWrapper._spiDefinition)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public SPIDefinition getWrappedSPIDefinition() {
		return _spiDefinition;
	}

	@Override
	public SPIDefinition getWrappedModel() {
		return _spiDefinition;
	}

	@Override
	public void resetOriginalValues() {
		_spiDefinition.resetOriginalValues();
	}

	private SPIDefinition _spiDefinition;
}