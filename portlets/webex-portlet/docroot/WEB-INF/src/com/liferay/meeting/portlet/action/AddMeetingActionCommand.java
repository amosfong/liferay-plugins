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

import com.liferay.meeting.Meeting;
import com.liferay.meeting.MeetingContext;
import com.liferay.meeting.MeetingException;
import com.liferay.meeting.MeetingParticipant;
import com.liferay.meeting.webex.MeetingServiceUtil;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.TimeZoneUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.mail.Address;
import javax.mail.internet.InternetAddress;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

/**
 * @author Daniela Zapata Riesco
 * @author Brian Wing Shun Chan
 */
public class AddMeetingActionCommand extends BaseMeetingActionCommand {

	@Override
	protected void doProcessCommand(
			PortletRequest portletRequest, PortletResponse portletResponse)
		throws Exception {

		Meeting meeting = getMeeting(portletRequest);

		MeetingContext meetingContext = getMeetingContext(portletRequest);

		MeetingServiceUtil.addMeeting(meeting, meetingContext);
	}

	protected long getDuration(PortletRequest portletRequest) {
		long durationHour = ParamUtil.getLong(portletRequest, "durationHour");
		long durationMinute = ParamUtil.getLong(
			portletRequest, "durationMinute");

		return (durationHour * 60) + durationMinute;
	}

	protected Meeting getMeeting(PortletRequest portletRequest)
		throws Exception {

		Meeting meeting = new Meeting();

		meeting.addMeetingParticipants(getMeetingParticipants(portletRequest));

		String description = ParamUtil.getString(portletRequest, "description");

		meeting.setDescription(description);

		meeting.setDuration(getDuration(portletRequest));

		String name = ParamUtil.getString(portletRequest, "name");

		meeting.setName(name);

		String password = ParamUtil.getString(portletRequest, "password");

		meeting.setPassword(password);

		meeting.setStartCalendar(getStartCalendar(portletRequest));

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		meeting.setTimeZone(themeDisplay.getTimeZone());

		return meeting;
	}

	protected Set<MeetingParticipant> getMeetingParticipants(
			PortletRequest portletRequest)
		throws Exception {

		Set<MeetingParticipant> meetingParticipants =
			new HashSet<MeetingParticipant>();

		String emailAddresses = ParamUtil.getString(
			portletRequest, "emailAddresses");

		Address[] addresses = parseInternetAddresses(emailAddresses);

		for (Address address : addresses) {
			InternetAddress internetAddress = (InternetAddress)address;

			MeetingParticipant meetingParticipant = new MeetingParticipant();

			meetingParticipant.setEmailAddress(internetAddress.getAddress());

			meetingParticipants.add(meetingParticipant);
		}

		return meetingParticipants;
	}

	protected Calendar getStartCalendar(PortletRequest portletRequest) {
		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		Calendar startCalendar = CalendarFactoryUtil.getCalendar(
			TimeZoneUtil.getTimeZone("GMT"), themeDisplay.getLocale());

		int startDateMonth = ParamUtil.getInteger(
			portletRequest, "startDateMonth");

		startCalendar.set(Calendar.MONTH, startDateMonth);

		int startDateDay = ParamUtil.getInteger(portletRequest, "startDateDay");

		startCalendar.set(Calendar.DATE, startDateDay);

		int startDateYear = ParamUtil.getInteger(
			portletRequest, "startDateYear");

		startCalendar.set(Calendar.YEAR, startDateYear);

		int startDateHour = ParamUtil.getInteger(
			portletRequest, "startDateHour");

		int startDateAmPm = ParamUtil.getInteger(
			portletRequest, "startDateAmPm");

		if (startDateAmPm == Calendar.PM) {
			startDateHour += 12;
		}

		startCalendar.set(Calendar.HOUR_OF_DAY, startDateHour);

		int startDateMinute = ParamUtil.getInteger(
			portletRequest, "startDateMinute");

		startCalendar.set(Calendar.MINUTE, startDateMinute);

		startCalendar.set(Calendar.SECOND, 0);
		startCalendar.set(Calendar.MILLISECOND, 0);

		return startCalendar;
	}

	protected InternetAddress[] parseInternetAddresses(String emailAddresses)
		throws Exception {

		try {
			InternetAddress[] internetAddresses = InternetAddress.parse(
				emailAddresses, true);

			for (InternetAddress internetAddress : internetAddresses) {
				if (!Validator.isEmailAddress(internetAddress.getAddress())) {
					throw new MeetingException(
						MeetingException.INVALID_EMAIL_ADDRESS);
				}
			}

			return internetAddresses;
		}
		catch (MeetingException me) {
			throw me;
		}
		catch (Exception e) {
			throw new MeetingException(MeetingException.INVALID_EMAIL_ADDRESS);
		}
	}

}