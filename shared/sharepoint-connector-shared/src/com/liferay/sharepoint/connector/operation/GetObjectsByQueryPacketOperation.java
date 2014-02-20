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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.sharepoint.connector.SharepointException;
import com.liferay.sharepoint.connector.SharepointObject;
import com.liferay.sharepoint.connector.SharepointResultException;

import com.microsoft.schemas.sharepoint.soap.ListsSoap;
import com.microsoft.webservices.SharePoint.QueryService.QueryServiceSoap;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Ivan Zaera
 */
public class GetObjectsByQueryPacketOperation extends BaseOperation {

	public GetObjectsByQueryPacketOperation(
		ListsSoap listSoap, QueryServiceSoap queryServiceSoap,
		PathHelper pathHelper, String serviceURL) {

		_queryServiceSoap = queryServiceSoap;

		_getObjectByPathOperation = new GetObjectByPathOperation(
				listSoap, pathHelper);

		_searchPrefix = serviceURL + pathHelper.getLibraryName();

		_searchPrefixLength = _searchPrefix.length();
	}

	public List<SharepointObject> execute(String queryPacket)
		throws SharepointException {

		try {
			String queryServiceSoapResultString = _queryServiceSoap.query(
				queryPacket);

			QueryServiceSoapResult queryServiceSoapResult =
				new QueryServiceSoapResult(queryServiceSoapResultString);

			if (!queryServiceSoapResult.isSuccess()) {
				throw new SharepointResultException(
					queryServiceSoapResult.getStatus(),
					queryServiceSoapResult.getDebugErrorMessage());
			}

			if (queryServiceSoapResult.isEmpty()) {
				return Collections.emptyList();
			}

			List<String> queryServiceSoapResultLinkUrls =
				queryServiceSoapResult.getLinkUrls();

			List<SharepointObject> sharepointObjects =
				new ArrayList<SharepointObject>();

			for (String queryServiceSoapResultLinkUrl :
					queryServiceSoapResultLinkUrls) {

				if (queryServiceSoapResultLinkUrl.startsWith(_searchPrefix)) {
					String sharepointObjectPath =
						queryServiceSoapResultLinkUrl.substring(
							_searchPrefixLength);

					SharepointObject sharepointObject =
						_getObjectByPathOperation.execute(sharepointObjectPath);

					if (sharepointObject == null) {
						if (_log.isWarnEnabled()) {
							_log.warn(
								"Sharepoint object with path " +
								sharepointObjectPath + " ignored");
						}

						continue;
					}

					sharepointObjects.add(sharepointObject);
				}
			}

			return sharepointObjects;
		}
		catch (RemoteException re) {
			throw new SharepointException(
				"Unable to communicate with the Sharepoint server", re);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		GetObjectsByQueryPacketOperation.class);

	private GetObjectByPathOperation _getObjectByPathOperation;
	private QueryServiceSoap _queryServiceSoap;
	private String _searchPrefix;
	private int _searchPrefixLength;

}