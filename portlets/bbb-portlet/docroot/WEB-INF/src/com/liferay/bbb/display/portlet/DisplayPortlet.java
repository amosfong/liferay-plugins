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

package com.liferay.bbb.display.portlet;

import com.liferay.bbb.model.BBBMeeting;
import com.liferay.bbb.model.BBBMeetingConstants;
import com.liferay.bbb.model.BBBParticipant;
import com.liferay.bbb.model.BBBParticipantConstants;
import com.liferay.bbb.service.BBBMeetingLocalServiceUtil;
import com.liferay.bbb.service.BBBParticipantLocalServiceUtil;
import com.liferay.bbb.util.BBBAPIUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.DigesterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

/**
 * @author Shinn Lok
 */
public class DisplayPortlet extends MVCPortlet {

	public void joinMeeting(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long bbbParticipantId = ParamUtil.getLong(
			actionRequest, "bbbParticipantId");
		String hash = ParamUtil.getString(actionRequest, "hash");
		String name = ParamUtil.getString(actionRequest, "name");
		boolean recordMeeting = ParamUtil.getBoolean(
			actionRequest, "recordMeeting");

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		BBBParticipant bbbParticipant =
			BBBParticipantLocalServiceUtil.getBBBParticipant(bbbParticipantId);

		if (!hash.equals(
				DigesterUtil.digestHex(bbbParticipant.getEmailAddress()))) {

			jsonObject.put("success", Boolean.FALSE);

			writeJSON(actionRequest, actionResponse, jsonObject);

			return;
		}

		BBBMeeting bbbMeeting = BBBMeetingLocalServiceUtil.getBBBMeeting(
			bbbParticipant.getBbbMeetingId());

		try {
			if ((bbbMeeting.getBbbServerId() ==
					BBBMeetingConstants.BBB_SERVER_ID_DEFAULT) &&
				(bbbParticipant.getType() !=
					BBBParticipantConstants.TYPE_MODERATOR)) {

				jsonObject.put("retry", Boolean.TRUE);
			}
			else {
				if ((bbbParticipant.getType() ==
						BBBParticipantConstants.TYPE_MODERATOR) &&
					!BBBAPIUtil.isMeetingRunning(
						bbbParticipant.getBbbMeetingId())) {

					BBBAPIUtil.startMeeting(
						bbbParticipant.getBbbMeetingId(), recordMeeting);
				}

				String joinURL = BBBAPIUtil.getJoinURL(bbbParticipant, name);

				jsonObject.put("joinURL", joinURL);
				jsonObject.put("success", Boolean.TRUE);
			}
		}
		catch (Exception e) {
			jsonObject.putException(e);
		}

		writeJSON(actionRequest, actionResponse, jsonObject);
	}

}