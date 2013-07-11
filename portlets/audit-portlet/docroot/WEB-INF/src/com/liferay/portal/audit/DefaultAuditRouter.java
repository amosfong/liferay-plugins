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

import com.liferay.portal.kernel.audit.AuditException;
import com.liferay.portal.kernel.audit.AuditMessage;
import com.liferay.portal.kernel.audit.AuditMessageProcessor;
import com.liferay.portal.kernel.audit.AuditRouter;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Michael C. Han
 * @author Brian Wing Shun Chan
 */
public class DefaultAuditRouter implements AuditRouter {

	@Override
	public boolean isDeployed() {
		int auditMessageProcessorsCount = 0;

		if (_auditMessageProcessors != null) {
			auditMessageProcessorsCount = _auditMessageProcessors.size();
		}

		if ((auditMessageProcessorsCount > 0) ||
			!_globalAuditMessageProcessors.isEmpty()) {

			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public void route(AuditMessage auditMessage) throws AuditException {
		for (AuditMessageProcessor globalAuditMessageProcessor :
				_globalAuditMessageProcessors) {

			globalAuditMessageProcessor.process(auditMessage);
		}

		String eventType = auditMessage.getEventType();

		Set<AuditMessageProcessor> auditMessageProcessors =
			_auditMessageProcessors.get(eventType);

		if (auditMessageProcessors != null) {
			for (AuditMessageProcessor auditMessageProcessor :
					auditMessageProcessors) {

				auditMessageProcessor.process(auditMessage);
			}
		}
	}

	public void setAuditMessageProcessors(
		Map<String, List<AuditMessageProcessor>> auditMessageProcessors) {

		for (Map.Entry<String, List<AuditMessageProcessor>> entry :
				auditMessageProcessors.entrySet()) {

			String eventType = entry.getKey();

			Set<AuditMessageProcessor> auditMessageProcessorsSet =
				_auditMessageProcessors.get(eventType);

			if (auditMessageProcessorsSet == null) {
				auditMessageProcessorsSet =
					new HashSet<AuditMessageProcessor>();

				_auditMessageProcessors.put(
					eventType, auditMessageProcessorsSet);
			}

			auditMessageProcessorsSet.addAll(entry.getValue());
		}
	}

	public void setGlobalAuditMessageProcessors(
		List<AuditMessageProcessor> globalAuditMessageProcessors) {

		_globalAuditMessageProcessors.addAll(globalAuditMessageProcessors);
	}

	private Map<String, Set<AuditMessageProcessor>> _auditMessageProcessors =
		new ConcurrentHashMap<String, Set<AuditMessageProcessor>>();
	private List<AuditMessageProcessor> _globalAuditMessageProcessors =
		new CopyOnWriteArrayList<AuditMessageProcessor>();

}