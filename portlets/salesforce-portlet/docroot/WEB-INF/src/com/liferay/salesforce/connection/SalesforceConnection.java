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

package com.liferay.salesforce.connection;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;

import com.sforce.soap.partner.AssignmentRuleHeader;
import com.sforce.soap.partner.DeleteResult;
import com.sforce.soap.partner.DescribeGlobalResult;
import com.sforce.soap.partner.DescribeSObjectResult;
import com.sforce.soap.partner.LoginResult;
import com.sforce.soap.partner.QueryOptions;
import com.sforce.soap.partner.QueryResult;
import com.sforce.soap.partner.SaveResult;
import com.sforce.soap.partner.SearchResult;
import com.sforce.soap.partner.SessionHeader;
import com.sforce.soap.partner.SforceServiceLocator;
import com.sforce.soap.partner.SoapBindingStub;
import com.sforce.soap.partner.UpsertResult;
import com.sforce.soap.partner.sobject.SObject;

import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.transport.http.HTTPConstants;

/**
 * @author Michael C. Han
 */
public class SalesforceConnection {

	public SaveResult[] create(SObject[] sObjects) throws SystemException {
		return create(sObjects, _batchSize);
	}

	public SaveResult[] create(SObject[] sObjects, int batchSize)
		throws SystemException {

		validateBatchSize(batchSize);

		return batch(sObjects, batchSize, new CreateBatcher());
	}

	public DeleteResult[] delete(String[] objectIds) throws SystemException {
		login();

		try {
			return _soapBindingStub.delete(objectIds);
		}
		catch (RemoteException re) {
			if (_log.isDebugEnabled()) {
				_log.debug(re, re);
			}

			throw new SystemException(
				"Unable to delete object IDs " + StringUtil.merge(objectIds),
				re);
		}
	}

	public DescribeGlobalResult describeGlobal() throws SystemException {
		login();

		try {
			return _soapBindingStub.describeGlobal();
		}
		catch (RemoteException re) {
			if (_log.isDebugEnabled()) {
				_log.debug(re, re);
			}

			throw new SystemException("Unable to describe global", re);
		}
	}

	public DescribeSObjectResult describeSObject(String objectType)
		throws SystemException {

		login();

		try {
			return _soapBindingStub.describeSObject(objectType);
		}
		catch (RemoteException re) {
			if (_log.isDebugEnabled()) {
				_log.debug(re, re);
			}

			throw new SystemException(
				"Unable to describe object with type " + objectType, re);
		}
	}

	public DescribeSObjectResult[] describeSObjects(String[] objectTypes)
		throws SystemException {

		login();

		try {
			return _soapBindingStub.describeSObjects(objectTypes);
		}
		catch (RemoteException re) {
			if (_log.isDebugEnabled()) {
				_log.debug(re, re);
			}

			throw new SystemException(
				"Unable to describe objects with types " +
					StringUtil.merge(objectTypes),
				re);
		}
	}

	public int getBatchSize() {
		return _batchSize;
	}

	public long getNextLoginTime() {
		return _nextLoginTime;
	}

	public String getPassword() {
		return _password;
	}

	public int getQuerySize() {
		return _querySize;
	}

	public String getServerUrl() {
		return _serverUrl;
	}

	public String getSessionId() {
		return _sessionId;
	}

	public int getSessionTimeout() {
		return _sessionTimeout;
	}

	public String getUserName() {
		return _userName;
	}

	public boolean isUseCompression() {
		return _useCompression;
	}

	public synchronized void login() throws SystemException {
		try {
			if (Validator.isNotNull(_sessionId) &&
				(System.currentTimeMillis() < _nextLoginTime)) {

				return;
			}

			resetSoapBindingStub();

			LoginResult loginResult = _soapBindingStub.login(
				_userName, _password);

			login(loginResult.getSessionId(), loginResult.getServerUrl());
		}
		catch (ServiceException se) {
			if (_log.isDebugEnabled()) {
				_log.debug(se, se);
			}

			throw new SystemException(
				"Unable to communicate with Salesforce.com", se);
		}
		catch (RemoteException re) {
			if (_log.isDebugEnabled()) {
				_log.debug(re, re);
			}

			throw new SystemException("Unable to login", re);
		}
	}

	public void login(String sessionId, String serverUrl)
		throws ServiceException {

		resetSoapBindingStub();

		_nextLoginTime =
			System.currentTimeMillis() + (_sessionTimeout * Time.MINUTE);

		_serverUrl = serverUrl;

		_soapBindingStub._setProperty(
			SoapBindingStub.ENDPOINT_ADDRESS_PROPERTY, serverUrl);

		_sessionId = sessionId;

		SessionHeader sessionHeader = new SessionHeader();

		sessionHeader.setSessionId(sessionId);

		setHeader("SessionHeader", sessionHeader);
	}

