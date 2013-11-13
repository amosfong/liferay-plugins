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

package com.liferay.lcs.util;

import com.liferay.lcs.task.HandshakeTask;
import com.liferay.lcs.task.SignoffTask;
import com.liferay.portal.kernel.bean.BeanReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author Igor Beslic
 * @author Ivica Cardic
 */
public class HandshakeManagerImpl implements HandshakeManager {

	@Override
	public void deregister() {
		stop(true);
	}

	public void destroy() {
		Future<?> future = stop();

		try {
			future.get();
		}
		catch (Exception e) {
		}

		_scheduledExecutorService.shutdown();

		try {
			if (!_scheduledExecutorService.awaitTermination(
					5, TimeUnit.SECONDS)) {

				_scheduledExecutorService.shutdownNow();
			}
		}
		catch (final InterruptedException ie) {
			_scheduledExecutorService.shutdownNow();
		}
	}

	@Override
	public Map<String, String> getLCSConnectionMetadata() {
		return _lcsConnectionMetadata;
	}

	@Override
	public List<ScheduledFuture<?>> getScheduledFutures() {
		return _scheduledFutures;
	}

	@Override
	public synchronized boolean isPending() {
		return _pending;
	}

	@Override
	synchronized public boolean isReady() {
		return _ready;
	}

	@Override
	public void putLCSConnectionMetadata(String key, String value) {
		_lcsConnectionMetadata.put(key, value);
	}

	public void setHandshakeWaitTime(long handshakeWaitTime) {
		_lcsConnectionMetadata.put(
			"handshakeWaitTime", String.valueOf(handshakeWaitTime));
	}

	public void setHeartbeatInterval(long heartbeatInterval) {
		_lcsConnectionMetadata.put(
			"heartbeatInterval", String.valueOf(heartbeatInterval));
	}

	@Override
	synchronized public void setPending(boolean pending) {
		_pending = pending;
	}

	@Override
	synchronized public void setReady(boolean ready) {
		_ready = ready;
	}

	@Override
	public Future<?> start() {
		if (isReady() || isPending()) {
			return null;
		}

		setPending(true);

		Future<?> future = _scheduledExecutorService.submit(_handshakeTask);

		return future;
	}

	@Override
	public Future<?> stop() {
		return stop(false);
	}

	public Future<?> stop(boolean deregister) {
		if (!isReady()) {
			return null;
		}

		setPending(true);
		setReady(false);

		if (deregister) {
			_signoffTask.setDeregister(true);
		}
		else {
			_signoffTask.setDeregister(false);
		}

		Future<?> future = _scheduledExecutorService.submit(_signoffTask);

		return future;
	}

	private static ScheduledExecutorService _scheduledExecutorService =
		Executors.newScheduledThreadPool(3);

	@BeanReference(type = HandshakeTask.class)
	private HandshakeTask _handshakeTask;

	private Map<String, String> _lcsConnectionMetadata =
		new HashMap<String, String>();
	private boolean _pending;
	private boolean _ready;
	private List<ScheduledFuture<?>> _scheduledFutures =
		new ArrayList<ScheduledFuture<?>>();

	@BeanReference(type = SignoffTask.class)
	private SignoffTask _signoffTask;

}