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
import com.liferay.sharepoint.connector.SharepointObject;
import com.liferay.sharepoint.connector.schema.batch.Batch;
import com.liferay.sharepoint.connector.schema.batch.BatchField;
import com.liferay.sharepoint.connector.schema.batch.BatchMethod;

import com.microsoft.schemas.sharepoint.soap.CopySoap;
import com.microsoft.schemas.sharepoint.soap.ListsSoap;

import java.net.URL;

/**
 * @author Ivan Zaera
 */
public class MoveObjectOperation extends BaseOperation {

	public MoveObjectOperation(
		CopySoap copySoap, ListsSoap listsSoap, PathHelper pathHelper) {

		_pathHelper = pathHelper;

		_batchOperation = new BatchOperation(
			listsSoap, pathHelper.getLibraryName());
		_copyObjectOperation = new CopyObjectOperation(
			copySoap, listsSoap, pathHelper);
		_deleteObjectOperation = new DeleteObjectOperation(
			listsSoap, pathHelper);
		_getObjectByPathOperation = new GetObjectByPathOperation(
			listsSoap, pathHelper);
	}

	public void execute(String path, String newPath)
		throws SharepointException {

		if (_isRename(path, newPath)) {
			SharepointObject sharepointObject =
				_getObjectByPathOperation.execute(path);

			URL url = sharepointObject.getURL();
			String newName = _pathHelper.getNameWithoutExtension(newPath);
			String newExtension = _pathHelper.getExtension(newPath);

			_batchOperation.execute(
				new Batch(
					Batch.OnError.RETURN, null,
					new BatchMethod(
						SharepointConstants.BATCH_METHOD_ID_DEFAULT,
						BatchMethod.Command.UPDATE,
						new BatchField(
							"ID", Long.toString(sharepointObject.getId())),
						new BatchField(
							"FileRef", url.toString()),
						new BatchField("BaseName", newName),
						new BatchField("File_x0020_Type", newExtension))));
		}
		else {
			_copyObjectOperation.execute(path, newPath);
			_deleteObjectOperation.execute(path);
		}
	}

	private boolean _isRename(String path, String newPath) {
		String parentFolderPath = _pathHelper.getParentFolderPath(path);
		String newParentFolderPath = _pathHelper.getParentFolderPath(newPath);

		return parentFolderPath.equals(newParentFolderPath);
	}

	private BatchOperation _batchOperation;
	private CopyObjectOperation _copyObjectOperation;
	private DeleteObjectOperation _deleteObjectOperation;
	private GetObjectByPathOperation _getObjectByPathOperation;
	private PathHelper _pathHelper;

}