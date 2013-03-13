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

import org.opensaml.saml2.metadata.provider.AbstractReloadingMetadataProvider;
import org.opensaml.saml2.metadata.provider.MetadataProviderException;

/**
 * @author Mika Koivisto
 */
public class ReinitializingTimerTask extends TimerTask {

	public ReinitializingTimerTask(
		Timer timer,
		AbstractReloadingMetadataProvider abstractReloadingMetadataProvider) {

		_timer = timer;
		_abstractReloadingMetadataProvider = abstractReloadingMetadataProvider;
	}

	@Override
	public void run() {
		try {
			_abstractReloadingMetadataProvider.initialize();
		}
		catch (MetadataProviderException mpe) {
			TimerTask timerTask = new ReinitializingTimerTask(
				_timer, _abstractReloadingMetadataProvider);

			_timer.schedule(
				timerTask,
				_abstractReloadingMetadataProvider.getMinRefreshDelay());
		}
	}

	private AbstractReloadingMetadataProvider
		_abstractReloadingMetadataProvider;
	private Timer _timer;

}