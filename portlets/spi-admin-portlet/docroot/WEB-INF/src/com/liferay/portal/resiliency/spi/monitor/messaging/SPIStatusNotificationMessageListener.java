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

package com.liferay.portal.resiliency.spi.monitor.messaging;

import com.liferay.mail.service.MailServiceUtil;
import com.liferay.portal.kernel.mail.MailMessage;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.resiliency.spi.model.SPIDefinition;

import java.util.HashSet;
import java.util.Set;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * @author Michael C. Han
 */
public class SPIStatusNotificationMessageListener
	extends BaseSPIStatusMessageListener {

	public SPIStatusNotificationMessageListener(
		String fromEmailAddress, Set<String> recipientEmailAddresses,
		String subject, String message, Integer... interestedStatuses) {

		try {
			_fromInternetAddress = new InternetAddress(fromEmailAddress);

			Set<InternetAddress> recipientInternetAddresses =
				new HashSet<InternetAddress>(recipientEmailAddresses.size());

			for (String recipientEmailAddress : recipientEmailAddresses) {
				InternetAddress recipientInternetAddress = new InternetAddress(
					recipientEmailAddress);

				recipientInternetAddresses.add(recipientInternetAddress);
			}

			_recipientInternetAddresses = recipientInternetAddresses.toArray(
				new InternetAddress[recipientInternetAddresses.size()]);
		}
		catch (AddressException e) {
			throw new IllegalArgumentException(
				"Unable to parse email addresses", e);
		}

		_message = message;
		_subject = subject;

		setInterestedStatus(interestedStatuses);
	}

	@Override
	protected void processSPIStatus(SPIDefinition spiDefinition, int status)
		throws Exception {

		StringBundler sb = new StringBundler(10);

		sb.append(_message);
		sb.append("<br /><table><tr><td>ID</td><td>");
		sb.append(spiDefinition.getSpiDefinitionId());
		sb.append("</td></tr><tr><td>Name</td><td>");
		sb.append(spiDefinition.getName());
		sb.append("</td></tr><tr><td>Description</td><td>");
		sb.append(spiDefinition.getDescription());
		sb.append("</td></tr><tr><td>Port</td><td>");
		sb.append(spiDefinition.getConnectorPort());
		sb.append("</td></tr><tr><td>Status</td><td>");
		sb.append(spiDefinition.getStatusLabel());
		sb.append("</td></tr><tr><td>Message</td><td>");
		sb.append(spiDefinition.getStatusMessage());
		sb.append("</td></tr></table>");

		MailMessage mailMessage = new MailMessage(
			_fromInternetAddress, _subject, sb.toString(), true);

		mailMessage.setTo(_recipientInternetAddresses);

		MailServiceUtil.sendEmail(mailMessage);
	}

	private InternetAddress _fromInternetAddress;
	private String _message;
	private InternetAddress[] _recipientInternetAddresses;
	private String _subject;

}