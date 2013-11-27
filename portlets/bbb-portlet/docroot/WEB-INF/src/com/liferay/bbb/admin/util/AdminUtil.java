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

package com.liferay.bbb.admin.util;

import com.liferay.bbb.model.BBBMeeting;
import com.liferay.bbb.model.BBBParticipant;
import com.liferay.bbb.model.BBBParticipantConstants;
import com.liferay.bbb.service.BBBMeetingLocalServiceUtil;
import com.liferay.bbb.service.BBBParticipantLocalServiceUtil;
import com.liferay.bbb.service.BBBParticipantServiceUtil;
import com.liferay.bbb.util.PortletKeys;
import com.liferay.mail.service.MailServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.mail.MailMessage;
import com.liferay.portal.kernel.util.DigesterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutConstants;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.PortletURLFactoryUtil;
import com.liferay.util.ContentUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.mail.internet.InternetAddress;

import javax.portlet.ActionRequest;
import javax.portlet.PortletMode;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Shinn Lok
 */
public class AdminUtil {

	public static List<BBBParticipant> getBBBParticipants(
		ActionRequest actionRequest) {

		String bbbParticipantsIndexesString = actionRequest.getParameter(
			"bbbParticipantIndexes");

		if (bbbParticipantsIndexesString == null) {
			return Collections.emptyList();
		}

		List<BBBParticipant> bbbParticipants = new ArrayList<BBBParticipant>();

		int[] bbbParticipantsIndexes = StringUtil.split(
			bbbParticipantsIndexesString, 0);

		for (int bbbParticipantsIndex : bbbParticipantsIndexes) {
			long bbbParticipantId = ParamUtil.getLong(
				actionRequest, "bbbParticipantId" + bbbParticipantsIndex);

			String name = ParamUtil.getString(
				actionRequest, "bbbParticipantName" + bbbParticipantsIndex);

			String emailAddress = ParamUtil.getString(
				actionRequest,
				"bbbParticipantEmailAddress" + bbbParticipantsIndex);

			if (Validator.isNull(name) && Validator.isNull(emailAddress)) {
				continue;
			}

			int type = ParamUtil.getInteger(
				actionRequest, "bbbParticipantType" + bbbParticipantsIndex);

			BBBParticipant bbbParticipant =
				BBBParticipantLocalServiceUtil.createBBBParticipant(
					bbbParticipantId);

			bbbParticipant.setName(name);
			bbbParticipant.setEmailAddress(emailAddress);
			bbbParticipant.setType(type);

			bbbParticipants.add(bbbParticipant);
		}

		return bbbParticipants;
	}

	public static String getInvitationURL(
			BBBParticipant bbbParticipant, HttpServletRequest request)
		throws Exception {

		Layout layout = LayoutLocalServiceUtil.fetchFirstLayout(
			bbbParticipant.getGroupId(), false,
			LayoutConstants.DEFAULT_PARENT_LAYOUT_ID);

		PortletURL portletURL = PortletURLFactoryUtil.create(
			request, PortletKeys.BBB_DISPLAY, layout.getPlid(),
			PortletRequest.RENDER_PHASE);

		portletURL.setParameter("mvcPath", "/display/view.jsp");

		portletURL.setPortletMode(PortletMode.VIEW);
		portletURL.setWindowState(WindowState.MAXIMIZED);

		portletURL.setParameter(
			"bbbParticipantId",
			String.valueOf(bbbParticipant.getBbbParticipantId()));
		portletURL.setParameter(
			"hash", DigesterUtil.digestHex(bbbParticipant.getEmailAddress()));

		String url = portletURL.toString();

		url = HttpUtil.removeParameter(url, "doAsGroupId");
		url = HttpUtil.removeParameter(url, "refererPlid");

		return HttpUtil.removeParameter(url, "controlPanelCategory");
	}

	public static void sendEmail(
			long bbbMeetingId, ServiceContext serviceContext)
		throws Exception {

		BBBMeeting bbbMeeting = BBBMeetingLocalServiceUtil.getBBBMeeting(
			bbbMeetingId);

		List<BBBParticipant> bbbParticipants =
			BBBParticipantLocalServiceUtil.getBBBParticipants(bbbMeetingId);

		for (BBBParticipant bbbParticipant : bbbParticipants) {
			if (bbbParticipant.getStatus() ==
					BBBParticipantConstants.STATUS_INVITED) {

				continue;
			}

			sendEmail(bbbMeeting, bbbParticipant, serviceContext);

			BBBParticipantLocalServiceUtil.updateStatus(
				bbbParticipant.getBbbParticipantId(),
				BBBParticipantConstants.STATUS_INVITED);
		}
	}

	public static void updateBBBParticipants(
			long groupId, long bbbMeetingId,
			List<BBBParticipant> bbbParticipants)
		throws PortalException, SystemException {

		Set<Long> bbbParticipantIds = new HashSet<Long>();

		for (BBBParticipant bbbParticipant : bbbParticipants) {
			long bbbParticipantId = bbbParticipant.getBbbParticipantId();

			String name = bbbParticipant.getName();
			String emailAddress = bbbParticipant.getEmailAddress();
			int type = bbbParticipant.getType();

			if (bbbParticipantId <= 0) {
				bbbParticipant =
					BBBParticipantServiceUtil.addBBBParticipant(
						groupId, bbbMeetingId, name, emailAddress, type,
						BBBParticipantConstants.STATUS_DEFAULT,
						new ServiceContext());

				bbbParticipantId = bbbParticipant.getBbbParticipantId();
			}
			else {
				BBBParticipantServiceUtil.updateBBBParticipant(
					bbbParticipantId, bbbMeetingId, name, emailAddress, type,
					new ServiceContext());
			}

			bbbParticipantIds.add(bbbParticipantId);
		}

		bbbParticipants = BBBParticipantServiceUtil.getBBBParticipants(
			bbbMeetingId);

		for (BBBParticipant bbbParticipant : bbbParticipants) {
			if (!bbbParticipantIds.contains(
					bbbParticipant.getBbbParticipantId())) {

				BBBParticipantServiceUtil.deleteBBBParticipant(bbbParticipant);
			}
		}
	}

	protected static void sendEmail(
			BBBMeeting bbbMeeting, BBBParticipant bbbParticipant,
			ServiceContext serviceContext)
		throws Exception {

		Company company = CompanyLocalServiceUtil.getCompany(
			serviceContext.getCompanyId());

		InternetAddress from = new InternetAddress(company.getEmailAddress());
		InternetAddress to = new InternetAddress(
			bbbParticipant.getEmailAddress(), bbbParticipant.getName());

		String subject = ContentUtil.get(
			"admin/dependencies/meeting_scheduled_notification_subject.tmpl");

		subject = StringUtil.replace(
			subject,
			new String[] {
				"[$COMPANY_NAME$]", "[$MEETING_NAME$]"
			},
			new String[] {
				company.getName(), bbbMeeting.getName()
			});

		String body = ContentUtil.get(
			"admin/dependencies/meeting_scheduled_notification_body.tmpl");

		body = StringUtil.replace(
			body,
			new String[] {
				"[$MEETING_DESCRIPTION$]", "[$MEETING_NAME$]", "[$MEETING_URL$]"
			},
			new String[] {
				bbbMeeting.getDescription(), bbbMeeting.getName(),
				getInvitationURL(bbbParticipant, serviceContext.getRequest())
			});

		MailMessage mailMessage = new MailMessage(
			from, to, subject, body, true);

		MailServiceUtil.sendEmail(mailMessage);
	}

}