	public synchronized void logout() throws SystemException {
		try {
			_soapBindingStub.logout();
		}
		catch (RemoteException re) {
			if (_log.isDebugEnabled()) {
				_log.debug(re, re);
			}

			throw new SystemException("Unable to logout", re);
		}
	}

	public QueryResult query(String queryString) throws SystemException {
		return query(queryString, -1);
	}

	public QueryResult query(String queryString, int batchSize)
		throws SystemException {

		login();

		try {
			if (batchSize == -1) {
				setBatchSizeHeader(_querySize);
			}
			else {
				setBatchSizeHeader(batchSize);
			}

			return _soapBindingStub.query(queryString);
		}
		catch (RemoteException re) {
			if (_log.isDebugEnabled()) {
				_log.debug(re, re);
			}

			throw new SystemException("Unable to query " + queryString, re);
		}
	}

	public QueryResult queryMore(String queryLocator) throws SystemException {
		login();

		try {
			return _soapBindingStub.queryMore(queryLocator);
		}
		catch (RemoteException re) {
			if (_log.isDebugEnabled()) {
				_log.debug(re, re);
			}

			throw new SystemException(
				"Unable to query more " + queryLocator, re);
		}
	}

	public SObject[] retrieve(
			String fieldNames, String objectType, String[] objectIds)
		throws SystemException {

		login();

		try {
			return _soapBindingStub.retrieve(fieldNames, objectType, objectIds);
		}
		catch (RemoteException re) {
			if (_log.isDebugEnabled()) {
				_log.debug(re, re);
			}

			throw new SystemException(
				"Unable to retrieve field names " + fieldNames + " for type " +
					objectType,
				re);
		}
	}

	public SearchResult search(String searchString) throws SystemException {
		try {
			return _soapBindingStub.search(searchString);
		}
		catch (RemoteException re) {
			if (_log.isDebugEnabled()) {
				_log.debug(re, re);
			}

			throw new SystemException("Unable to search " + searchString, re);
		}
	}

	public void setAssignmentRuleId(String assignmentRuleId) {
		setAssignmentRuleHeader(assignmentRuleId, false);
	}

	public void setBatchSize(int batchSize) {
		_batchSize = batchSize;
	}

	public void setNextLoginTime(long nextLoginTime) {
		_nextLoginTime = nextLoginTime;
	}

	public void setPassword(String password) {
		_password = password;
	}

	public void setQuerySize(int querySize) {
		_querySize = querySize;
	}

	public void setServerUrl(String serverUrl) {
		_serverUrl = serverUrl;
	}

	public void setSessionId(String sessionId) {
		_sessionId = sessionId;
	}

	public void setSessionTimeout(int sessionTimeout) {
		_sessionTimeout = sessionTimeout;
	}

	public void setUseCompression(boolean useCompression) {
		_useCompression = useCompression;
	}

