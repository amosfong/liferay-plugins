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

package com.liferay.bbb.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MeetingServerService}.
 *
 * @author Shinn Lok
 * @see MeetingServerService
 * @generated
 */
public class MeetingServerServiceWrapper implements MeetingServerService,
	ServiceWrapper<MeetingServerService> {
	public MeetingServerServiceWrapper(
		MeetingServerService meetingServerService) {
		_meetingServerService = meetingServerService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _meetingServerService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_meetingServerService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _meetingServerService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public MeetingServerService getWrappedMeetingServerService() {
		return _meetingServerService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedMeetingServerService(
		MeetingServerService meetingServerService) {
		_meetingServerService = meetingServerService;
	}

	@Override
	public MeetingServerService getWrappedService() {
		return _meetingServerService;
	}

	@Override
	public void setWrappedService(MeetingServerService meetingServerService) {
		_meetingServerService = meetingServerService;
	}

	private MeetingServerService _meetingServerService;
}