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

package com.liferay.reports.admin.messaging;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.HotDeployMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelperUtil;
import com.liferay.portal.kernel.scheduler.StorageType;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.reports.service.permission.ActionKeys;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Peter Shin
 */
public class AdminHotDeployListener extends HotDeployMessageListener {

	public AdminHotDeployListener(String... servletContextNames) {
		super(servletContextNames);
	}

	@Override
	protected void onDeploy(Message message) throws Exception {
		processSchedulerRequests("resume");
	}

	@Override
	protected void onUndeploy(Message message) throws Exception {
		processSchedulerRequests("pause");
	}

	protected void processSchedulerRequests(String command) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			ps = con.prepareStatement(
				"select entryId from Reports_Entry where scheduleRequest = ?");

			ps.setBoolean(1, true);

			rs = ps.executeQuery();

			while (rs.next()) {
				long entryId = rs.getLong("entryId");

				String jobName = ActionKeys.ADD_REPORT.concat(
					String.valueOf(entryId));
				String groupName = DestinationNames.REPORT_REQUEST.concat(
					StringPool.SLASH).concat(String.valueOf(entryId));

				if (command.equals("pause")) {
					SchedulerEngineHelperUtil.pause(
						jobName, groupName, StorageType.PERSISTED);
				}
				else if (command.equals("resume")) {
					SchedulerEngineHelperUtil.resume(
						jobName, groupName, StorageType.PERSISTED);
				}
			}
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e.getMessage());
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		AdminHotDeployListener.class);

}