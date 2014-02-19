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

import com.liferay.sharepoint.connector.SharepointException;
import com.liferay.sharepoint.connector.schema.batch.Batch;
import com.liferay.sharepoint.connector.schema.batch.BatchField;
import com.liferay.sharepoint.connector.schema.batch.BatchMethod;

import com.microsoft.schemas.sharepoint.soap.ListsSoap;

/**
 * @author Ivan Zaera
 */
public class AddFolderOperation extends BaseOperation {

	public AddFolderOperation(ListsSoap listsSoap, PathHelper pathHelper) {
		_pathHelper = pathHelper;

		_batchOperation = new BatchOperation(
			listsSoap, _pathHelper.getLibraryName());
	}

	public void execute(String folderPath, String folderName)
		throws SharepointException {

		String folderFullPath = _pathHelper.toFullPath(folderPath);

		_batchOperation.execute(
			new Batch(
				Batch.OnError.CONTINUE, folderFullPath,
				new BatchMethod(
						SharepointConstants.BATCH_METHOD_ID_DEFAULT,
						BatchMethod.Command.NEW,
					new BatchField("ID", "New"),
					new BatchField(
						"FSObjType", SharepointConstants.FS_OBJ_TYPE_FOLDER),
					new BatchField("BaseName", folderName))));
	}

	private BatchOperation _batchOperation;
	private PathHelper _pathHelper;

}