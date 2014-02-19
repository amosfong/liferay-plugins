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

package com.liferay.reports.service.impl;

import com.liferay.portal.kernel.bi.reporting.MemoryReportDesignRetriever;
import com.liferay.portal.kernel.bi.reporting.ReportDataSourceType;
import com.liferay.portal.kernel.bi.reporting.ReportDesignRetriever;
import com.liferay.portal.kernel.bi.reporting.ReportRequest;
import com.liferay.portal.kernel.bi.reporting.ReportRequestContext;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Junction;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelperUtil;
import com.liferay.portal.kernel.scheduler.StorageType;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.scheduler.TriggerFactoryUtil;
import com.liferay.portal.kernel.scheduler.TriggerType;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portal.util.SubscriptionSender;
import com.liferay.portlet.documentlibrary.DuplicateDirectoryException;
import com.liferay.portlet.documentlibrary.NoSuchDirectoryException;
import com.liferay.portlet.documentlibrary.store.DLStoreUtil;
import com.liferay.reports.EntryEmailDeliveryException;
import com.liferay.reports.EntryEmailNotificationsException;
import com.liferay.reports.ReportStatus;
import com.liferay.reports.admin.util.AdminUtil;
import com.liferay.reports.model.Definition;
import com.liferay.reports.model.Entry;
import com.liferay.reports.model.Source;
import com.liferay.reports.service.base.EntryLocalServiceBaseImpl;

import java.io.File;

import java.text.DateFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletPreferences;

/**
 * @author Gavin Wan
 */
public class EntryLocalServiceImpl extends EntryLocalServiceBaseImpl {

	public Entry addEntry(
			long userId, long groupId, long definitionId, String format,
			boolean schedulerRequest, Date startDate, Date endDate,
			boolean repeating, String recurrence, String emailNotifications,
			String emailDelivery, String portletId, String pageURL,
			String reportParameters, ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Entry

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		validate(emailNotifications, emailDelivery);

		long entryId = counterLocalService.increment();

		Entry entry = entryPersistence.create(entryId);

		entry.setGroupId(groupId);
		entry.setCompanyId(user.getCompanyId());
		entry.setUserId(user.getUserId());
		entry.setUserName(user.getFullName());
		entry.setCreateDate(serviceContext.getCreateDate(now));
		entry.setModifiedDate(serviceContext.getModifiedDate(now));
		entry.setDefinitionId(definitionId);
		entry.setFormat(format);
		entry.setScheduleRequest(schedulerRequest);
		entry.setStartDate(startDate);
		entry.setEndDate(endDate);
		entry.setReportParameters(reportParameters);
		entry.setRepeating(repeating);
		entry.setRecurrence(recurrence);
		entry.setEmailNotifications(emailNotifications);
		entry.setEmailDelivery(emailDelivery);
		entry.setPortletId(portletId);
		entry.setPageURL(pageURL + "&entryId=" + entryId);
		entry.setStatus(ReportStatus.PENDING.getValue());

		entryPersistence.update(entry);

		// Resources

		resourceLocalService.addModelResources(entry, serviceContext);

		// Scheduler

		if (schedulerRequest) {
			scheduleEntry(entry);
		}

		// Report

		if (!schedulerRequest) {
			generateReport(entryId);
		}

		return entry;
	}

	public void addEntryResources(
			Entry entry, boolean addCommunityPermissions,
			boolean addGuestPermissions)
		throws PortalException, SystemException {

		resourceLocalService.addResources(
			entry.getCompanyId(), entry.getGroupId(), entry.getUserId(),
			Entry.class.getName(), entry.getEntryId(), false,
			addCommunityPermissions, addGuestPermissions);
	}

	public void addEntryResources(
			Entry entry, String[] communityPermissions,
			String[] guestPermissions)
		throws PortalException, SystemException {

		resourceLocalService.addModelResources(
			entry.getCompanyId(), entry.getGroupId(), entry.getUserId(),
			Entry.class.getName(), entry.getEntryId(), communityPermissions,
			guestPermissions);
	}

	public void deleteAttachment(long companyId, String fileName)
		throws PortalException, SystemException {

		DLStoreUtil.deleteFile(companyId, CompanyConstants.SYSTEM, fileName);
	}

