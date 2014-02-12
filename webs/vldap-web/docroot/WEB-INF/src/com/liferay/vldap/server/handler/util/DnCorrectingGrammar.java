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

	protected DnCorrectingGrammar() {
		_grammar = (LdapMessageGrammar)LdapMessageGrammar.getInstance();

		_storeNameAction = new DnCorrectingStoreName();
	}

	private static DnCorrectingGrammar _instance = new DnCorrectingGrammar();

	private LdapMessageGrammar _grammar;
	private DnCorrectingStoreName _storeNameAction;

}