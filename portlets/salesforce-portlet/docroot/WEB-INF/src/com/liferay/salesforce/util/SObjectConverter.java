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

package com.liferay.salesforce.util;

import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBatch;
import com.liferay.portal.kernel.util.Validator;

import com.sforce.soap.partner.QueryResult;
import com.sforce.soap.partner.SearchRecord;
import com.sforce.soap.partner.SearchResult;
import com.sforce.soap.partner.sobject.SObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.soap.Node;

import org.apache.axis.message.MessageElement;

/**
 * @author Michael C. Han
 */
public class SObjectConverter {

	public static List<SObject> convert(List<Message> messages) {
		List<SObject> sObjects = new ArrayList<SObject>(messages.size());

		for (Message message : messages) {
			SObject sObject = convert(message);

			sObjects.add(sObject);
		}

		return sObjects;
	}

	public static SObject convert(Message message) {
		String objectType = message.getString(
			SalesforceMessageConstants.SOBJECT_TYPE);

		SObjectBuilder sObjectBuilder = new SObjectBuilder(objectType);

		Map<String, Object> attributes =
			(Map<String, Object>)message.getPayload();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			sObjectBuilder.addField(entry.getKey(), entry.getValue());
		}

		String[] fieldsToNull = (String[])message.get(
			SalesforceMessageConstants.SOBJECT_FIELDS_TO_NULL);

		if (Validator.isNotNull(fieldsToNull)) {
			sObjectBuilder.setFieldsToNull(fieldsToNull);
		}

		String objectId = message.getString(
			SalesforceMessageConstants.SOBJECT_ID);

		sObjectBuilder.setObjectId(objectId);

		return sObjectBuilder.getSObject();
	}

	public static MessageBatch convert(QueryResult queryResult) {
		return convert(queryResult, 200);
	}

	public static MessageBatch convert(QueryResult queryResult, int batchSize) {
		String queryLocator = null;

		int queryResultSize = queryResult.getSize();

		if (!queryResult.isDone()) {
			queryLocator = queryResult.getQueryLocator();

			queryResultSize = batchSize;
		}

		MessageBatch messageBatch = new MessageBatch(
			queryLocator, queryResultSize);

		for (int i = 0; i < queryResultSize; i++) {
			SObject sObject = queryResult.getRecords(i);

			Message message = convert(sObject);

			messageBatch.addMessage(message);
		}

		return messageBatch;
	}

	public static MessageBatch convert(SearchResult searchResult) {
		SearchRecord[] searchRecords = searchResult.getSearchRecords();

		MessageBatch messageBatch = new MessageBatch(searchRecords.length);

		for (SearchRecord searchRecord : searchRecords) {
			SObject sObject = searchRecord.getRecord();

			Message message = convert(sObject);

			messageBatch.addMessage(message);
		}

		return messageBatch;
	}

	public static Message convert(SObject sObject) {
		Message message = new Message();

		message.put(
			SalesforceMessageConstants.SOBJECT_FIELDS_TO_NULL,
			sObject.getFieldsToNull());
		message.put(SalesforceMessageConstants.SOBJECT_ID, sObject.getId());
		message.put(SalesforceMessageConstants.SOBJECT_TYPE, sObject.getType());

		MessageElement[] messageElements = sObject.get_any();

		Map<String, Object> attributes = new HashMap<String, Object>();

		for (int i = 0; i < messageElements.length; i++) {
			MessageElement messageElement = messageElements[i];

			QName qName = messageElement.getQName();

			String name = qName.getLocalPart();

			Object value = null;

			List<Node> nodes = messageElement.getChildren();

			if (nodes != null) {
				Node node = nodes.get(0);

				value = node.getValue();
			}

			if (Validator.isNull(value)) {
				value = messageElement.getObjectValue();
			}

			attributes.put(name, value);
		}

		message.setPayload(attributes);

		return message;
	}

}