	@Override
	public Entry deleteEntry(Entry entry)
		throws PortalException, SystemException {

		// Entry

		entryPersistence.remove(entry);

		// Resources

		resourceLocalService.deleteResource(
			entry.getCompanyId(), Entry.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL, entry.getEntryId());

		// Scheduler

		if (entry.isRepeating()) {
			SchedulerEngineHelperUtil.unschedule(
				entry.getJobName(), entry.getSchedulerRequestName(),
				StorageType.PERSISTED);
		}

		// Attachments

		try {
			DLStoreUtil.deleteDirectory(
				entry.getCompanyId(), CompanyConstants.SYSTEM,
				entry.getAttachmentsDir());
		}
		catch (NoSuchDirectoryException nsde) {
		}

		return entry;
	}

	@Override
	public Entry deleteEntry(long entryId)
		throws PortalException, SystemException {

		Entry entry = entryPersistence.findByPrimaryKey(entryId);

		return deleteEntry(entry);
	}

	public void generateReport(long entryId)
		throws PortalException, SystemException {

		Entry entry = entryPersistence.findByPrimaryKey(entryId);

		Definition definition = definitionPersistence.findByPrimaryKey(
			entry.getDefinitionId());

		String[] existingFiles = definition.getAttachmentsFiles();

		byte[] templateFile = DLStoreUtil.getFileAsBytes(
			definition.getCompanyId(), CompanyConstants.SYSTEM,
			existingFiles[0]);

		String reportName = definition.getReportName().concat(
			StringPool.PERIOD).concat(entry.getFormat());

		ReportDesignRetriever retriever = new MemoryReportDesignRetriever(
			reportName, definition.getModifiedDate(), templateFile);

		long sourceId = definition.getSourceId();

		Map<String, String> reportParameters = new HashMap<String, String>();

		JSONArray reportParametersJSONArray = JSONFactoryUtil.createJSONArray(
			entry.getReportParameters());

		for (int i = 0; i < reportParametersJSONArray.length(); i++) {
			JSONObject reportParameterJSONObject =
				reportParametersJSONArray.getJSONObject(i);

			reportParameters.put(
				reportParameterJSONObject.getString("key"),
				reportParameterJSONObject.getString("value"));
		}

		ReportRequestContext reportRequestContext = null;

		if (sourceId == 0) {
			reportRequestContext = new ReportRequestContext(
				ReportDataSourceType.PORTAL);
		}
		else {
			Source source = sourcePersistence.findByPrimaryKey(sourceId);

			reportRequestContext = new ReportRequestContext(
				ReportDataSourceType.JDBC);

			reportRequestContext.setAttribute(
				ReportRequestContext.JDBC_DRIVER_CLASS,
				source.getDriverClassName());
			reportRequestContext.setAttribute(
				ReportRequestContext.JDBC_URL, source.getDriverUrl());
			reportRequestContext.setAttribute(
				ReportRequestContext.JDBC_USER_NAME,
				source.getDriverUserName());
			reportRequestContext.setAttribute(
				ReportRequestContext.JDBC_PASSWORD, source.getDriverPassword());
		}

		ReportRequest reportRequest = new ReportRequest(
			reportRequestContext, retriever, reportParameters,
			entry.getFormat());

		Message message = new Message();

		message.setPayload(reportRequest);
		message.setResponseId(String.valueOf(entry.getEntryId()));
		message.setResponseDestinationName("liferay/reports_admin");

		MessageBusUtil.sendMessage(DestinationNames.REPORT_REQUEST, message);
	}

