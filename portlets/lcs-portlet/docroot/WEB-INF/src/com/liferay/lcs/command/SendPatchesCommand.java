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
import com.liferay.lcs.util.LCSConstants;
import com.liferay.lcs.util.ResponseCommandMessageUtil;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Digester;
import com.liferay.portal.kernel.util.DigesterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.patcher.PatcherUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ivica Cardic
 */
public class SendPatchesCommand implements Command {

	@Override
	public void execute(RequestCommandMessage requestCommandMessage)
		throws PortalException, SystemException {

		String[] fixedIssues = PatcherUtil.getFixedIssues();

		String hashCode = null;

		if (requestCommandMessage.getPayload() != null) {
			hashCode = (String)requestCommandMessage.getPayload();
		}

		String[] installedPatches = PatcherUtil.getInstalledPatches();
		String[] patchLevels = PatcherUtil.getPatchLevels();

		Map<String, Object> payload = new HashMap<String, Object>();

		if (PatcherUtil.isConfigured()) {
			StringBundler sb = new StringBundler(installedPatches.length + 1);

			if (installedPatches.length > 0) {
				Arrays.sort(installedPatches);

				for (String patch : installedPatches) {
					sb.append(DigesterUtil.digestHex(Digester.MD5, patch));
				}
			}

			sb.append(
				DigesterUtil.digestHex(
					Digester.MD5, String.valueOf(PatcherUtil.isConfigured())));
			sb.append(
				DigesterUtil.digestHex(
					Digester.MD5,
					String.valueOf(PatcherUtil.getPatchingToolVersion())));

			String installedHashCode = DigesterUtil.digestHex(
				Digester.MD5, sb.toString());

			if (!installedHashCode.equals(hashCode)) {
				payload.put("fixedIssues", ListUtil.fromArray(fixedIssues));
				payload.put("hashCode", installedHashCode);

				Map<String, Integer> patches = new HashMap<String, Integer>();

				for (String patch : installedPatches) {
					patches.put(patch, LCSConstants.PATCHES_INSTALLED);
				}

				payload.put("patches", patches);
				payload.put(
					"patchingToolVersion",
					PatcherUtil.getPatchingToolVersion());
				payload.put("patchLevels", ListUtil.fromArray(patchLevels));

				ResponseCommandMessage responseCommandMessage =
					ResponseCommandMessageUtil.createResponseCommandMessage(
						requestCommandMessage, payload);

				_lcsGatewayService.sendMessage(responseCommandMessage);
			}
		}
		else if (hashCode != null) {
			ResponseCommandMessage responseCommandMessage =
				ResponseCommandMessageUtil.createResponseCommandMessage(
					requestCommandMessage, payload);

			_lcsGatewayService.sendMessage(responseCommandMessage);
		}
	}

	@BeanReference(type = LCSGatewayService.class)
	private LCSGatewayService _lcsGatewayService;

}