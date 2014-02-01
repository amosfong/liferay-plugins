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

package com.liferay.sharepoint.connector.schema;

import com.liferay.sharepoint.connector.schema.node.batch.Batch;
import com.liferay.sharepoint.connector.schema.node.batch.Batch.OnError;
import com.liferay.sharepoint.connector.schema.node.batch.Field;
import com.liferay.sharepoint.connector.schema.node.batch.Method;

/**
 * @author Iván Zaera
 */
public class BatchBuilder {

	public Batch batch(
		OnError onError, String rootFolderName, Method...methods) {

		return new Batch(onError, rootFolderName, methods);
	}

	public Field field(String name, String value) {
		return new Field(name, value);
	}

	public Method method(int id, Method.Command cmd, Field...fields) {
		return new Method(id, cmd, fields);
	}

}