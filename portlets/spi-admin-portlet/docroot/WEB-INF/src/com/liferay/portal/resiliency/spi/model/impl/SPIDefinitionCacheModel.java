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

package com.liferay.portal.resiliency.spi.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.resiliency.spi.model.SPIDefinition;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing SPIDefinition in entity cache.
 *
 * @author Michael C. Han
 * @see SPIDefinition
 * @generated
 */
public class SPIDefinitionCacheModel implements CacheModel<SPIDefinition>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{spiDefinitionId=");
		sb.append(spiDefinitionId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", applications=");
		sb.append(applications);
		sb.append(", jvmArguments=");
		sb.append(jvmArguments);
		sb.append(", typeSettings=");
		sb.append(typeSettings);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SPIDefinition toEntityModel() {
		SPIDefinitionImpl spiDefinitionImpl = new SPIDefinitionImpl();

		spiDefinitionImpl.setSpiDefinitionId(spiDefinitionId);
		spiDefinitionImpl.setCompanyId(companyId);
		spiDefinitionImpl.setUserId(userId);

		if (userName == null) {
			spiDefinitionImpl.setUserName(StringPool.BLANK);
		}
		else {
			spiDefinitionImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			spiDefinitionImpl.setCreateDate(null);
		}
		else {
			spiDefinitionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			spiDefinitionImpl.setModifiedDate(null);
		}
		else {
			spiDefinitionImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			spiDefinitionImpl.setName(StringPool.BLANK);
		}
		else {
			spiDefinitionImpl.setName(name);
		}

		if (description == null) {
			spiDefinitionImpl.setDescription(StringPool.BLANK);
		}
		else {
			spiDefinitionImpl.setDescription(description);
		}

		if (applications == null) {
			spiDefinitionImpl.setApplications(StringPool.BLANK);
		}
		else {
			spiDefinitionImpl.setApplications(applications);
		}

		if (jvmArguments == null) {
			spiDefinitionImpl.setJvmArguments(StringPool.BLANK);
		}
		else {
			spiDefinitionImpl.setJvmArguments(jvmArguments);
		}

		if (typeSettings == null) {
			spiDefinitionImpl.setTypeSettings(StringPool.BLANK);
		}
		else {
			spiDefinitionImpl.setTypeSettings(typeSettings);
		}

		spiDefinitionImpl.resetOriginalValues();

		return spiDefinitionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		spiDefinitionId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		name = objectInput.readUTF();
		description = objectInput.readUTF();
		applications = objectInput.readUTF();
		jvmArguments = objectInput.readUTF();
		typeSettings = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(spiDefinitionId);
		objectOutput.writeLong(companyId);
		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}

		if (applications == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(applications);
		}

		if (jvmArguments == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(jvmArguments);
		}

		if (typeSettings == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(typeSettings);
		}
	}

	public long spiDefinitionId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String description;
	public String applications;
	public String jvmArguments;
	public String typeSettings;
}