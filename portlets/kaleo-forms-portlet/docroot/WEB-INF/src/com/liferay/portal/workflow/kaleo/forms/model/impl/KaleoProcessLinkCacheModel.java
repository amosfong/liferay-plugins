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

package com.liferay.portal.workflow.kaleo.forms.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink;

import java.io.Serializable;

/**
 * The cache model class for representing KaleoProcessLink in entity cache.
 *
 * @author Marcellus Tavares
 * @see KaleoProcessLink
 * @generated
 */
public class KaleoProcessLinkCacheModel implements CacheModel<KaleoProcessLink>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{kaleoProcessLinkId=");
		sb.append(kaleoProcessLinkId);
		sb.append(", kaleoProcessId=");
		sb.append(kaleoProcessId);
		sb.append(", workflowTaskName=");
		sb.append(workflowTaskName);
		sb.append(", DDMTemplateId=");
		sb.append(DDMTemplateId);
		sb.append("}");

		return sb.toString();
	}

	public KaleoProcessLink toEntityModel() {
		KaleoProcessLinkImpl kaleoProcessLinkImpl = new KaleoProcessLinkImpl();

		kaleoProcessLinkImpl.setKaleoProcessLinkId(kaleoProcessLinkId);
		kaleoProcessLinkImpl.setKaleoProcessId(kaleoProcessId);

		if (workflowTaskName == null) {
			kaleoProcessLinkImpl.setWorkflowTaskName(StringPool.BLANK);
		}
		else {
			kaleoProcessLinkImpl.setWorkflowTaskName(workflowTaskName);
		}

		kaleoProcessLinkImpl.setDDMTemplateId(DDMTemplateId);

		kaleoProcessLinkImpl.resetOriginalValues();

		return kaleoProcessLinkImpl;
	}

	public long kaleoProcessLinkId;
	public long kaleoProcessId;
	public String workflowTaskName;
	public long DDMTemplateId;
}