	public void setUseDefaultAssignmentRule(boolean useDefaultAssignmentRule) {
		setAssignmentRuleHeader(null, useDefaultAssignmentRule);
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public SaveResult[] update(SObject[] sObjects) throws SystemException {
		return update(sObjects, _batchSize);
	}

	public SaveResult[] update(SObject[] sObjects, int batchSize)
		throws SystemException {

		validateBatchSize(batchSize);

		return batch(sObjects, batchSize, new UpdateBatcher());
	}

	public UpsertResult[] upsert(String externalId, SObject[] sObjects)
		throws SystemException {

		return upsert(externalId, sObjects, _batchSize);
	}

	public UpsertResult[] upsert(
			String externalId, SObject[] sObjects, int batchSize)
		throws SystemException {

		validateBatchSize(batchSize);

		return batch(externalId, sObjects, batchSize, new UpsertBatcher());
	}

	protected SaveResult[] batch(
			SObject[] sObjects, int batchSize, Batcher batcher)
		throws SystemException {

		if (sObjects.length <= batchSize) {
			login();

			return batcher.perform(sObjects);
		}

		SaveResult[] saveResults = new SaveResult[sObjects.length];

		SObject[] curSObjects = null;

		int pos = 0;

		while (pos < sObjects.length) {
			int curBatchSize = Math.min(batchSize, sObjects.length - pos);

			if ((curSObjects == null) || (curSObjects.length != curBatchSize)) {
				curSObjects = new SObject[curBatchSize];
			}

			System.arraycopy(sObjects, pos, curSObjects, 0, curBatchSize);

			SaveResult[] curSaveResults = batch(
				curSObjects, curBatchSize, batcher);

			System.arraycopy(curSaveResults, 0, saveResults, pos, curBatchSize);

			pos += curBatchSize;
		}

		return saveResults;
	}

	protected UpsertResult[] batch(
			String externalId, SObject[] sObjects, int batchSize,
			UpsertBatcher upsertBatcher)
		throws SystemException {

		if (sObjects.length <= batchSize) {
			login();

			return upsertBatcher.perform(externalId, sObjects);
		}

		UpsertResult[] upsertResults = new UpsertResult[sObjects.length];

		SObject[] curSObjects = null;

		int pos = 0;

		while (pos < sObjects.length) {
			int curBatchSize = Math.min(batchSize, sObjects.length - pos);

			if ((curSObjects == null) || (curSObjects.length != curBatchSize)) {
				curSObjects = new SObject[curBatchSize];
			}

			System.arraycopy(sObjects, pos, curSObjects, 0, curBatchSize);

			UpsertResult[] curUpsertResults = batch(
				externalId, curSObjects, curBatchSize, upsertBatcher);

			System.arraycopy(
				curUpsertResults, 0, upsertResults, pos, curBatchSize);

			pos += curBatchSize;
		}

		return upsertResults;
	}

	protected void resetSoapBindingStub() throws ServiceException {
		_soapBindingStub = (SoapBindingStub)_sforceServiceLocator.getSoap();

		_soapBindingStub._setProperty(
			HTTPConstants.MC_ACCEPT_GZIP, _useCompression);
		_soapBindingStub._setProperty(
			HTTPConstants.MC_GZIP_REQUEST, _useCompression);
		_soapBindingStub.setTimeout((int)Time.MINUTE);

		_assignmentRuleHeader = null;
		_queryOptions = null;
	}

	protected void setAssignmentRuleHeader(
		String assignmentRuleId, boolean useDefaultAssignmentRule) {

		if (_assignmentRuleHeader == null) {
			_assignmentRuleHeader = new AssignmentRuleHeader();

			setHeader("AssignmentRuleHeader", _assignmentRuleHeader);
		}

		_assignmentRuleHeader.setAssignmentRuleId(assignmentRuleId);
		_assignmentRuleHeader.setUseDefaultRule(useDefaultAssignmentRule);
	}

	protected void setBatchSizeHeader(int batchSize) {
		if (_queryOptions == null) {
			_queryOptions = new QueryOptions();

			setHeader("QueryOptions", _queryOptions);
		}

		_queryOptions.setBatchSize(batchSize);
	}

	protected void setHeader(String name, Object value) {
		QName qName = _sforceServiceLocator.getServiceName();

		_soapBindingStub.setHeader(qName.getNamespaceURI(), name, value);
	}

	protected void validateBatchSize(int batchSize) {
		if (batchSize < 1) {
			throw new IllegalArgumentException("Batch size is less than 1");
		}

		if (batchSize > _batchSize) {
			throw new IllegalArgumentException(
				"Batch size is larger than " + _batchSize);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(SalesforceConnection.class);

	private AssignmentRuleHeader _assignmentRuleHeader;
	private int _batchSize = 200;
	private long _nextLoginTime;
	private String _password;
	private QueryOptions _queryOptions;
	private int _querySize = 200;
	private String _serverUrl;
	private String _sessionId;
	private int _sessionTimeout = 29;
	private SforceServiceLocator _sforceServiceLocator =
		new SforceServiceLocator();
	private SoapBindingStub _soapBindingStub;
	private boolean _useCompression = true;
	private String _userName;

	private abstract class Batcher {

		public abstract SaveResult[] perform(SObject[] sObjects)
			throws SystemException;

	}

	private class CreateBatcher extends Batcher {

		@Override
		public SaveResult[] perform(SObject[] sObjects) throws SystemException {
			login();

			try {
				return _soapBindingStub.create(sObjects);
			}
			catch (RemoteException re) {
				if (_log.isDebugEnabled()) {
					_log.debug(re, re);
				}

				throw new SystemException("Unable to create batch", re);
			}
		}

	}

	private class UpdateBatcher extends Batcher {

		@Override
		public SaveResult[] perform(SObject[] sObjects) throws SystemException {
			login();

			try {
				return _soapBindingStub.update(sObjects);
			}
			catch (RemoteException re) {
				if (_log.isDebugEnabled()) {
					_log.debug(re, re);
				}

				throw new SystemException("Unable to update batch", re);
			}
		}

	}

	private class UpsertBatcher {

		public UpsertResult[] perform(String externalId, SObject[] sObjects)
			throws SystemException {

			login();

			try {
				return _soapBindingStub.upsert(externalId, sObjects);
			}
			catch (RemoteException re) {
				if (_log.isDebugEnabled()) {
					_log.debug(re, re);
				}

				throw new SystemException("Unable to upsert batch", re);
			}
		}

	}

}