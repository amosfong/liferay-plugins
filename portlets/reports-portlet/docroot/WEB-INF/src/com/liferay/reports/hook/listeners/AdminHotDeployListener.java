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

package com.liferay.reports.hook.listeners;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.deploy.hot.HotDeployEvent;
import com.liferay.portal.kernel.deploy.hot.HotDeployException;
import com.liferay.portal.kernel.deploy.hot.HotDeployListener;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.DestinationNames;
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
public class AdminHotDeployListener implements HotDeployListener {

	public void invokeDeploy(HotDeployEvent event) throws HotDeployException {
		try {
			doInvokeDeploy(event);
		}
		catch (Throwable t) {
			throw new HotDeployException(t);
		}
	}

	public void invokeUndeploy(HotDeployEvent event) throws HotDeployException {
		try {
			doInvokeUndeploy(event);
		}
		catch (Throwable t) {
			throw new HotDeployException(t);
		}
	}

	protected void doInvokeDeploy(HotDeployEvent event) {
		processSchedulerRequests("resume");
	}

	protected void doInvokeUndeploy(HotDeployEvent event) {
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