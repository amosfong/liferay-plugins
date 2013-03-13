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
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;

import java.util.List;

/**
 * @author Mika Koivisto
 * @author Brian Wing Shun Chan
 */
public class RoleListener extends BaseModelListener<Role> {

	@Override
	public void onBeforeAddAssociation(
			Object classPK, String associationClassName,
			Object associationClassPK)
		throws ModelListenerException {

		auditOnAddorRemoveAssociation(
			EventTypes.ASSIGN, classPK, associationClassName,
			associationClassPK);
	}

	public void onBeforeCreate(Role role) throws ModelListenerException {
		auditOnCreateOrRemove(EventTypes.ADD, role);
	}

	public void onBeforeRemove(Role role) throws ModelListenerException {
		auditOnCreateOrRemove(EventTypes.DELETE, role);
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

	public void onBeforeUpdate(Role newRole) throws ModelListenerException {
		try {
			Role oldRole = RoleLocalServiceUtil.getRole(newRole.getRoleId());

			List<Attribute> attributes = getModifiedAttributes(
				newRole, oldRole);

			if (!attributes.isEmpty()) {
				AuditMessage auditMessage =
					AuditMessageBuilder.buildAuditMessage(
						EventTypes.UPDATE, Role.class.getName(),
						newRole.getRoleId(), attributes);

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
			!associationClassName.equals(Organization.class.getName()) &&
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

			long roleId = (Long)classPK;

			additionalInfo.put("roleId", roleId);

			Role role = RoleLocalServiceUtil.getRole(roleId);

			additionalInfo.put("roleName", role.getName());

			AuditRouterUtil.route(auditMessage);
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	protected void auditOnCreateOrRemove(String eventType, Role role)
		throws ModelListenerException {

		try {
			AuditMessage auditMessage = AuditMessageBuilder.buildAuditMessage(
				eventType, Role.class.getName(), role.getRoleId(), null);

			AuditRouterUtil.route(auditMessage);
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	protected List<Attribute> getModifiedAttributes(
		Role newRole, Role oldRole) {

		AttributesBuilder attributesBuilder = new AttributesBuilder(
			newRole, oldRole);

		attributesBuilder.add("description");
		attributesBuilder.add("name");
		attributesBuilder.add("subtype");
		attributesBuilder.add("type");

		return attributesBuilder.getAttributes();
	}

}