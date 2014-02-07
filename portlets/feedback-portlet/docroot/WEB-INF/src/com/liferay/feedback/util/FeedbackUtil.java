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

package com.liferay.feedback.util;

import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.util.ContentUtil;
import com.liferay.util.portlet.PortletProps;

/**
 * @author Lin Cui
 */
public class FeedbackUtil {

	public static String getFeedbackBody(String type) throws Exception {

		Filter filter = new Filter(type);

		String templatePath = PortletProps.get(
			PortletPropsKeys.FEEDBACK_BODY, filter);

		return ContentUtil.get(templatePath);
	}

	public static String getFeedbackSubject() throws SystemException {
		String templatePath = PortletProps.get(
			PortletPropsKeys.FEEDBACK_SUBJECT);

		return ContentUtil.get(templatePath);
	}

}