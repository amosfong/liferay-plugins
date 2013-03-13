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
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.model.BaseModelListener;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.util.List;

/**
 * @author Mika Koivisto
 * @author Brian Wing Shun Chan
 */
public class UserListener extends BaseModelListener<User> {

	public void onBeforeCreate(User user) throws ModelListenerException {
		auditOnCreateOrRemove(EventTypes.ADD, user);
	}

	public void onBeforeRemove(User user) throws ModelListenerException {
		auditOnCreateOrRemove(EventTypes.DELETE, user);
	}

	public void onBeforeUpdate(User newUser) throws ModelListenerException {
		try {
			User oldUser = UserLocalServiceUtil.getUser(newUser.getUserId());

			List<Attribute> attributes = getModifiedAttributes(
				newUser, oldUser);

			if (!attributes.isEmpty()) {
				AuditMessage auditMessage =
					AuditMessageBuilder.buildAuditMessage(
						EventTypes.UPDATE, User.class.getName(),
						newUser.getUserId(), attributes);

				AuditRouterUtil.route(auditMessage);
			}
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	protected void auditOnCreateOrRemove(String eventType, User user)
		throws ModelListenerException {

		try {
			AuditMessage auditMessage = AuditMessageBuilder.buildAuditMessage(
				eventType, User.class.getName(), user.getUserId(), null);

			JSONObject additionalInfo = auditMessage.getAdditionalInfo();

			additionalInfo.put("emailAddress", user.getEmailAddress());
			additionalInfo.put("screenName", user.getScreenName());
			additionalInfo.put("userId", user.getUserId());
			additionalInfo.put("userName", user.getFullName());

			AuditRouterUtil.route(auditMessage);
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	protected List<Attribute> getModifiedAttributes(
		User newUser, User oldUser) {

		AttributesBuilder attributesBuilder = new AttributesBuilder(
			newUser, oldUser);

		attributesBuilder.add("active");
		attributesBuilder.add("agreedToTermsOfUse");
		attributesBuilder.add("comments");
		attributesBuilder.add("emailAddress");
		attributesBuilder.add("languageId");
		attributesBuilder.add("reminderQueryAnswer");
		attributesBuilder.add("reminderQueryQuestion");
		attributesBuilder.add("screenName");
		attributesBuilder.add("timeZoneId");

		List<Attribute> attributes = attributesBuilder.getAttributes();

		if (newUser.isPasswordModified()) {
			attributes.add(new Attribute("password"));
		}

		return attributes;
	}

}