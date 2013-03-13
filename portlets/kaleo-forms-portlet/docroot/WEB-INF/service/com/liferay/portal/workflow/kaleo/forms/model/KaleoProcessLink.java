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

package com.liferay.portal.workflow.kaleo.forms.model;

import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the KaleoProcessLink service. Represents a row in the &quot;KaleoProcessLink&quot; database table, with each column mapped to a property of this class.
 *
 * @author Marcellus Tavares
 * @see KaleoProcessLinkModel
 * @see com.liferay.portal.workflow.kaleo.forms.model.impl.KaleoProcessLinkImpl
 * @see com.liferay.portal.workflow.kaleo.forms.model.impl.KaleoProcessLinkModelImpl
 * @generated
 */
public interface KaleoProcessLink extends KaleoProcessLinkModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.portal.workflow.kaleo.forms.model.impl.KaleoProcessLinkImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess getKaleoProcess()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;
}