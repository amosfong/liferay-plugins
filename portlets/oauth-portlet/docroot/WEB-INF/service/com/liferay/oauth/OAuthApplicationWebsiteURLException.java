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

package com.liferay.oauth;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Ivica Cardic
 */
public class OAuthApplicationWebsiteURLException extends PortalException {

	public OAuthApplicationWebsiteURLException() {
		super();
	}

	public OAuthApplicationWebsiteURLException(String msg) {
		super(msg);
	}

	public OAuthApplicationWebsiteURLException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public OAuthApplicationWebsiteURLException(Throwable cause) {
		super(cause);
	}

}