	@SuppressWarnings("unchecked")
	public List<Entry> getEntries(
			long groupId, String definitionName, String userName,
			Date createDateGT, Date createDateLT, boolean andSearch, int start,
			int end, OrderByComparator orderByComparator)
		throws SystemException {

		DynamicQuery dynamicQuery = buildDynamicQuery(
			groupId, definitionName, userName, createDateGT, createDateLT,
			andSearch);

		return dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	public int getEntriesCount(
			long groupId, String definitionName, String userName,
			Date createDateGT, Date createDateLT, boolean andSearch)
		throws SystemException {

		DynamicQuery dynamicQuery = buildDynamicQuery(
			groupId, definitionName, userName, createDateGT, createDateLT,
			andSearch);

		return (int)dynamicQueryCount(dynamicQuery);
	}

	public void sendEmails(
			long entryId, String fileName, String[] emailAddresses,
			boolean notification)
		throws PortalException, SystemException {

		Entry entry = entryLocalService.getEntry(entryId);

		String reportName = StringUtil.extractLast(
			fileName, StringPool.FORWARD_SLASH);

		File file = DLStoreUtil.getFile(
			entry.getCompanyId(), CompanyConstants.SYSTEM, fileName);

		try {
			notifySubscribers(
				entry, emailAddresses, reportName, file, notification);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	public void unscheduleEntry(long entryId)
		throws PortalException, SystemException {

		Entry entry = entryPersistence.findByPrimaryKey(entryId);

		entry.setScheduleRequest(false);
		entry.setRepeating(false);

		entryPersistence.update(entry);

		SchedulerEngineHelperUtil.unschedule(
			entry.getJobName(), entry.getSchedulerRequestName(),
			StorageType.PERSISTED);
	}

	public void updateEntry(
			long entryId, String reportName, byte[] reportResults)
		throws PortalException, SystemException {

		Entry entry = entryPersistence.findByPrimaryKey(entryId);
		Date now = new Date();

		if (entry.isScheduleRequest()) {
			StringBundler sb = new StringBundler(4);

			sb.append(StringUtil.extractFirst(reportName, StringPool.PERIOD));

			DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat(
				"MM_dd_yyyy_HH_mm");

			sb.append(dateFormat.format(now));

			sb.append(StringPool.PERIOD);
			sb.append(StringUtil.extractLast(reportName, StringPool.PERIOD));

			reportName = sb.toString();
		}

		String fileName =
			entry.getAttachmentsDir() + StringPool.SLASH + reportName;

		try {
			DLStoreUtil.addDirectory(
				entry.getCompanyId(), CompanyConstants.SYSTEM,
				entry.getAttachmentsDir());
		}
		catch (DuplicateDirectoryException dde) {
		}

		DLStoreUtil.addFile(
			entry.getCompanyId(), CompanyConstants.SYSTEM, fileName, false,
			reportResults);

		String[] emailAddresses = StringUtil.split(entry.getEmailDelivery());

		sendEmails(entryId, fileName, emailAddresses, false);

		emailAddresses = StringUtil.split(entry.getEmailNotifications());

		sendEmails(entryId, fileName, emailAddresses, true);

		entry.setStatus(ReportStatus.COMPLETE.getValue());

		entryPersistence.update(entry);
	}

	public void updateEntryStatus(
			long entryId, ReportStatus status, String errorMessage)
		throws PortalException, SystemException {

		Entry entry = entryLocalService.getEntry(entryId);

		entry.setStatus(status.getValue());
		entry.setErrorMessage(errorMessage);

		entryPersistence.update(entry);
	}

	protected DynamicQuery buildDynamicQuery(
		long groupId, String definitionName, String userName, Date createDateGT,
		Date createDateLT, boolean andSearch) {

		Junction junction = null;

		if (andSearch) {
			junction = RestrictionsFactoryUtil.conjunction();
		}
		else {
			junction = RestrictionsFactoryUtil.disjunction();
		}

		if (Validator.isNotNull(definitionName)) {
			DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
				Definition.class, getClassLoader());

			Property nameProperty = PropertyFactoryUtil.forName("name");

			String value =
				StringPool.PERCENT + definitionName + StringPool.PERCENT;

			dynamicQuery.add(nameProperty.like(value));

			dynamicQuery.setProjection(
				ProjectionFactoryUtil.property("definitionId"));

			Property definitionIdProperty = PropertyFactoryUtil.forName(
				"definitionId");

			junction.add(definitionIdProperty.in(dynamicQuery));
		}

		if (Validator.isNotNull(userName)) {
			DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
				User.class, getClassLoader());

			Property nameProperty = PropertyFactoryUtil.forName("screenName");

			String value = StringPool.PERCENT + userName + StringPool.PERCENT;

			dynamicQuery.add(nameProperty.like(value));

			dynamicQuery.setProjection(
				ProjectionFactoryUtil.property("userId"));

			Property definitionIdProperty = PropertyFactoryUtil.forName(
				"userId");

			junction.add(definitionIdProperty.in(dynamicQuery));
		}

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Entry.class, getClassLoader());

		if (groupId > 0) {
			Property property = PropertyFactoryUtil.forName("groupId");

			dynamicQuery.add(property.eq(groupId));
		}

		if (createDateGT != null) {
			Property property = PropertyFactoryUtil.forName("createDate");

			dynamicQuery.add(property.gt(createDateGT));
		}

		if (createDateLT != null) {
			Property property = PropertyFactoryUtil.forName("createDate");

			dynamicQuery.add(property.lt(createDateLT));
		}

		dynamicQuery.add(junction);

		return dynamicQuery;
	}

