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

import com.liferay.lcs.json.JSONWebServiceClient;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONDeserializer;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.security.auth.PrincipalException;

import java.io.IOException;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.login.CredentialException;

import org.jabsorb.JSONSerializer;
import org.jabsorb.serializer.MarshallException;
import org.jabsorb.serializer.UnmarshallException;

/**
 * @author Ivica Cardic
 */
public class BaseLCSServiceImpl {

	public BaseLCSServiceImpl() {
		try {
			_jsonSerializer.registerDefaultSerializers();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void setJSONWebServiceClient(
		JSONWebServiceClient jsonWebServiceClient) {

		_jsonWebServiceClient = jsonWebServiceClient;
	}

	protected String doGet(String url, String... parametersArray)
		throws SystemException {

		try {
			Map<String, String> parameters = new HashMap<String, String>();

			for (int i = 0; i < parametersArray.length; i += 2) {
				parameters.put(parametersArray[i], parametersArray[i + 1]);
			}

			return _jsonWebServiceClient.doGet(url, parameters);
		}
		catch (CredentialException ce) {
			throw new SystemException(ce);
		}
		catch (IOException ioe) {
			throw new SystemException(ioe);
		}
	}

	protected <T> List<T> doGetToList(
			Class<T> clazz, String url, String... parametersArray)
		throws SystemException {

		String json = doGet(url, parametersArray);

		if ((json == null) || json.equals("{}") || json.equals("[]")) {
			return Collections.emptyList();
		}

		JSONDeserializer<List<T>> jsonDeserializer =
			JSONFactoryUtil.createJSONDeserializer();

		jsonDeserializer.use("values", clazz);

		return jsonDeserializer.deserialize(json);
	}

	protected <T> T doGetToObject(
			Class<T> clazz, String url, String... parametersArray)
		throws SystemException {

		String json = doGet(url, parametersArray);

		if (json.contains("exception")) {
			throw new SystemException(getExceptionMessage(json));
		}

		return JSONFactoryUtil.looseDeserialize(json, clazz);
	}

	protected <T> T doGetToObject(String url, String... parametersArray)
		throws SystemException {

		String json = doGet(url, parametersArray);

		JSONDeserializer<T> jsonDeserializer =
			JSONFactoryUtil.createJSONDeserializer();

		return jsonDeserializer.deserialize(json);
	}

	protected void doPost(String url, String... parametersArray)
		throws PortalException, SystemException {

		try {
			Map<String, String> parameters = new HashMap<String, String>();

			for (int i = 0; i < parametersArray.length; i += 2) {
				parameters.put(parametersArray[i], parametersArray[i + 1]);
			}

			_jsonWebServiceClient.doPost(url, parameters);
		}
		catch (CredentialException e) {
			throw new PrincipalException(e);
		}
		catch (IOException e) {
			throw new SystemException(e);
		}
	}

	protected Object fromJSON(String json) throws JSONException {
		try {
			return _jsonSerializer.fromJSON(json);
		}
		catch (UnmarshallException ue) {
			throw new JSONException(ue);
		}
	}

	protected String getExceptionMessage(String json) {
		int exceptionMessageStart = json.indexOf("exception\":\"") + 12;

		int exceptionMessageEnd = json.indexOf("\"", exceptionMessageStart);

		return json.substring(exceptionMessageStart, exceptionMessageEnd);
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