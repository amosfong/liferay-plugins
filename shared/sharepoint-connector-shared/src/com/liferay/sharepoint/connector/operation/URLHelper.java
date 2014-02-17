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

package com.liferay.sharepoint.connector.operation;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.sharepoint.connector.SharepointException;
import com.liferay.sharepoint.connector.SharepointRuntimeException;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Iván Zaera
 */
public class URLHelper {

	public URL escapeSharepointURL(URL sharepointURL) {
		String sharepointURLString = sharepointURL.toString();

		String escapedSharepointURLString = sharepointURLString.replaceAll(
			StringPool.SPACE, "%20");

		try {
			return new URL(escapedSharepointURLString);
		}
		catch (MalformedURLException mue) {
			throw new SharepointRuntimeException(
				"Unable to reparse URL '" + escapedSharepointURLString +
					"' after escaping", mue);
		}
	}

	public URL toURL(String spec) throws SharepointException {
		try {
			return new URL(spec);
		}
		catch (MalformedURLException mue) {
			throw new SharepointException(
				"Unable to parse URL '" + spec + "'", mue);
		}
	}

}