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
 * @author Igor Beslic
 */
public interface HandshakeManager {

	public void deregister();

	public Map<String, String> getLCSConnectionMetadata();

	public List<ScheduledFuture<?>> getScheduledFutures();

	public boolean isPending();

	boolean isReady();

	public void putLCSConnectionMetadata(String key, String value);

	public void setPending(boolean pending);

	public void setReady(boolean ready);

	public Future<?> start();

	public Future<?> stop();

}