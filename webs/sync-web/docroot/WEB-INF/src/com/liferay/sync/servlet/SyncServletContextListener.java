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

package com.liferay.sync.servlet;

import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.SerialDestination;
import com.liferay.portal.kernel.util.BasePortalLifecycle;
import com.liferay.sync.messaging.SyncMessageListener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author Brian Wing Shun Chan
 * @author Dennis Ju
 */
public class SyncServletContextListener
	extends BasePortalLifecycle implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		portalDestroy();
	}

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		registerPortalLifecycle();
	}

	@Override
	protected void doPortalDestroy() throws Exception {
		MessageBusUtil.unregisterMessageListener(
			DestinationNames.DOCUMENT_LIBRARY_SYNC_PROCESSOR, _messageListener);
	}

	@Override
	protected void doPortalInit() {
		_messageListener = new SyncMessageListener();

		SerialDestination serialDestination = new SerialDestination();

		serialDestination.setName(
			DestinationNames.DOCUMENT_LIBRARY_SYNC_PROCESSOR);

		serialDestination.afterPropertiesSet();

		MessageBusUtil.addDestination(serialDestination);

		MessageBusUtil.registerMessageListener(
			DestinationNames.DOCUMENT_LIBRARY_SYNC_PROCESSOR, _messageListener);
	}

	private MessageListener _messageListener;

}