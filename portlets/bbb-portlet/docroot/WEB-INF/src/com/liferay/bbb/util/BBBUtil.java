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

package com.liferay.bbb.util;

import com.liferay.bbb.model.BBBMeeting;
import com.liferay.bbb.model.BBBMeetingConstants;
import com.liferay.bbb.model.BBBParticipant;
import com.liferay.bbb.model.BBBParticipantConstants;
import com.liferay.bbb.model.BBBServer;
import com.liferay.bbb.service.BBBMeetingLocalServiceUtil;
import com.liferay.bbb.service.BBBParticipantLocalServiceUtil;
import com.liferay.bbb.service.BBBServerLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Digester;
import com.liferay.portal.kernel.util.DigesterUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.service.ServiceContext;

import java.io.IOException;

import java.net.HttpURLConnection;
import java.net.URL;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Shinn Lok
 */
public class BBBUtil {

	public static BBBMeeting endMeeting(long bbbMeetingId)
		throws PortalException, SystemException {

		BBBMeeting bbbMeeting = BBBMeetingLocalServiceUtil.getBBBMeeting(
			bbbMeetingId);

		StringBundler sb = new StringBundler(7);

		sb.append(BBBConstants.API_PARAMETER_MEETING_ID);
		sb.append(StringPool.EQUAL);
		sb.append(bbbMeeting.getBbbMeetingId());
		sb.append(StringPool.AMPERSAND);
		sb.append(BBBConstants.API_PARAMETER_PASSWORD);
		sb.append(StringPool.EQUAL);
		sb.append(HtmlUtil.escapeURL(bbbMeeting.getModeratorPassword()));

		execute(bbbMeeting, BBBConstants.API_METHOD_END, sb.toString());

		BBBMeetingLocalServiceUtil.updateStatus(
			bbbMeetingId, BBBMeetingConstants.STATUS_COMPLETED);

		return bbbMeeting;
	}

	public static String getJoinURL(
			BBBParticipant bbbParticipant, String userName)
		throws PortalException, SystemException {

		if (!userName.equals(bbbParticipant.getName())) {
			bbbParticipant = BBBParticipantLocalServiceUtil.addBBBParticipant(
				bbbParticipant.getUserId(), bbbParticipant.getGroupId(),
				bbbParticipant.getBbbMeetingId(), userName, null,
				bbbParticipant.getType(),
				BBBParticipantConstants.STATUS_INVITED, new ServiceContext());
		}

		BBBMeeting bbbMeeting = BBBMeetingLocalServiceUtil.getBBBMeeting(
			bbbParticipant.getBbbMeetingId());

		StringBundler sb = new StringBundler(11);

		sb.append(BBBConstants.API_PARAMETER_FULL_NAME);
		sb.append(StringPool.EQUAL);
		sb.append(HtmlUtil.escapeURL(bbbParticipant.getName()));
		sb.append(StringPool.AMPERSAND);
		sb.append(BBBConstants.API_PARAMETER_MEETING_ID);
		sb.append(StringPool.EQUAL);
		sb.append(bbbMeeting.getBbbMeetingId());
		sb.append(StringPool.AMPERSAND);
		sb.append(BBBConstants.API_PARAMETER_PASSWORD);
		sb.append(StringPool.EQUAL);

		if (bbbParticipant.getType() ==
				BBBParticipantConstants.TYPE_MODERATOR) {

			sb.append(HtmlUtil.escapeURL(bbbMeeting.getModeratorPassword()));
		}
		else {
			sb.append(HtmlUtil.escapeURL(bbbMeeting.getAttendeePassword()));
		}

		return getURL(bbbMeeting, BBBConstants.API_METHOD_JOIN, sb.toString());
	}

	public static boolean isMeetingRunning(long bbbMeetingId) {
		try {
			BBBMeeting bbbMeeting = BBBMeetingLocalServiceUtil.getBBBMeeting(
				bbbMeetingId);

			StringBundler sb = new StringBundler(3);

			sb.append(BBBConstants.API_PARAMETER_MEETING_ID);
			sb.append(StringPool.EQUAL);
			sb.append(bbbMeeting.getBbbMeetingId());

			Document document = execute(
				bbbMeeting, BBBConstants.API_METHOD_IS_MEETING_RUNNING,
				sb.toString());

			Element element = document.getRootElement();

			String running = getText(
				element, BBBConstants.API_RESPONSE_RUNNING);

			if (Validator.isNotNull(running) &&
				GetterUtil.getBoolean(running)) {

				return true;
			}
		}
		catch (Exception e) {
		}

		return false;
	}

