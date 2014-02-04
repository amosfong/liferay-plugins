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

package com.liferay.sharepoint.connector.impl;

import com.liferay.sharepoint.connector.SharepointObject;
import com.liferay.sharepoint.connector.SharepointVersion;
import com.liferay.sharepoint.connector.exception.SharepointException;

import java.io.InputStream;

/**
 * @author Ivan Zaera
 */
public class GetFileContentOp {

	public GetFileContentOp(SharepointConnectionImpl sharepointConnectionImpl) {
	}

	public InputStream run(SharepointObject sharepointObject)
		throws SharepointException {

		return null;
	}

	public InputStream run(SharepointVersion sharepointVersion)
		throws SharepointException {

		return null;
	}

}