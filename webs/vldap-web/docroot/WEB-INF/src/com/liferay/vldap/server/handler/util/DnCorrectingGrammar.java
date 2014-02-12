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

import org.apache.directory.shared.asn1.ber.Asn1Container;
import org.apache.directory.shared.asn1.ber.grammar.AbstractGrammar;
import org.apache.directory.shared.asn1.ber.grammar.GrammarTransition;
import org.apache.directory.shared.asn1.ber.tlv.UniversalTag;
import org.apache.directory.shared.ldap.codec.LdapMessageGrammar;
import org.apache.directory.shared.ldap.codec.actions.bindRequest.StoreName;

/**
 * @author Minhchau Dang
 */
public class DnCorrectingGrammar extends AbstractGrammar {

	public static DnCorrectingGrammar getInstance() {
		return _instance;
	}

	protected DnCorrectingGrammar() {
		_grammar = (LdapMessageGrammar) LdapMessageGrammar.getInstance();

		_storeNameAction = new DnCorrectingStoreName();
	}

	@Override
	public GrammarTransition getTransition(Enum state, int tag) {
		GrammarTransition transition = _grammar.getTransition(state, tag);

		if (transition.getAction() instanceof StoreName) {
			transition = new GrammarTransition(
				transition.getPreviousState(), transition.getCurrentState(),
				UniversalTag.OCTET_STRING, _storeNameAction);
		}

		return transition;
	}

	private static DnCorrectingGrammar _instance = new DnCorrectingGrammar();

	private LdapMessageGrammar _grammar;
	private DnCorrectingStoreName _storeNameAction;

}