	protected void notifySubscribers(
			Entry entry, String[] emailAddresses, String reportName, File file,
			boolean notification)
		throws Exception {

		if (emailAddresses.length == 0) {
			return;
		}

		long ownerId = entry.getGroupId();
		int ownerType = PortletKeys.PREFS_OWNER_TYPE_GROUP;
		long plid = PortletKeys.PREFS_PLID_SHARED;
		String portletId = entry.getPortletId();
		String defaultPreferences = null;

		PortletPreferences preferences =
			portletPreferencesLocalService.getPreferences(
				entry.getCompanyId(), ownerId, ownerType, plid, portletId,
				defaultPreferences);

		String fromName = AdminUtil.getEmailFromName(preferences);
		String fromAddress = AdminUtil.getEmailFromAddress(preferences);

		String subject = null;
		String body = null;

		if (notification) {
			subject = AdminUtil.getEmailNotificationsSubject(preferences);
			body = AdminUtil.getEmailNotificationsBody(preferences);
		}
		else {
			subject = AdminUtil.getEmailDeliverySubject(preferences);
			body = AdminUtil.getEmailDeliveryBody(preferences);
		}

		subject = StringUtil.replace(
			subject,
			new String[] {
				"[$FROM_ADDRESS$]", "[$FROM_NAME$]", "[$PAGE_URL$]",
				"[$REPORT_NAME$]"
			},
			new String[] {
				fromName, fromAddress, entry.getPageURL(), reportName
			}
		);

		body = StringUtil.replace(
			body,
			new String[] {
				"[$FROM_ADDRESS$]", "[$FROM_NAME$]", "[$PAGE_URL$]",
				"[$REPORT_NAME$]"
			},
			new String[] {
				fromName, fromAddress, entry.getPageURL(), reportName
			}
		);

		SubscriptionSender subscriptionSender = new SubscriptionSender();

		if (!notification) {
			subscriptionSender.addFileAttachment(file, reportName);
		}

		subscriptionSender.setBody(body);
		subscriptionSender.setCompanyId(entry.getCompanyId());
		subscriptionSender.setFrom(fromAddress, fromName);
		subscriptionSender.setGroupId(entry.getGroupId());
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setMailId("reports_entry", entry.getEntryId());
		subscriptionSender.setReplyToAddress(fromAddress);
		subscriptionSender.setSubject(subject);
		subscriptionSender.setUserId(entry.getUserId());

		for (String emailAddress : emailAddresses) {
			subscriptionSender.addRuntimeSubscribers(
				emailAddress, emailAddress);
		}

		subscriptionSender.flushNotificationsAsync();
	}

	protected void scheduleEntry(Entry entry) throws PortalException {
		Trigger trigger = TriggerFactoryUtil.buildTrigger(
			TriggerType.CRON, entry.getJobName(),
			entry.getSchedulerRequestName(), entry.getStartDate(),
			entry.getEndDate(), entry.getRecurrence());

		Message message = new Message();

		message.put("entryId", entry.getEntryId());

		SchedulerEngineHelperUtil.schedule(
			trigger, StorageType.PERSISTED, null,
			"liferay/reports_scheduler_event", message, 0);
	}

	protected void validate(String emailNotifications, String emailDelivery)
		throws PortalException {

		for (String emailAddress : StringUtil.split(emailNotifications)) {
			if (!Validator.isEmailAddress(emailAddress)) {
				throw new EntryEmailNotificationsException();
			}
		}

		for (String emailAddress : StringUtil.split(emailDelivery)) {
			if (!Validator.isEmailAddress(emailAddress)) {
				throw new EntryEmailDeliveryException();
			}
		}
	}

}