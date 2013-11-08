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

package com.liferay.bbb.service.messaging;

import com.liferay.bbb.service.ClpSerializer;
import com.liferay.bbb.service.MeetingEntryLocalServiceUtil;
import com.liferay.bbb.service.MeetingEntryServiceUtil;
import com.liferay.bbb.service.MeetingParticipantLocalServiceUtil;
import com.liferay.bbb.service.MeetingParticipantServiceUtil;
import com.liferay.bbb.service.MeetingServerLocalServiceUtil;
import com.liferay.bbb.service.MeetingServerServiceUtil;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;

/**
 * @author Shinn Lok
 */
public class ClpMessageListener extends BaseMessageListener {
	public static String getServletContextName() {
		return ClpSerializer.getServletContextName();
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		String command = message.getString("command");
		String servletContextName = message.getString("servletContextName");

		if (command.equals("undeploy") &&
				servletContextName.equals(getServletContextName())) {
			MeetingEntryLocalServiceUtil.clearService();

			MeetingEntryServiceUtil.clearService();
			MeetingParticipantLocalServiceUtil.clearService();

			MeetingParticipantServiceUtil.clearService();
			MeetingServerLocalServiceUtil.clearService();

			MeetingServerServiceUtil.clearService();
		}
	}
}