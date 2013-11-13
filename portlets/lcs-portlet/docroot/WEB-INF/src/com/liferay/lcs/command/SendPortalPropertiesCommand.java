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

package com.liferay.lcs.command;

import com.liferay.lcs.messaging.RequestCommandMessage;
import com.liferay.lcs.messaging.ResponseCommandMessage;
import com.liferay.lcs.service.LCSGatewayService;
import com.liferay.lcs.util.ResponseCommandMessageUtil;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Digester;
import com.liferay.portal.kernel.util.DigesterUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.SortedProperties;
import com.liferay.portal.kernel.util.StringBundler;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author Ivica Cardic
 */
public class SendPortalPropertiesCommand implements Command {

	@Override
	public void execute(RequestCommandMessage requestCommandMessage)
		throws PortalException, SystemException {

		Properties portalProperties = new SortedProperties(
			PropsUtil.getProperties());

		Set<Object> portalPropertiesKeys = portalProperties.keySet();

		Iterator<Object> iterator = portalPropertiesKeys.iterator();

		while (iterator.hasNext()) {
			String portalPropertiesKey = (String)iterator.next();

			if (portalPropertiesKey.endsWith(".password")) {
				iterator.remove();
			}
		}

		for (String portalPropertiesKey : _PASSWORD_PORTAL_PROPERTIES_KEYS) {
			portalProperties.remove(portalPropertiesKey);
		}

		StringBundler sb = new StringBundler(portalProperties.size());

		for (Object key : portalProperties.keySet()) {
			sb.append(
				DigesterUtil.digestHex(
					Digester.MD5, (String)portalProperties.get(key)));
		}

		String installedHashCode = DigesterUtil.digestHex(
			Digester.MD5, sb.toString());

		String hashCode = null;

		if (requestCommandMessage.getPayload() != null) {
			hashCode = (String)requestCommandMessage.getPayload();
		}

		if (installedHashCode.equals(hashCode)) {
			return;
		}

		Map<String, String> partialPortalProperties =
			new TreeMap<String, String>();

		int i = 0;

		for (Object key : portalProperties.keySet()) {
			String value = portalProperties.getProperty((String)key);

			partialPortalProperties.put((String)key, value);

			if (((i % 50) == 0) || (i == (portalProperties.size() - 1))) {
				Map<String, Object> payload = new HashMap<String, Object>();

				payload.put("hashCode", installedHashCode);
				payload.put("portalProperties", partialPortalProperties);

				ResponseCommandMessage responseCommandMessage =
					ResponseCommandMessageUtil.createResponseCommandMessage(
						requestCommandMessage, payload);

				_lcsGatewayService.sendMessage(responseCommandMessage);

				partialPortalProperties.clear();
			}

			i++;
		}
	}

	private static final String[] _PASSWORD_PORTAL_PROPERTIES_KEYS = {
		"mail.hook.cyrus.add.user", "mail.hook.cyrus.delete.user",
	};

	@BeanReference(type = LCSGatewayService.class)
	private LCSGatewayService _lcsGatewayService;

}