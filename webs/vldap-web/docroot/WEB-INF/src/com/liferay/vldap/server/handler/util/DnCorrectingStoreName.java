/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.vldap.server.handler.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import org.apache.directory.shared.asn1.DecoderException;
import org.apache.directory.shared.asn1.ber.tlv.TLV;
import org.apache.directory.shared.asn1.ber.tlv.Value;
import org.apache.directory.shared.ldap.codec.actions.bindRequest.StoreName;
import org.apache.directory.shared.ldap.codec.api.LdapMessageContainer;
import org.apache.directory.shared.ldap.codec.decorators.BindRequestDecorator;
import org.apache.directory.shared.ldap.model.exception.LdapInvalidDnException;
import org.apache.directory.shared.ldap.model.message.BindRequest;
import org.apache.directory.shared.ldap.model.name.Dn;
import org.apache.directory.shared.util.Strings;

/**
 * @author Minhchau Dang
 */
public class DnCorrectingStoreName extends StoreName {

	@Override
	public void action(LdapMessageContainer<BindRequestDecorator> container)
		throws DecoderException {

		TLV tlv = container.getCurrentTLV();
		Value value = tlv.getValue();
		byte[] dnBytes = value.getData();
		String dnString = Strings.utf8ToString(dnBytes);

		Dn dn = _getDn(dnString);

		BindRequest message = container.getMessage();
		message.setName(dn);
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
			_log.error(dnString + " could not be converted to a valid DN");

			return Dn.EMPTY_DN;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(DnCorrectingStoreName.class);
}
