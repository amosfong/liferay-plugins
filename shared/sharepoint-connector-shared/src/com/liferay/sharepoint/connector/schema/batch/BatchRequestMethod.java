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
public class BatchRequestMethod extends BaseNode {

	public BatchRequestMethod(
		int batchRequestMethodId, Command command,
		BatchRequestField... batchRequestFields) {

		_batchRequestMethodId = batchRequestMethodId;
		_command = command;
		_batchRequestFields = batchRequestFields;
	}

	public static enum Command {

		DELETE, NEW, UPDATE

	}

	private BatchRequestField[] _batchRequestFields;
	private int _batchRequestMethodId;
	private Command _command;

}