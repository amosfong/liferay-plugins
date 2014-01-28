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

package com.liferay.portal.workflow.kaleo.forms.ddm;

import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.workflow.kaleo.forms.service.permission.KaleoFormsPermission;
import com.liferay.portlet.dynamicdatalists.model.DDLRecordSet;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplateConstants;
import com.liferay.portlet.dynamicdatamapping.storage.StorageType;
import com.liferay.portlet.dynamicdatamapping.util.BaseDDMDisplay;

/**
 * @author Marcellus Tavares
 */
public class KaleoFormsDDMDisplay extends BaseDDMDisplay {

	@Override
	public String getPortletId() {
		return "2_WAR_kaleoformsportlet";
	}

	@Override
	public String getResourceName() {
		return KaleoFormsPermission.RESOURCE_NAME;
	}

	@Override
	public String getStorageType() {
		return StorageType.XML.toString();
	}

	@Override
	public String getStructureType() {
		return DDLRecordSet.class.getName();
	}

	@Override
	public String getTemplateMode() {
		return DDMTemplateConstants.TEMPLATE_MODE_CREATE;
	}

	@Override
	public String getTemplateType() {
		return DDMTemplateConstants.TEMPLATE_TYPE_FORM;
	}

	@Override
	public String getViewTemplatesBackURL(
			LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse, long classPK)
		throws Exception {

		return StringPool.BLANK;
	}

}