	public static BBBMeeting startMeeting(long bbbMeetingId)
		throws PortalException, SystemException {

		BBBMeeting bbbMeeting = BBBMeetingLocalServiceUtil.getBBBMeeting(
			bbbMeetingId);

		bbbMeeting.setBbbServerId(getBBBServerId(bbbMeeting.getGroupId()));

		StringBundler sb = new StringBundler(7);

		sb.append(BBBConstants.API_PARAMETER_MEETING_ID);
		sb.append(StringPool.EQUAL);
		sb.append(bbbMeeting.getBbbMeetingId());
		sb.append(StringPool.AMPERSAND);
		sb.append(BBBConstants.API_PARAMETER_NAME);
		sb.append(StringPool.EQUAL);
		sb.append(HtmlUtil.escapeURL(bbbMeeting.getName()));

		Document document = execute(
			bbbMeeting, BBBConstants.API_METHOD_CREATE, sb.toString());

		Element element = document.getRootElement();

		String returnCode = getText(
			element, BBBConstants.API_RESPONSE_RETURN_CODE);

		if (returnCode.equals(BBBConstants.API_RESPONSE_FAILED)) {
			throw new PortalException();
		}

		bbbMeeting.setAttendeePassword(
			getText(element, BBBConstants.API_PARAMETER_ATTENDEE_PW));
		bbbMeeting.setModeratorPassword(
			getText(element, BBBConstants.API_PARAMETER_MODERATOR_PW));
		bbbMeeting.setStatus(BBBMeetingConstants.STATUS_IN_PROGRESS);

		BBBMeetingLocalServiceUtil.updateBBBMeeting(bbbMeeting);

		return bbbMeeting;
	}

	protected static Document execute(
			BBBMeeting bbbMeeting, String methodName, String parameters)
		throws PortalException, SystemException {

		Document document = null;

		String url = getURL(bbbMeeting, methodName, parameters);

		try {
			URL urlObj = new URL(url);

			HttpURLConnection urlConnection =
				(HttpURLConnection)urlObj.openConnection();

			urlConnection.setConnectTimeout(30000);

			document = SAXReaderUtil.read(urlConnection.getInputStream());
		}
		catch (DocumentException de) {
			throw new PortalException(de);
		}
		catch (IOException ioe) {
			throw new PortalException(ioe);
		}

		return document;
	}

	protected static long getBBBServerId(long groupId) throws SystemException {
		TreeMap<Integer, Long> bbbServersMap = new TreeMap<Integer, Long>();

		List<BBBServer> bbbServers = BBBServerLocalServiceUtil.getBBBServers(
			groupId, true);

		if (bbbServers.isEmpty()) {
			return BBBMeetingConstants.BBB_SERVER_ID_DEFAULT;
		}

		for (BBBServer bbbServer : bbbServers) {
			int count = BBBMeetingLocalServiceUtil.getBBBMeetingsCount(
				bbbServer.getBbbServerId(),
				BBBMeetingConstants.STATUS_IN_PROGRESS);

			bbbServersMap.put(count, bbbServer.getBbbServerId());
		}

		Map.Entry<Integer, Long> entry = bbbServersMap.firstEntry();

		return entry.getValue();
	}

	protected static String getText(Element parentElement, String name) {
		Element element = parentElement.element(name);

		if (element == null) {
			return null;
		}

		return element.getText();
	}

	protected static String getURL(
			BBBMeeting bbbMeeting, String methodName, String parameters)
		throws PortalException, SystemException {

		BBBServer bbbServer = BBBServerLocalServiceUtil.getBBBServer(
			bbbMeeting.getBbbServerId());

		String checksum =
			DigesterUtil.digestHex(
				Digester.SHA_1,
				methodName.concat(parameters).concat(bbbServer.getSecret()));

		StringBundler sb = new StringBundler(6);

		sb.append(bbbServer.getUrl());
		sb.append(methodName);
		sb.append(StringPool.QUESTION);
		sb.append(parameters);
		sb.append("&checksum=");
		sb.append(checksum);

		return sb.toString();
	}

}