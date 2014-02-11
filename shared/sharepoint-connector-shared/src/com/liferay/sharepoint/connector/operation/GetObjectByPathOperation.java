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
public class GetObjectByPathOperation {

	public GetObjectByPathOperation(
		SharepointConnectionImpl sharepointConnectionImpl) {

		_getObjectsByQueryOperation = new GetObjectsByQueryOperation(
			sharepointConnectionImpl);

		_sharepointConnectionImpl = sharepointConnectionImpl;
	}

	public SharepointObject execute(String filePath)
		throws SharepointException {

		String fileFullPath = _sharepointConnectionImpl.toFullPath(filePath);

		Query query = new Query(
			new EqOperator(
				new QueryField("FileRef"),
				new QueryValue(fileFullPath.substring(1))));

		List<SharepointObject> sharepointObjects =
			_getObjectsByQueryOperation.execute(
				query,
				new QueryOptionsList(new FolderQueryOption(StringPool.BLANK)));

		if (sharepointObjects.isEmpty()) {
			return null;
		}

		return sharepointObjects.get(0);
	}

	private GetObjectsByQueryOperation _getObjectsByQueryOperation;
	private SharepointConnectionImpl _sharepointConnectionImpl;

}