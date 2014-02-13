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

package com.liferay.vldap.server.handler.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import org.apache.directory.shared.asn1.ber.grammar.GrammarAction;
import org.apache.directory.shared.asn1.ber.tlv.TLV;
import org.apache.directory.shared.asn1.ber.tlv.Value;
import org.apache.directory.shared.ldap.model.exception.LdapInvalidDnException;
import org.apache.directory.shared.ldap.model.message.BindRequest;
import org.apache.directory.shared.ldap.model.name.Dn;
import org.apache.directory.shared.util.Strings;

/**
 * @author Minhchau Dang
 */
public class DnCorrectingStoreName<E extends LiferayLdapMessageContainer>
	extends GrammarAction<E> {

	@Override
	public void action(E messageContainer) {
		BindRequest bindRequest = (BindRequest)messageContainer.getMessage();

		TLV tlv = messageContainer.getCurrentTLV();

		Value value = tlv.getValue();

		byte[] dnBytes = value.getData();

		String dnString = Strings.utf8ToString(dnBytes);

		Dn dn = _getDn(dnString);

		bindRequest.setName(dn);
	}

	private Dn _getDn(String dnString) {
		if (Validator.isNull(dnString)) {
			return Dn.EMPTY_DN;
		}

		String fixedDnString = dnString;

		if (!dnString.contains(StringPool.EQUAL)) {
			if (Validator.isEmailAddress(dnString)) {
				fixedDnString = "mail=" + dnString;
			}
			else {
				fixedDnString = "cn=" + dnString;
			}
		}

		try {
			return new Dn(fixedDnString);
		}
		catch (LdapInvalidDnException e) {
			_log.error("Unable to convert " + dnString + " to a valid DN");

			return Dn.EMPTY_DN;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		DnCorrectingStoreName.class);

}