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

package com.liferay.saml.provider;

import java.io.File;

import java.util.Timer;
import java.util.TimerTask;

import org.opensaml.saml2.metadata.provider.FilesystemMetadataProvider;
import org.opensaml.saml2.metadata.provider.MetadataProviderException;

/**
 * @author Mika Koivisto
 */
public class ReinitializingFilesystemMetadataProvider
	extends FilesystemMetadataProvider {

	public ReinitializingFilesystemMetadataProvider(Timer timer, File file)
		throws MetadataProviderException {

		super(timer, file);

		_timer = timer;
	}

	@Override
	public synchronized void destroy() {
		super.destroy();

		_timer.cancel();
	}

	@Override
	public synchronized void initialize() throws MetadataProviderException {
		try {
			super.initialize();

			if (!super.isInitialized()) {
				scheduleReinitialization();
			}
		}
		catch (MetadataProviderException mpe) {
			scheduleReinitialization();

			throw mpe;
		}
	}

	protected void scheduleReinitialization() {
		TimerTask timerTask = new ReinitializingTimerTask(_timer, this);

		_timer.schedule(timerTask, getMinRefreshDelay());
	}

	private Timer _timer;

}