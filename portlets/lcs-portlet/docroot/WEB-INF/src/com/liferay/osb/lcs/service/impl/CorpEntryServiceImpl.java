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

package com.liferay.osb.lcs.service.impl;

import com.liferay.lcs.service.impl.BaseLCSServiceImpl;
import com.liferay.osb.lcs.model.CorpEntryIdentifier;
import com.liferay.osb.lcs.service.CorpEntryService;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;

/**
 * @author Igor Beslic
 */
public class CorpEntryServiceImpl
	extends BaseLCSServiceImpl implements CorpEntryService {

	@Override
	public String getBeanIdentifier() {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<CorpEntryIdentifier> getCorpEntryIdentifiers()
		throws SystemException {

		try {
			return doGetToList(
				CorpEntryIdentifier.class,
				_URL_CORP_ENTRY_GET_CORP_ENTRY_IDENTIFIERS);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@Override
	public List<CorpEntryIdentifier> getCorpEntryIdentifiers(
		boolean checkLCSClusterUser) {

		throw new UnsupportedOperationException();
	}

	@Override
	public Object invokeMethod(
			String name, String[] parameterTypes, Object[] arguments)
		throws Throwable {

		throw new UnsupportedOperationException();
	}

	@Override
	public void setBeanIdentifier(String beanIdentifier) {
		throw new UnsupportedOperationException();
	}

	private static final String _URL_CORP_ENTRY =
		"/api/secure/jsonws/osb-lcs-portlet.corpentry/";

	private static final String _URL_CORP_ENTRY_GET_CORP_ENTRY_IDENTIFIERS =
		_URL_CORP_ENTRY + "get-corp-entry-identifiers";

}