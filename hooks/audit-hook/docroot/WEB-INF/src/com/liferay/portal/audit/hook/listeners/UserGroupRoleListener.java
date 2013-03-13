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
import com.liferay.portal.audit.hook.listeners.util.AuditMessageBuilder;
import com.liferay.portal.audit.util.EventTypes;
import com.liferay.portal.kernel.audit.AuditMessage;
import com.liferay.portal.kernel.audit.AuditRouterUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.model.BaseModelListener;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroupRole;

/**
 * @author Mika Koivisto
 * @author Brian Wing Shun Chan
 */
public class UserGroupRoleListener extends BaseModelListener<UserGroupRole> {

	public void onBeforeCreate(UserGroupRole userGroupRole)
		throws ModelListenerException {

		auditOnCreateOrRemove(EventTypes.ASSIGN, userGroupRole);
	}

	public void onBeforeRemove(UserGroupRole userGroupRole)
		throws ModelListenerException {

		auditOnCreateOrRemove(EventTypes.UNASSIGN, userGroupRole);
	}

	protected void auditOnCreateOrRemove(
			String eventType, UserGroupRole userGroupRole)
		throws ModelListenerException {

		try {
			AuditMessage auditMessage = AuditMessageBuilder.buildAuditMessage(
				eventType, User.class.getName(), userGroupRole.getUserId(),
				null);

			JSONObject additionalInfo = auditMessage.getAdditionalInfo();

			additionalInfo.put("roleId", userGroupRole.getRoleId());

			Role role = userGroupRole.getRole();

			additionalInfo.put("roleName", role.getName());

			Group group = userGroupRole.getGroup();

			additionalInfo.put("scopeClassName", group.getClassName());
			additionalInfo.put("scopeClassPK", group.getClassPK());

			AuditRouterUtil.route(auditMessage);
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

}