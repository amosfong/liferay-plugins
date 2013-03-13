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

import com.liferay.portal.kernel.util.Validator;

import com.sforce.soap.partner.sobject.SObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.axis.message.MessageElement;

/**
 * @author Michael C. Han
 */
public class SObjectBuilder {

	public SObjectBuilder(String objectType) {
		this(objectType, 5);
	}

	public SObjectBuilder(String objectType, int fieldsCount) {
		_objectType = objectType;
		_fields = new ArrayList<MessageElement>(fieldsCount);
	}

	public void addField(String fieldName, Object value) {
		MessageElement messageElement = new MessageElement(
			new QName(fieldName), value);

		_fields.add(messageElement);
	}

	public void addFieldToNull(String fieldName) {
		if (_fieldsToNull == null) {
			_fieldsToNull = new ArrayList<String>();
		}

		_fieldsToNull.add(fieldName);
	}

	public String getObjectId() {
		return _objectId;
	}

	public SObject getSObject() {
		SObject sObject = new SObject();

		if (Validator.isNotNull(_objectId)) {
			sObject.setId(_objectId);
		}

		sObject.setType(_objectType);

		sObject.set_any(_fields.toArray(new MessageElement[_fields.size()]));

		if (_fieldsToNull != null) {
			sObject.setFieldsToNull(
				_fieldsToNull.toArray(new String[_fieldsToNull.size()]));
		}

		return sObject;
	}

	public void setFieldsToNull(String[] nullFields) {
		if (_fieldsToNull == null) {
			_fieldsToNull = new ArrayList<String>();
		}

		_fieldsToNull.addAll(Arrays.asList(nullFields));
	}

	public void setObjectId(String objectId) {
		_objectId = objectId;
	}

	private List<MessageElement> _fields;
	private List<String> _fieldsToNull;
	private String _objectId;
	private String _objectType;

}