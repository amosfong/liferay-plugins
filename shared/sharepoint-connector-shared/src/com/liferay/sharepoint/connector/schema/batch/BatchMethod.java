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

package com.liferay.sharepoint.connector.schema.batch;

import com.liferay.portal.kernel.xml.simple.Element;
import com.liferay.sharepoint.connector.schema.BaseNode;

/**
 * @author Iván Zaera
 */
public class BatchMethod extends BaseNode {

	public BatchMethod(
		int batchMethodId, Command command, BatchField... batchFields) {

		_batchMethodId = batchMethodId;
		_command = command;
		_batchFields = batchFields;
	}

	public static enum Command {

		DELETE("Delete"), NEW("New"), UPDATE("Update");

		private Command(String protocolValue) {
			_protocolValue = protocolValue;
		}

		public String getProtocolValue() {
			return _protocolValue;
		}

		private String _protocolValue;

	}

	@Override
	protected String getNodeName() {
		return "Method";
	}

	@Override
	protected void populate(Element element) {
		element.addAttribute("Cmd", _command.getProtocolValue());
		element.addAttribute("ID", String.valueOf(_batchMethodId));

		for (BatchField batchField : _batchFields) {
			batchField.attach(element);
		}
	}

	private BatchField[] _batchFields;
	private int _batchMethodId;
	private Command _command;

}