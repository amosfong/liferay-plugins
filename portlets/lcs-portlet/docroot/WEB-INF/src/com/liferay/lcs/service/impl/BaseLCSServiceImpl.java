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

package com.liferay.lcs.service.impl;

import com.liferay.lcs.json.BaseJSONWebServiceClientHandler;
import com.liferay.lcs.json.JSONWebServiceClient;
import com.liferay.lcs.json.jabsorb.serializer.MessageSerializer;
import com.liferay.portal.kernel.json.JSONException;

import org.jabsorb.JSONSerializer;
import org.jabsorb.serializer.MarshallException;
import org.jabsorb.serializer.UnmarshallException;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class BaseLCSServiceImpl extends BaseJSONWebServiceClientHandler {

	public BaseLCSServiceImpl() {
		try {
			_jsonSerializer.registerDefaultSerializers();

			_jsonSerializer.registerSerializer(new MessageSerializer());
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public JSONWebServiceClient getJSONWebServiceClient() {
		return _jsonWebServiceClient;
	}

	public void setJSONWebServiceClient(
		JSONWebServiceClient jsonWebServiceClient) {

		_jsonWebServiceClient = jsonWebServiceClient;
	}

	protected Object fromJSON(String json) throws JSONException {
		try {
			return _jsonSerializer.fromJSON(json);
		}
		catch (UnmarshallException ue) {
			throw new JSONException(ue);
		}
	}

	protected String toJSON(Object obj) throws JSONException {
		try {
			return _jsonSerializer.toJSON(obj);
		}
		catch (MarshallException me) {
			throw new JSONException(me);
		}
	}

	private JSONSerializer _jsonSerializer = new JSONSerializer();
	private JSONWebServiceClient _jsonWebServiceClient;

}