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

import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.sharepoint.connector.SharepointConnection;
import com.liferay.sharepoint.connector.SharepointException;
import com.liferay.sharepoint.connector.SharepointResultException;

import com.microsoft.schemas.sharepoint.soap.CopyErrorCode;
import com.microsoft.schemas.sharepoint.soap.CopyResult;
import com.microsoft.schemas.sharepoint.soap.CopySoap;
import com.microsoft.schemas.sharepoint.soap.FieldInformation;
import com.microsoft.schemas.sharepoint.soap.ListsSoap;
import com.microsoft.schemas.sharepoint.soap.holders.CopyResultCollectionHolder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.net.URL;

import java.rmi.RemoteException;

import org.apache.axis.holders.UnsignedIntHolder;

/**
 * @author Ivan Zaera
 */
public class AddOrUpdateFileOperation extends BaseOperation {

	public AddOrUpdateFileOperation(CopySoap copySoap, ListsSoap listsSoap) {
		_copySoap = copySoap;

		_checkInFileOperation = new CheckInFileOperation(listsSoap);
	}

	public void execute(
			String filePath, String changeLog, InputStream inputStream)
		throws SharepointException {

		URL filePathURL = toURL(filePath);

		String[] filePathURLs = new String[] {filePathURL.toString()};

		byte[] inputStreamBytes = _readAndCloseStream(inputStream);

		CopyResultCollectionHolder copyResultCollectionHolder =
			new CopyResultCollectionHolder();

		try {
			_copySoap.copyIntoItems(
				SharepointConstants.URL_SOURCE_NONE, filePathURLs,
				_EMPTY_FIELD_INFORMATIONS, inputStreamBytes,
				new UnsignedIntHolder(), copyResultCollectionHolder);
		}
		catch (RemoteException re) {
			throw new SharepointException(
				"Unable to communicate with the Sharepoint server", re);
		}

		CopyResult copyResult = copyResultCollectionHolder.value[0];

		CopyErrorCode copyErrorCode = copyResult.getErrorCode();

		if (copyErrorCode != CopyErrorCode.Success) {
			throw new SharepointResultException(
				copyErrorCode.toString(), copyResult.getErrorMessage());
		}

		if (changeLog != null) {
			_checkInFileOperation.execute(
				filePath, changeLog, SharepointConnection.CheckInType.MAJOR);
		}
	}

	private byte[] _readAndCloseStream(InputStream inputStream)
		throws SharepointException {

		ByteArrayOutputStream byteArrayOutputStream =
			new ByteArrayOutputStream();

		try {
			StreamUtil.transfer(inputStream, byteArrayOutputStream);
		}
		catch (IOException e) {
			throw new SharepointException("Unable to read stream", e);
		}

		return byteArrayOutputStream.toByteArray();
	}

	private static final FieldInformation[] _EMPTY_FIELD_INFORMATIONS =
		new FieldInformation[] {};

	private CheckInFileOperation _checkInFileOperation;
	private CopySoap _copySoap;

}