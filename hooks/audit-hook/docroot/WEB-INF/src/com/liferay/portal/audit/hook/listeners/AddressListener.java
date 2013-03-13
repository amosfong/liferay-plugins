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

package com.liferay.portal.audit.hook.listeners;

import com.liferay.portal.ModelListenerException;
import com.liferay.portal.audit.hook.listeners.util.Attribute;
import com.liferay.portal.audit.hook.listeners.util.AttributesBuilder;
import com.liferay.portal.audit.hook.listeners.util.AuditMessageBuilder;
import com.liferay.portal.audit.util.EventTypes;
import com.liferay.portal.kernel.audit.AuditMessage;
import com.liferay.portal.kernel.audit.AuditRouterUtil;
import com.liferay.portal.model.Address;
import com.liferay.portal.model.BaseModelListener;
import com.liferay.portal.model.User;
import com.liferay.portal.service.AddressLocalServiceUtil;

import java.util.List;

/**
 * @author Mika Koivisto
 * @author Brian Wing Shun Chan
 */
public class AddressListener extends BaseModelListener<Address> {

	public void onBeforeUpdate(Address newAddress)
		throws ModelListenerException {

		try {
			String className = newAddress.getClassName();

			if (!className.equals(User.class.getName())) {
				return;
			}

			Address oldAddress = AddressLocalServiceUtil.getAddress(
				newAddress.getAddressId());

			List<Attribute> attributes = getModifiedAttributes(
				newAddress, oldAddress);

			if (!attributes.isEmpty()) {
				AuditMessage auditMessage =
					AuditMessageBuilder.buildAuditMessage(
						EventTypes.UPDATE, User.class.getName(),
						newAddress.getClassPK(), attributes);

				AuditRouterUtil.route(auditMessage);
			}
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	protected List<Attribute> getModifiedAttributes(
		Address newAddress, Address oldAddress) {

		AttributesBuilder attributesBuilder = new AttributesBuilder(
			newAddress, oldAddress);

		attributesBuilder.add("countryId");
		attributesBuilder.add("city");
		attributesBuilder.add("mailing");
		attributesBuilder.add("primary");
		attributesBuilder.add("regionId");
		attributesBuilder.add("street1");
		attributesBuilder.add("street2");
		attributesBuilder.add("street3");
		attributesBuilder.add("typeId");
		attributesBuilder.add("zip");

		return attributesBuilder.getAttributes();
	}

}