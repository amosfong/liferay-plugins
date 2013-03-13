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
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroup;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.UserGroupLocalServiceUtil;

import java.util.List;

/**
 * @author Mika Koivisto
 * @author Brian Wing Shun Chan
 */
public class UserGroupListener extends BaseModelListener<UserGroup> {

	@Override
	public void onBeforeAddAssociation(
			Object classPK, String associationClassName,
			Object associationClassPK)
		throws ModelListenerException {

		auditOnAddorRemoveAssociation(
			EventTypes.ASSIGN, classPK, associationClassName,
			associationClassPK);
	}

	public void onBeforeCreate(UserGroup userGroup)
		throws ModelListenerException {

		auditOnCreateOrRemove(EventTypes.ADD, userGroup);
	}

	public void onBeforeRemove(UserGroup userGroup)
		throws ModelListenerException {

		auditOnCreateOrRemove(EventTypes.DELETE, userGroup);
	}

	@Override
	public void onBeforeRemoveAssociation(
			Object classPK, String associationClassName,
			Object associationClassPK)
		throws ModelListenerException {

		auditOnAddorRemoveAssociation(
			EventTypes.UNASSIGN, classPK, associationClassName,
			associationClassPK);
	}

	public void onBeforeUpdate(UserGroup newUserGroup)
		throws ModelListenerException {

		try {
			UserGroup oldUserGroup = UserGroupLocalServiceUtil.getUserGroup(
				newUserGroup.getUserGroupId());

			List<Attribute> attributes = getModifiedAttributes(
				newUserGroup, oldUserGroup);

			if (!attributes.isEmpty()) {
				AuditMessage auditMessage =
					AuditMessageBuilder.buildAuditMessage(
						EventTypes.UPDATE, UserGroup.class.getName(),
						newUserGroup.getUserGroupId(), attributes);

				AuditRouterUtil.route(auditMessage);
			}
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	protected void auditOnAddorRemoveAssociation(
			String eventType, Object classPK, String associationClassName,
			Object associationClassPK)
		throws ModelListenerException {

		if (!associationClassName.equals(Group.class.getName()) &&
			!associationClassName.equals(User.class.getName())) {

			return;
		}

		try {
			AuditMessage auditMessage = null;

			if (associationClassName.equals(Group.class.getName())) {
				long groupId = (Long)associationClassPK;

				Group group = GroupLocalServiceUtil.getGroup(groupId);

				auditMessage = AuditMessageBuilder.buildAuditMessage(
					eventType, group.getClassName(), group.getClassPK(), null);
			}
			else {
				auditMessage = AuditMessageBuilder.buildAuditMessage(
					eventType, associationClassName, (Long)associationClassPK,
					null);
			}

			JSONObject additionalInfo = auditMessage.getAdditionalInfo();

			long userGroupId = (Long)classPK;

			additionalInfo.put("userGroupId", userGroupId);

			UserGroup userGroup = UserGroupLocalServiceUtil.getUserGroup(
				userGroupId);

			additionalInfo.put("userGroupName", userGroup.getName());

			AuditRouterUtil.route(auditMessage);
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	protected void auditOnCreateOrRemove(String eventType, UserGroup userGroup)
		throws ModelListenerException {

		try {
			AuditMessage auditMessage = AuditMessageBuilder.buildAuditMessage(
				eventType, UserGroup.class.getName(),
				userGroup.getUserGroupId(), null);

			AuditRouterUtil.route(auditMessage);
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	protected List<Attribute> getModifiedAttributes(
		UserGroup newUserGroup, UserGroup oldUserGroup) {

		AttributesBuilder attributesBuilder = new AttributesBuilder(
			newUserGroup, oldUserGroup);

		attributesBuilder.add("description");
		attributesBuilder.add("name");

		return attributesBuilder.getAttributes();
	}

}