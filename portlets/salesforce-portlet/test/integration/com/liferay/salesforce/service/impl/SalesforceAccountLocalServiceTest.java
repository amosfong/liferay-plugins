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

package com.liferay.salesforce.service.impl;

import com.liferay.portal.kernel.dao.orm.ObjectNotFoundException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBatch;
import com.liferay.salesforce.connection.SalesforceConnection;
import com.liferay.salesforce.query.builder.SelectQuery;
import com.liferay.salesforce.util.SalesforceMessageBuilder;
import com.liferay.salesforce.util.SalesforceMessageConstants;
import com.liferay.salesforce.util.SalesforceObjectNames;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

/**
 * @author Michael C. Han
 */
public class SalesforceAccountLocalServiceTest
	extends BaseSalesforceServiceTestCase {

	@Test
	public void testAddAccount() throws Exception {
		Message newAccountMessage = _addAccount();

		String objectId = newAccountMessage.getString(
			SalesforceMessageConstants.SOBJECT_ID);

		List<String> fieldNames = new ArrayList<String>();

		fieldNames.add("Name");
		fieldNames.add("BillingStreet");
		fieldNames.add("BillingCity");
		fieldNames.add("BillingState");
		fieldNames.add("BillingPostalCode");
		fieldNames.add("LastActivityDate");

		Message existingAccountMessage =
			salesforceLocalServiceImpl.executeQuery(
				0, objectId, SalesforceObjectNames.ACCOUNT, fieldNames);

		Assert.assertEquals(
			getAttributeString(existingAccountMessage, "Name"),
			getAttributeString(newAccountMessage, "Name"));
		Assert.assertEquals(
			getAttributeString(existingAccountMessage, "BillingState"),
			getAttributeString(newAccountMessage, "BillingState"));
		Assert.assertEquals(
			getAttributeString(existingAccountMessage, "BillingCity"),
			getAttributeString(newAccountMessage, "BillingCity"));
		Assert.assertEquals(
			getAttributeString(existingAccountMessage, "BillingPostalCode"),
			getAttributeString(newAccountMessage, "BillingPostalCode"));

		_deleteAccount(newAccountMessage);
	}

	@Test
	public void testDeleteAccount() throws Exception {
		Message newAccountMessage = _addAccount();

		String objectId = newAccountMessage.getString(
			SalesforceMessageConstants.SOBJECT_ID);

		_deleteAccount(newAccountMessage);

		List<String> fieldNames = new ArrayList<String>();

		fieldNames.add("Name");
		fieldNames.add("BillingStreet");
		fieldNames.add("BillingCity");
		fieldNames.add("BillingState");
		fieldNames.add("BillingPostalCode");
		fieldNames.add("LastActivityDate");

		try {
			salesforceLocalServiceImpl.executeQuery(
				0, objectId, SalesforceObjectNames.ACCOUNT, fieldNames);

			fail();
		}
		catch (ObjectNotFoundException onfe) {
		}
	}

	@Test
	public void testFindAccountException() throws Exception {
		List<String> fieldNames = new ArrayList<String>();

		fieldNames.add("Id");
		fieldNames.add("BillingStreet");
		fieldNames.add("BillingCity");
		fieldNames.add("BillingState");
		fieldNames.add("BillingCountry");
		fieldNames.add("BillingPostalCode");
		fieldNames.add("LastActivityDate");

		try {
			salesforceLocalServiceImpl.executeQuery(
				0, "00Q7000000PJhAE", SalesforceObjectNames.ACCOUNT,
				fieldNames);

			fail();
		}
		catch (ObjectNotFoundException onfe) {
		}
	}

	@Test
	public void testFindAccounts() throws Exception {
		Message accountMessage1 = _addAccount();
		Message accountMessage2 = _addAccount();

		List<String> fieldNames = new ArrayList<String>();

		fieldNames.add("Id");
		fieldNames.add("BillingStreet");
		fieldNames.add("BillingCity");
		fieldNames.add("BillingState");
		fieldNames.add("BillingCountry");
		fieldNames.add("BillingPostalCode");
		fieldNames.add("LastActivityDate");

		String query = SelectQuery.build(
			SalesforceObjectNames.ACCOUNT, fieldNames, null, null);

		MessageBatch messageBatch = salesforceLocalServiceImpl.executeQuery(
			0, query);

		List<Message> messages = messageBatch.getMessages();

		Assert.assertTrue((messages.size() >= 2));

		_deleteAccount(accountMessage1);
		_deleteAccount(accountMessage2);
	}

	@Test
	public void testUpdateAccount() throws Exception {
		Message newAccountMessage = _addAccount();

		String objectId = newAccountMessage.getString(
			SalesforceMessageConstants.SOBJECT_ID);

		Map<String, Object> payload =
			(Map<String, Object>)newAccountMessage.getPayload();

		String billingCity = (String)payload.get("BillingCity");

		payload.put("BillingCity", "Springfield");

		newAccountMessage.setPayload(payload);

		salesforceLocalServiceImpl.executeUpdate(0, newAccountMessage);

		List<String> fieldNames = new ArrayList<String>();

		fieldNames.add("Id");
		fieldNames.add("Name");
		fieldNames.add("BillingStreet");
		fieldNames.add("BillingCity");
		fieldNames.add("BillingState");
		fieldNames.add("BillingCountry");
		fieldNames.add("BillingPostalCode");
		fieldNames.add("LastActivityDate");

		Message existingAccountMessage =
			salesforceLocalServiceImpl.executeQuery(
				0, objectId, SalesforceObjectNames.ACCOUNT, fieldNames);

		Assert.assertEquals(
			getAttributeString(newAccountMessage, "Name"),
			getAttributeString(existingAccountMessage, "Name"));
		Assert.assertEquals(
			getAttributeString(newAccountMessage, "BillingState"),
			getAttributeString(existingAccountMessage, "BillingState"));
		Assert.assertFalse(
			billingCity.equals(
				getAttributeString(existingAccountMessage, "BillingCity")));
		Assert.assertEquals(
			getAttributeString(
				existingAccountMessage, "BillingCity"), "Springfield");
		Assert.assertEquals(
			getAttributeString(newAccountMessage, "BillingPostalCode"),
			getAttributeString(existingAccountMessage, "BillingPostalCode"));

		_deleteAccount(existingAccountMessage);
	}

	@Test
	public void testUpdateAccountException() throws Exception {
		Message newAccountMessage = _addAccount();

		String objectId = newAccountMessage.getString(
			SalesforceMessageConstants.SOBJECT_ID);

		List<String> fieldNames = new ArrayList<String>();

		fieldNames.add("Id");
		fieldNames.add("BillingStreet");
		fieldNames.add("BillingCity");
		fieldNames.add("BillingState");
		fieldNames.add("BillingCountry");
		fieldNames.add("BillingPostalCode");

		Message existingAccountMessage =
			salesforceLocalServiceImpl.executeQuery(
				0, objectId, SalesforceObjectNames.ACCOUNT, fieldNames);

		newAccountMessage.put(
			SalesforceMessageConstants.SOBJECT_ID, "00Q70000000PJhAE");

		setAttribute(existingAccountMessage, "BillingCity", "Springfield");

		try {
			salesforceLocalServiceImpl.executeUpdate(0, existingAccountMessage);

			fail();
		}
		catch (SystemException se) {
		}

		_deleteAccount(newAccountMessage);
	}

	private Message _addAccount() throws Exception {
		SalesforceMessageBuilder salesforceMessageBuilder =
			new SalesforceMessageBuilder(SalesforceObjectNames.ACCOUNT);

		salesforceMessageBuilder.addAttribute(
			"Name", "Blue Cross Blue Shield of IL");
		salesforceMessageBuilder.addAttribute("BillingCity", "Chicago");
		salesforceMessageBuilder.addAttribute("BillingState", "IL");
		salesforceMessageBuilder.addAttribute("BillingPostalCode", "60601");

		Message message = salesforceMessageBuilder.getMessage();

		String objectId = salesforceLocalServiceImpl.executeAdd(0, message);

		message.put(SalesforceMessageConstants.SOBJECT_ID, objectId);

		return message;
	}

	private void _deleteAccount(Message message) throws Exception {
		SalesforceConnection salesforceConnection =
			salesforceConnectionManager.getSalesforceConnection(0);

		salesforceConnection.delete(
			new String[] {
				message.getString(SalesforceMessageConstants.SOBJECT_ID)
			});
	}

}