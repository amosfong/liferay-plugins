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
import com.liferay.sharepoint.connector.SharepointResultException;
import com.liferay.sharepoint.connector.schema.XMLHelper;
import com.liferay.sharepoint.connector.schema.batch.Batch;

import com.microsoft.schemas.sharepoint.soap.ListsSoap;
import com.microsoft.schemas.sharepoint.soap.UpdateListItemsResponseUpdateListItemsResult;
import com.microsoft.schemas.sharepoint.soap.UpdateListItemsUpdates;

import java.rmi.RemoteException;

import org.apache.axis.message.MessageElement;

import org.w3c.dom.Element;

/**
 * @author Ivan Zaera
 */
public class BatchOperation extends BaseOperation {

	public BatchOperation(ListsSoap listsSoap, String libraryName) {
		_listsSoap = listsSoap;
		_libraryName = libraryName;
	}

	public void execute(Batch batch) throws SharepointException {
		UpdateListItemsUpdates updateListItemsUpdates = new UpdateListItemsUpdates();

		Element element = _xmlHelper.toElement(batch);

		MessageElement messageElement = new MessageElement(element);

		updateListItemsUpdates.set_any(new MessageElement[] {messageElement});

		UpdateListItemsResponseUpdateListItemsResult
			updateListItemsResponseUpdateListItemsResult = null;

		try {
			updateListItemsResponseUpdateListItemsResult =
				_listsSoap.updateListItems(
					_libraryName, updateListItemsUpdates);
		}
		catch (RemoteException re) {
			throw new SharepointException(
				"Unable to communicate with the Sharepoint server", re);
		}

		_parseUpdateListItemsResponseUpdateListItemsResult(
			updateListItemsResponseUpdateListItemsResult);
	}

	private void _parseUpdateListItemsResponseUpdateListItemsResult(
			UpdateListItemsResponseUpdateListItemsResult
				updateListItemsResponseUpdateListItemsResult)
		throws SharepointException {

		Element updateListItemsResultElement = _xmlHelper.getElement(
			updateListItemsResponseUpdateListItemsResult);

		Element resultElement = _xmlHelper.getElement(
			"Result", updateListItemsResultElement);

		Element errorCodeElement = _xmlHelper.getElement(
			"ErrorCode", resultElement);

		String errorCode = errorCodeElement.getTextContent();

		if (!errorCode.equals(SharepointConstants.ERROR_CODE_SUCCESS)) {
			Element errorTextElement = _xmlHelper.getElement(
				"ErrorText", resultElement);

			String errorText = errorTextElement.getTextContent();

			errorText = errorText.replaceAll(
				StringPool.NEW_LINE, StringPool.PIPE);

			throw new SharepointResultException(errorCode, errorText);
		}
	}

	private static final XMLHelper _xmlHelper = new XMLHelper();

	private String _libraryName;
	private ListsSoap _listsSoap;

}