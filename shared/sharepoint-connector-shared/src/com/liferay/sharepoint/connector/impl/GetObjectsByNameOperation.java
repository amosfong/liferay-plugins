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
import com.liferay.sharepoint.connector.exception.SharepointException;

import java.util.List;

/**
 * @author Ivan Zaera
 */
public class GetObjectsByNameOperation {

	public GetObjectsByNameOperation(
		SharepointConnectionImpl sharepointConnectionImpl) {
	}

	public List<SharepointObject> execute(String name)
		throws SharepointException {

		return null;
	}

}