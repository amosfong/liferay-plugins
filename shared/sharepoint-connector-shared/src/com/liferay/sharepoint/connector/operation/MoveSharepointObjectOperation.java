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

import java.net.URL;

/**
 * @author Ivan Zaera
 */
public class MoveSharepointObjectOperation extends BaseOperation {

	@Override
	public void afterPropertiesSet() {
		_batchOperation = getOperation(BatchOperation.class);
		_copySharepointObjectOperation = getOperation(
			CopySharepointObjectOperation.class);
		_deleteSharepointObjectOperation = getOperation(
			DeleteSharepointObjectOperation.class);
		_getSharepointObjectByPathOperation = getOperation(
			GetSharepointObjectByPathOperation.class);
	}

	public void execute(String path, String newPath)
		throws SharepointException {

		if (isRename(path, newPath)) {
			SharepointObject sharepointObject =
				_getSharepointObjectByPathOperation.execute(path);

			URL url = sharepointObject.getURL();
			String newName = pathHelper.getNameWithoutExtension(newPath);
			String newExtension = pathHelper.getExtension(newPath);

			_batchOperation.execute(
				new Batch(
					Batch.OnError.RETURN, null,
					new BatchMethod(
						SharepointConstants.BATCH_METHOD_ID_DEFAULT,
						BatchMethod.Command.UPDATE,
						new BatchField(
							"ID", sharepointObject.getSharepointObjectId()),
						new BatchField(
							"FileRef", url.toString()),
						new BatchField("BaseName", newName),
						new BatchField("File_x0020_Type", newExtension))));
		}
		else {
			_copySharepointObjectOperation.execute(path, newPath);
			_deleteSharepointObjectOperation.execute(path);
		}
	}

	protected boolean isRename(String path, String newPath) {
		String parentFolderPath = pathHelper.getParentFolderPath(path);
		String newParentFolderPath = pathHelper.getParentFolderPath(newPath);

		return parentFolderPath.equals(newParentFolderPath);
	}

	private BatchOperation _batchOperation;
	private CopySharepointObjectOperation _copySharepointObjectOperation;
	private DeleteSharepointObjectOperation _deleteSharepointObjectOperation;
	private GetSharepointObjectByPathOperation
		_getSharepointObjectByPathOperation;

}