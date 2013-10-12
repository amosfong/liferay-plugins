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

import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the SPIDefinition service. Represents a row in the &quot;SPIDefinition&quot; database table, with each column mapped to a property of this class.
 *
 * @author Michael C. Han
 * @see SPIDefinitionModel
 * @see com.liferay.portal.resiliency.spi.model.impl.SPIDefinitionImpl
 * @see com.liferay.portal.resiliency.spi.model.impl.SPIDefinitionModelImpl
 * @generated
 */
public interface SPIDefinition extends SPIDefinitionModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.portal.resiliency.spi.model.impl.SPIDefinitionImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public java.lang.String getAgentClassName();

	public java.lang.String getJavaExecutable();

	public long getPingInterval();

	public long getRegisterTimeout();

	public long getShutdownTimeout();

	public com.liferay.portal.kernel.util.UnicodeProperties getTypeSettingsProperties();

	public java.lang.String getTypeSettingsProperty(java.lang.String key);

	public java.lang.String getTypeSettingsProperty(java.lang.String key,
		java.lang.String defaultValue);

	public boolean isAlive();

	public void setTypeSettingsProperties(
		com.liferay.portal.kernel.util.UnicodeProperties typeSettingsProperties);
}