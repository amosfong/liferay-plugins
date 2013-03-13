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

package com.liferay.portal.audit.hook.listeners.util;

import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class AttributesBuilder {

	public AttributesBuilder(Object newBean, Object oldBean) {
		_newBean = newBean;
		_oldBean = oldBean;
	}

	public void add(String name) {
		String newValue = String.valueOf(
			BeanPropertiesUtil.getObject(_newBean, name));
		String oldValue = String.valueOf(
			BeanPropertiesUtil.getObject(_oldBean, name));

		if (!Validator.equals(newValue, oldValue)) {
			Attribute attribute = new Attribute(name, newValue, oldValue);

			_attributes.add(attribute);
		}
	}

	public List<Attribute> getAttributes() {
		return _attributes;
	}

	private List<Attribute> _attributes = new ArrayList<Attribute>();
	private Object _newBean;
	private Object _oldBean;

}