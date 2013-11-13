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

package com.liferay.osb.lcs.service;

import com.liferay.osb.lcs.model.CorpEntryIdentifier;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;

/**
 * @author Igor Beslic
 */
public class CorpEntryServiceUtil {

	public static List<CorpEntryIdentifier> getCorpEntryIdentifiers()
		throws PortalException, SystemException {

		return getCorpEntryService().getCorpEntryIdentifiers();
	}

	public static CorpEntryService getCorpEntryService() {
		return _corpEntryService;
	}

	public void setCorpEntryService(CorpEntryService corpEntryService) {
		_corpEntryService = corpEntryService;
	}

	private static CorpEntryService _corpEntryService;

}