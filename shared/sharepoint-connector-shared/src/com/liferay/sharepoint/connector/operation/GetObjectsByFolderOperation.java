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

import com.liferay.sharepoint.connector.SharepointConnection.ObjectTypeFilter;
import com.liferay.sharepoint.connector.SharepointConnectionImpl;
import com.liferay.sharepoint.connector.SharepointException;
import com.liferay.sharepoint.connector.SharepointObject;
import com.liferay.sharepoint.connector.schema.query.Query;
import com.liferay.sharepoint.connector.schema.query.QueryField;
import com.liferay.sharepoint.connector.schema.query.QueryOptionsList;
import com.liferay.sharepoint.connector.schema.query.QueryValue;
import com.liferay.sharepoint.connector.schema.query.operator.EqOperator;
import com.liferay.sharepoint.connector.schema.query.option.FolderQueryOption;

import java.util.List;

/**
 * @author Ivan Zaera
 */
public class GetObjectsByFolderOperation extends BaseOperation {

	public GetObjectsByFolderOperation(
		SharepointConnectionImpl sharepointConnectionImpl) {

		super(sharepointConnectionImpl);

		_getObjectsByQueryOperation = new GetObjectsByQueryOperation(
			sharepointConnectionImpl);
	}

	public List<SharepointObject> execute(
			String folderPath, ObjectTypeFilter objectTypeFilter)
		throws SharepointException {

		Query query = null;

		switch (objectTypeFilter) {
			case FILES:
				query = new Query(
					new EqOperator(
						new QueryField("FSObjType"),
						new QueryValue(
							QueryValue.Type.LOOKUP,
							SharepointConstants.FS_OBJ_TYPE_FILE)));

				break;

			case FOLDERS:
				query = new Query(
					new EqOperator(
						new QueryField("FSObjType"),
						new QueryValue(
							QueryValue.Type.LOOKUP,
							SharepointConstants.FS_OBJ_TYPE_FOLDER)));

				break;

			case ALL: {
				query = new Query(null);

				break;
			}

			default: {
				throw new UnsupportedOperationException(
					"Unsupported object type filter: " + objectTypeFilter);
			}
		}

		String fullFolderPath = sharepointConnectionImpl.toFullPath(folderPath);

		QueryOptionsList queryOptionsList = new QueryOptionsList(
			new FolderQueryOption(fullFolderPath));

		return _getObjectsByQueryOperation.execute(query, queryOptionsList);
	}

	private GetObjectsByQueryOperation _getObjectsByQueryOperation;

}