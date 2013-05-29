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

package com.liferay.portal.audit;

import com.liferay.portal.kernel.audit.AuditMessage;
import com.liferay.portal.kernel.audit.AuditMessageProcessor;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * @author Mika Koivisto
 * @author Brian Wing Shun Chan
 */
public class LogAuditRouterProcessor implements AuditMessageProcessor {

	@Override
	public void process(AuditMessage auditMessage) {
		try {
			doProcess(auditMessage);
		}
		catch (Exception e) {
			_log.fatal("Unable to process audit message " + auditMessage, e);
		}
	}

	public void setLogMessageFormatter(
		LogMessageFormatter logMessageFormatter) {

		_logMessageFormatter = logMessageFormatter;
	}

	public void setOutputToConsole(boolean outputToConsole) {
		_outputToConsole = outputToConsole;
	}

	protected void doProcess(AuditMessage auditMessage) throws Exception {
		if (_log.isInfoEnabled() || _outputToConsole) {
			String logMessage = _logMessageFormatter.format(auditMessage);

			if (_log.isInfoEnabled()) {
				_log.info(logMessage);
			}

			if (_outputToConsole) {
				System.out.println("LogAuditRouterProcessor: " + logMessage);
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		LogAuditRouterProcessor.class);

	private LogMessageFormatter _logMessageFormatter;
	private boolean _outputToConsole;

}