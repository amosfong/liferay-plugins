/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.reports.hook.upgrade;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.reports.hook.upgrade.v1_1_0.UpgradeReportDefinitions;
import com.liferay.reports.hook.upgrade.v1_1_0.UpgradeReportEntries;
import com.liferay.reports.hook.upgrade.v1_1_0.UpgradeReports;

/**
 * @author Wesley Gong
 * @author Calvin Keum
 */
public class UpgradeProcess_1_1_0 extends UpgradeProcess {

	@Override
	public int getThreshold() {
		return 110;
	}

	@Override
	protected void doUpgrade() throws Exception {
		upgrade(UpgradeReports.class);
		upgrade(UpgradeReportDefinitions.class);
		upgrade(UpgradeReportEntries.class);
	}

}