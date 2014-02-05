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

package com.liferay.sharepoint.connector.impl;

import com.liferay.sharepoint.connector.exception.SharepointException;

import com.microsoft.schemas.sharepoint.soap.ListsSoap;

import java.net.URL;

import java.rmi.RemoteException;

/**
 * @author Ivan Zaera
 */
public class CancelCheckOutFileOperation {

	public CancelCheckOutFileOperation(
		SharepointConnectionImpl sharepointConnectionImpl) {

		_sharepointConnectionImpl = sharepointConnectionImpl;

		_listsSoap = sharepointConnectionImpl.getListsSoap();
	}

	public boolean execute(String filePath) throws SharepointException {
		try {
			URL fileURL = _sharepointConnectionImpl.getObjectURL(filePath);

			return _listsSoap.undoCheckOut(fileURL.toString());
		}
		catch (RemoteException e) {
			throw new SharepointException(
				"Failure communicating with Sharepoint server", e);
		}
	}

	private ListsSoap _listsSoap;
	private SharepointConnectionImpl _sharepointConnectionImpl;

}