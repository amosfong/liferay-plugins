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

package com.liferay.meeting.portlet.action;

import com.liferay.meeting.MeetingContext;
import com.liferay.meeting.webex.model.WebExAccount;
import com.liferay.meeting.webex.service.WebExAccountServiceUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.util.bridges.mvc.BaseActionCommand;

import javax.portlet.PortletRequest;

/**
 * @author Brian Wing Shun Chan
 * @author Daniela Zapata Riesco
 */
public abstract class BaseMeetingActionCommand extends BaseActionCommand {

	protected MeetingContext getMeetingContext(PortletRequest portletRequest)
		throws Exception {

		long webExAccountId = ParamUtil.getLong(
			portletRequest, "webExAccountId");

		WebExAccount webExAccount = WebExAccountServiceUtil.getWebExAccount(
			webExAccountId);

		return webExAccount.getMeetingContext();
	}

}