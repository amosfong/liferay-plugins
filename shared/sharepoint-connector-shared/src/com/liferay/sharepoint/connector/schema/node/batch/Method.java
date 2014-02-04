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

	public Method(int id, Cmd cmd, Field...fields) {
		_id = id;
		_cmd = cmd;
		_fields = fields;
	}

	public static enum Cmd {Delete, New, Update}

	private final Cmd _cmd;
	private final Field[] _fields;
	private final int _id;

}