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

package com.liferay.bbb.service.impl;

import com.liferay.bbb.model.BBBParticipant;
import com.liferay.bbb.service.base.BBBParticipantServiceBaseImpl;
import com.liferay.bbb.service.permission.AdminPermission;
import com.liferay.bbb.service.permission.BBBMeetingPermission;
import com.liferay.bbb.util.ActionKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * @author Shinn Lok
 */
public class BBBParticipantServiceImpl extends BBBParticipantServiceBaseImpl {

	@Override
	public BBBParticipant addBBBParticipant(
			long groupId, long meetingEntryId, String name, String emailAddress,
			int type, int status, ServiceContext serviceContext)
		throws PortalException, SystemException {

		AdminPermission.check(
			getPermissionChecker(), groupId, ActionKeys.ADD_PARTICIPANT);

		return bbbParticipantLocalService.addBBBParticipant(
			getUserId(), groupId, meetingEntryId, name, emailAddress, type,
			status, serviceContext);
	}

	@Override
	public BBBParticipant deleteBBBParticipant(BBBParticipant bbbParticipant)
		throws PortalException, SystemException {

		BBBMeetingPermission.check(
			getPermissionChecker(), bbbParticipant.getBbbMeetingId(),
			ActionKeys.UPDATE);

		return bbbParticipantPersistence.remove(bbbParticipant);
	}

	@Override
	public List<BBBParticipant> getBBBParticipants(long meetingEntryId)
		throws PortalException, SystemException {

		BBBMeetingPermission.check(
			getPermissionChecker(), meetingEntryId, ActionKeys.VIEW);

		return bbbParticipantLocalService.getBBBParticipants(meetingEntryId);
	}

	@Override
	public int getBBBParticipantsCount(long meetingEntryId)
		throws PortalException, SystemException {

		BBBMeetingPermission.check(
			getPermissionChecker(), meetingEntryId, ActionKeys.VIEW);

		return bbbParticipantLocalService.getBBBParticipantsCount(
			meetingEntryId);
	}

	@Override
	public BBBParticipant updateBBBParticipant(
			long bbbParticipantId, long meetingEntryId, String name,
			String emailAddress, int type, ServiceContext serviceContext)
		throws PortalException, SystemException {

		BBBMeetingPermission.check(
			getPermissionChecker(), meetingEntryId, ActionKeys.UPDATE);

		return bbbParticipantLocalService.updateBBBParticipant(
			bbbParticipantId, meetingEntryId, name, emailAddress, type,
			serviceContext);
	}

}