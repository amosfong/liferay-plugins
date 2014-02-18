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

import com.microsoft.schemas.sharepoint.soap.ListsSoap;

/**
 * @author Ivan Zaera
 */
public class DeleteObjectOperation extends BaseOperation {

	public DeleteObjectOperation(ListsSoap listsSoap, PathHelper pathHelper) {
		_pathHelper = pathHelper;

		_batchOperation = new BatchOperation(
			listsSoap, _pathHelper.getLibraryName());

		_getObjectByPathOperation = new GetObjectByPathOperation(
			listsSoap, _pathHelper);
	}

	public void execute(String path) throws SharepointException {
		SharepointObject sharepointObject = _getObjectByPathOperation.execute(
			path);

		if (sharepointObject == null) {
			throw new SharepointException(
				"Unable to find Sharepoint object with path " + path);
		}

		String fullPath = _pathHelper.toFullPath(sharepointObject.getPath());

		_batchOperation.execute(
			new Batch(
				Batch.OnError.CONTINUE, null,
				new BatchMethod(
					SharepointConstants.BATCH_METHOD_ID_DEFAULT,
					BatchMethod.Command.DELETE,
					new BatchField("ID", sharepointObject.getId()),
					new BatchField("FileRef", fullPath))));
	}

	private BatchOperation _batchOperation;
	private GetObjectByPathOperation _getObjectByPathOperation;
	private PathHelper _pathHelper;

}