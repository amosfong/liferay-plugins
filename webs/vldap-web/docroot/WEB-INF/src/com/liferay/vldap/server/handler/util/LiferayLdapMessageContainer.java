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

import org.apache.directory.shared.ldap.codec.api.LdapMessageContainer;
import org.apache.directory.shared.ldap.codec.api.MessageDecorator;
import org.apache.directory.shared.ldap.codec.standalone.StandaloneLdapApiService;
import org.apache.directory.shared.ldap.model.message.Message;

/**
 * @author Minhchau Dang
 */
public class LiferayLdapMessageContainer
	extends LdapMessageContainer<MessageDecorator<? extends Message>> {

	public LiferayLdapMessageContainer() throws Exception {
		super(new StandaloneLdapApiService());

		this.grammar = new DnCorrectingGrammar<LiferayLdapMessageContainer>();
	}

}