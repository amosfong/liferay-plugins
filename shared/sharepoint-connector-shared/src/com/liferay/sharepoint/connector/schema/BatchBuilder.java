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
import com.liferay.sharepoint.connector.schema.node.batch.BatchField;
import com.liferay.sharepoint.connector.schema.node.batch.BatchMethod;

/**
 * @author Iván Zaera
 */
public class BatchBuilder {

	public Batch batch(OnError onError, String folderPath, BatchMethod...methods) {
		return new Batch(onError, folderPath, methods);
	}

	public BatchField field(String name, String value) {
		return new BatchField(name, value);
	}

	public BatchMethod method(int id, BatchMethod.Command command, BatchField...fields) {
		return new BatchMethod(id, command, fields);
	}

}