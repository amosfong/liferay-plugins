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
import com.liferay.portal.model.BaseModelListener;
import com.liferay.portal.model.Contact;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ContactLocalServiceUtil;

import java.util.List;

/**
 * @author Mika Koivisto
 * @author Brian Wing Shun Chan
 */
public class ContactListener extends BaseModelListener<Contact> {

	public void onBeforeUpdate(Contact newContact)
		throws ModelListenerException {

		try {
			Contact oldContact = ContactLocalServiceUtil.getContact(
				newContact.getContactId());

			List<Attribute> attributes = getModifiedAttributes(
				newContact, oldContact);

			if (!attributes.isEmpty()) {
				AuditMessage auditMessage =
					AuditMessageBuilder.buildAuditMessage(
						EventTypes.UPDATE, User.class.getName(),
						newContact.getUserId(), attributes);

				AuditRouterUtil.route(auditMessage);
			}
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	protected List<Attribute> getModifiedAttributes(
		Contact newContact, Contact oldContact) {

		AttributesBuilder attributesBuilder = new AttributesBuilder(
			newContact, oldContact);

		attributesBuilder.add("aimSn");
		attributesBuilder.add("birthday");
		attributesBuilder.add("employeeNumber");
		attributesBuilder.add("employeeStatusId");
		attributesBuilder.add("facebookSn");
		attributesBuilder.add("firstName");
		attributesBuilder.add("hoursOfOperation");
		attributesBuilder.add("icqSn");
		attributesBuilder.add("jabberSn");
		attributesBuilder.add("jobClass");
		attributesBuilder.add("jobTitle");
		attributesBuilder.add("lastName");
		attributesBuilder.add("male");
		attributesBuilder.add("middleName");
		attributesBuilder.add("msnSn");
		attributesBuilder.add("mySpaceSn");
		attributesBuilder.add("prefixId");
		attributesBuilder.add("skypeSn");
		attributesBuilder.add("smsSn");
		attributesBuilder.add("suffixId");
		attributesBuilder.add("twitterSn");
		attributesBuilder.add("ymSn");

		return attributesBuilder.getAttributes();
	}

}