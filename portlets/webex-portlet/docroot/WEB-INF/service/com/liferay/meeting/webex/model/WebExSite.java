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

import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the WebExSite service. Represents a row in the &quot;WebEx_WebExSite&quot; database table, with each column mapped to a property of this class.
 *
 * @author Anant Singh
 * @see WebExSiteModel
 * @see com.liferay.meeting.webex.model.impl.WebExSiteImpl
 * @see com.liferay.meeting.webex.model.impl.WebExSiteModelImpl
 * @generated
 */
public interface WebExSite extends WebExSiteModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.meeting.webex.model.impl.WebExSiteImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public java.util.List<com.liferay.meeting.webex.model.WebExAccount> getWebExAccounts()
		throws com.liferay.portal.kernel.exception.SystemException;
}