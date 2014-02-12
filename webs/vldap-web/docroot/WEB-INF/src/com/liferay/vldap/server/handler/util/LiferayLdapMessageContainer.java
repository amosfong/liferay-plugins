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

import org.apache.directory.shared.ldap.codec.api.LdapMessageContainer;
import org.apache.directory.shared.ldap.codec.api.MessageDecorator;
import org.apache.directory.shared.ldap.codec.standalone.StandaloneLdapApiService;
import org.apache.directory.shared.ldap.model.message.Message;

/**
 * @author Minhchau Dang
 */
public class LiferayLdapMessageContainer extends
	LdapMessageContainer<MessageDecorator<? extends Message>> {

	public LiferayLdapMessageContainer() throws Exception {
		super(new StandaloneLdapApiService());

		this.grammar = DnCorrectingGrammar.getInstance();
	}

}
