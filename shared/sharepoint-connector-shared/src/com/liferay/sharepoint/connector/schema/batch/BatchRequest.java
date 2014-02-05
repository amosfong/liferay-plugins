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

package com.liferay.sharepoint.connector.schema.batch;

import com.liferay.sharepoint.connector.schema.BaseNode;

/**
 * @author Iván Zaera
 */
public class BatchRequest extends BaseNode {

	public BatchRequest(
		OnError onError, String folderPath,
		BatchRequestMethod... batchRequestMethods) {

		_onError = onError;
		_folderPath = folderPath;
		_batchRequestMethods = batchRequestMethods;
	}

	public static enum OnError {

		CONTINUE, RETURN

	}

	private BatchRequestMethod[] _batchRequestMethods;
	private String _folderPath;
	private OnError _onError;

}