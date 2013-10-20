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
			_fromEmailAddress = new InternetAddress(fromEmailAddress);

			Set<InternetAddress> recipientEmailAddressSet =
				new HashSet<InternetAddress>(recipientEmailAddresses.size());

			for (String recipientEmailAddress : recipientEmailAddresses) {
				InternetAddress recipientEmail = new InternetAddress(
					recipientEmailAddress);

				recipientEmailAddressSet.add(recipientEmail);
			}

			_recipientEmailAddresses = recipientEmailAddressSet.toArray(
				new InternetAddress[recipientEmailAddressSet.size()]);
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
	protected boolean processSPIStatus(SPIDefinition spiDefinition, int status)
		throws Exception {

		StringBundler notificationMessage = new StringBundler(10);

		notificationMessage.append(_message);
		notificationMessage.append("<br><table><tr><td>Id</td><td>");
		notificationMessage.append(spiDefinition.getSpiDefinitionId());
		notificationMessage.append("</td></tr><tr><td>Name</td><td>");
		notificationMessage.append(spiDefinition.getName());
		notificationMessage.append("</td></tr><tr><td>Description</td><td>");
		notificationMessage.append(spiDefinition.getDescription());
		notificationMessage.append("</td></tr><tr><td>Port</td><td>");
		notificationMessage.append(spiDefinition.getConnectorPort());
		notificationMessage.append("</td></tr><tr><td>Status</td><td>");
		notificationMessage.append(spiDefinition.getStatusLabel());
		notificationMessage.append("</td></tr><tr><td>Message</td><td>");
		notificationMessage.append(spiDefinition.getStatusMessage());
		notificationMessage.append("</td></tr></table>");

		MailMessage mailMessage = new MailMessage(
			_fromEmailAddress, _subject, notificationMessage.toString(), true);

		mailMessage.setTo(_recipientEmailAddresses);

		MailServiceUtil.sendEmail(mailMessage);

		return false;
	}

	private InternetAddress _fromEmailAddress;
	private String _message;
	private InternetAddress[] _recipientEmailAddresses;
	private String _subject;

}