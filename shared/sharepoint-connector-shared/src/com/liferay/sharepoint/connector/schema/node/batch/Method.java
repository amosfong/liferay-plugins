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

package com.liferay.sharepoint.connector.schema.node.batch;

import com.liferay.sharepoint.connector.schema.node.BaseNode;

/**
 * @author Iván Zaera
 */
public class Method extends BaseNode {

	public Method(int id, Command command, Field... fields) {
		_id = id;
		_command = command;
		_fields = fields;
	}

	public static enum Command {

		Delete, New, Update

	}

	private Command _command;
	private Field[] _fields;
	private int _id;

}