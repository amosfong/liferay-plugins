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

import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;

/**
 * @author Ivica Cardic
 */
public class HandshakeManagerUtil {

	public static void deregister() {
		getHandshakeManager().deregister();
	}

	public static HandshakeManager getHandshakeManager() {
		return _handshakeManager;
	}

	public static Map<String, String> getLCSConnectionMetadata() {
		return getHandshakeManager().getLCSConnectionMetadata();
	}

	public static List<ScheduledFuture<?>> getScheduledFutures() {
		return getHandshakeManager().getScheduledFutures();
	}

	public static boolean isPending() {
		return getHandshakeManager().isPending();
	}

	public static boolean isReady() {
		return getHandshakeManager().isReady();
	}

	public static void setPending(boolean pending) {
		getHandshakeManager().setPending(pending);
	}

	public static void setReady(boolean ready) {
		getHandshakeManager().setReady(ready);
	}

	public static Future<?> start() {
		return getHandshakeManager().start();
	}

	public static Future<?> stop() {
		return getHandshakeManager().stop();
	}

	public void setHandshakeManager(HandshakeManager handshakeManager) {
		_handshakeManager = handshakeManager;
	}

	private static HandshakeManager _handshakeManager;

}