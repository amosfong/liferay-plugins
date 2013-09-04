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

package com.liferay.meeting.webex.request;

import com.liferay.meeting.MeetingContext;
import com.liferay.meeting.MeetingRequest;

import com.webex.schemas.x2002.x06.service.BodyContentType;
import com.webex.schemas.x2002.x06.service.meeting.GethosturlMeeting;

/**
 * @author Daniela Zapata Riesco
 */
public class GetHostURLMeetingRequestTranslator
	extends BaseMeetingRequestTranslator {

	@Override
	protected BodyContentType getBodyContentType(
		MeetingRequest meetingRequest, MeetingContext meetingContext) {

		GethosturlMeeting gethostUrlMeeting =
			GethosturlMeeting.Factory.newInstance();

		gethostUrlMeeting.setMeetingKey(meetingRequest.getMeetingKey());

		return gethostUrlMeeting;
	}

}