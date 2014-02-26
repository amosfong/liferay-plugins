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

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.sharepoint.connector.SharepointException;
import com.liferay.sharepoint.connector.SharepointObject;
import com.liferay.sharepoint.connector.schema.query.Query;
import com.liferay.sharepoint.connector.schema.query.QueryField;
import com.liferay.sharepoint.connector.schema.query.QueryOptionsList;
import com.liferay.sharepoint.connector.schema.query.QueryValue;
import com.liferay.sharepoint.connector.schema.query.operator.EqOperator;
import com.liferay.sharepoint.connector.schema.query.option.FolderQueryOption;

import com.microsoft.schemas.sharepoint.soap.ListsSoap;

/**
 * @author Ivan Zaera
 */
public class GetObjectByPathOperation extends BaseOperation {

	public GetObjectByPathOperation(
		ListsSoap listsSoap, PathHelper pathHelper) {

		_pathHelper = pathHelper;

		_getObjectsByQueryOperation = new GetObjectsByQueryOperation(
			listsSoap, _pathHelper);
	}

	public SharepointObject execute(String path) throws SharepointException {
		String fullPath = _pathHelper.toFullPath(path);

		Query query = new Query(
			new EqOperator(
				new QueryField("FileRef"),
				new QueryValue(fullPath.substring(1))));

		return getSharepointObject(
			_getObjectsByQueryOperation.execute(
				query,
				new QueryOptionsList(new FolderQueryOption(StringPool.BLANK))));
	}

	private GetObjectsByQueryOperation _getObjectsByQueryOperation;
	private PathHelper _pathHelper;

}