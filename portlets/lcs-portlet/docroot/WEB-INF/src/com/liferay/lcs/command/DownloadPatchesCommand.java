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
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.patcher.PatcherUtil;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.net.URL;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class DownloadPatchesCommand implements Command {

	@Override
	public void execute(RequestCommandMessage requestCommandMessage)
		throws PortalException, SystemException {

		if (!PatcherUtil.isConfigured() ||
			(PatcherUtil.getPatchDirectory() == null)) {

			return;
		}

		Map<String, String> payload =
			(Map<String, String>)requestCommandMessage.getPayload();

		for (String fileName : payload.keySet()) {
			if (_log.isInfoEnabled()) {
				_log.info("Downloading patch " + fileName);
			}

			Map<String, Integer> responsePayload =
				new HashMap<String, Integer>();

			responsePayload.put(fileName, LCSConstants.PATCHES_DOWNLOADING);

			ResponseCommandMessage responseCommandMessage =
				ResponseCommandMessageUtil.createResponseCommandMessage(
					requestCommandMessage, responsePayload);

			_lcsGatewayService.sendMessage(responseCommandMessage);

			File file = new File(PatcherUtil.getPatchDirectory(), fileName);

			String urlString = payload.get(fileName);

			if (_log.isDebugEnabled()) {
				_log.debug("Download URL " + urlString);
			}

			try {
				URL url = new URL(urlString);

				InputStream inputStream = new BufferedInputStream(
					url.openStream());

				FileUtil.write(file, inputStream);

				inputStream.close();
			}
			catch (IOException ioe) {
				_log.error(ioe, ioe);

				responsePayload.clear();

				responsePayload.put(fileName, LCSConstants.PATCHES_ERROR);

				responseCommandMessage =
					ResponseCommandMessageUtil.createResponseCommandMessage(
						requestCommandMessage, responsePayload,
						ioe.getMessage());

				_lcsGatewayService.sendMessage(responseCommandMessage);

				return;
			}

			if (_log.isInfoEnabled()) {
				_log.info("Downloaded patch " + fileName);
			}

			responsePayload.clear();

			responsePayload.put(fileName, LCSConstants.PATCHES_DOWNLOADED);

			responseCommandMessage =
				ResponseCommandMessageUtil.createResponseCommandMessage(
					requestCommandMessage, responsePayload);

			_lcsGatewayService.sendMessage(responseCommandMessage);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		DownloadPatchesCommand.class);

	@BeanReference(type = LCSGatewayService.class)
	private LCSGatewayService _lcsGatewayService;

}