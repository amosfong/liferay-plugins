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

package com.liferay.portal.resiliency.spi.util;

import com.liferay.portal.kernel.util.StringPool;

/**
 * @author Michael C. Han
 */
public class SPIAdminConstants {

	public static final String DEFAULT_CONNECTOR_ADDRESS = "localhost";

	public static final String LABEL_STARTED = "started";

	public static final String LABEL_STARTING = "starting";

	public static final String LABEL_STOPPED = "stopped";

	public static final String LABEL_STOPPING = "stopping";

	public static final int STATUS_STARTED = 0;

	public static final int STATUS_STARTING = 1;

	public static final int STATUS_STOPPED = 2;

	public static final int STATUS_STOPPING = 3;

	public static String getStatusCssClass(int status) {
		if (status == STATUS_STOPPED) {
			return "label-important";
		}
		else if ((status == STATUS_STARTING) || (status == STATUS_STOPPING)) {
			return "label-info";
		}
		else if (status == STATUS_STARTED) {
			return "label-success";
		}

		return StringPool.BLANK;
	}

	public static String getStatusLabel(int status) {
		switch (status) {
			case 0:
				return LABEL_STARTED;
			case 1:
				return LABEL_STARTING;
			case 2:
				return LABEL_STOPPED;
			case 3:
				return LABEL_STOPPING;
			default:
				return StringPool.BLANK;
		}
	}

}