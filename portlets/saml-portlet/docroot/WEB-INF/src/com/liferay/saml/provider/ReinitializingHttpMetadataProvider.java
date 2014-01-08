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

import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.httpclient.HttpClient;

import org.opensaml.saml2.metadata.provider.HTTPMetadataProvider;
import org.opensaml.saml2.metadata.provider.MetadataProviderException;

/**
 * @author Mika Koivisto
 */
public class ReinitializingHttpMetadataProvider extends HTTPMetadataProvider {

	public ReinitializingHttpMetadataProvider(
			Timer timer, HttpClient httpClient, String url)
		throws MetadataProviderException {

		super(timer, httpClient, url);

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