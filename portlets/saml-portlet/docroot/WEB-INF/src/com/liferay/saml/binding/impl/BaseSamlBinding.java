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

package com.liferay.saml.binding.impl;

import com.liferay.saml.binding.SamlBinding;

import org.opensaml.ws.message.decoder.MessageDecoder;
import org.opensaml.ws.message.encoder.MessageEncoder;

/**
 * @author Mika Koivisto
 */
public abstract class BaseSamlBinding implements SamlBinding {

	public BaseSamlBinding(
		MessageDecoder messageDecoder, MessageEncoder messageEncoder) {

		_messageDecoder = messageDecoder;
		_messageEncoder = messageEncoder;
	}

	@Override
	public MessageDecoder getMessageDecoder() {
		return _messageDecoder;
	}

	@Override
	public MessageEncoder getMessageEncoder() {
		return _messageEncoder;
	}

	private MessageDecoder _messageDecoder;
	private MessageEncoder _messageEncoder;

}