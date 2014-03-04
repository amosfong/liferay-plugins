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

package com.liferay.skinny.service.impl;

import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.security.ac.AccessControlled;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portlet.dynamicdatalists.model.DDLRecord;
import com.liferay.portlet.dynamicdatalists.model.DDLRecordSet;
import com.liferay.portlet.dynamicdatalists.service.DDLRecordSetLocalServiceUtil;
import com.liferay.portlet.dynamicdatamapping.storage.Fields;
import com.liferay.portlet.journal.NoSuchArticleException;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.skinny.model.SkinnyDDLRecord;
import com.liferay.skinny.model.SkinnyJournalArticle;
import com.liferay.skinny.service.base.SkinnyServiceBaseImpl;

import java.io.Serializable;

import java.text.Format;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author James Falkner
 * @author Amos Fong
 */
public class SkinnyServiceImpl extends SkinnyServiceBaseImpl {

	@Override
	@AccessControlled(guestAccessEnabled = true)
	public List<SkinnyDDLRecord> getSkinnyDDLRecords(long ddlRecordSetId)
		throws Exception {

		List<SkinnyDDLRecord> skinnyDDLRecords =
			new ArrayList<SkinnyDDLRecord>();

		DDLRecordSet ddlRecordSet = ddlRecordSetLocalService.getRecordSet(
			ddlRecordSetId);

		if (getPermissionChecker().hasPermission(
				ddlRecordSet.getGroupId(), DDLRecordSet.class.getName(),
				ddlRecordSet.getRecordSetId(), "VIEW")) {

			for (DDLRecord ddlRecord : ddlRecordSet.getRecords()) {
				SkinnyDDLRecord skinnyDDLRecord = getSkinnyDDLRecord(ddlRecord);

				skinnyDDLRecords.add(skinnyDDLRecord);
			}
		}

		return skinnyDDLRecords;
	}

	@Override
	@AccessControlled(guestAccessEnabled = true)
	public List<SkinnyJournalArticle> getSkinnyJournalArticles(
			long companyId, String groupName, String journalStructureId,
			String locale)
		throws Exception {

		List<SkinnyJournalArticle> skinnyJournalArticles =
			new ArrayList<SkinnyJournalArticle>();

		Group group = groupLocalService.getGroup(companyId, groupName);

		Set<String> journalArticleIds = new HashSet<String>();

		List<JournalArticle> journalArticles =
			journalArticleLocalService.getStructureArticles(
				group.getGroupId(), journalStructureId);

		for (JournalArticle journalArticle : journalArticles) {
			if (journalArticleIds.contains(journalArticle.getArticleId())) {
				continue;
			}

			journalArticleIds.add(journalArticle.getArticleId());

			try {
				if (getPermissionChecker().hasPermission(
						group.getGroupId(), JournalArticle.class.getName(),
						journalArticle.getResourcePrimKey(), "VIEW")) {

							JournalArticle latestJournalArticle =
								journalArticleLocalService.getLatestArticle(
									group.getGroupId(), journalArticle.getArticleId(),
									WorkflowConstants.STATUS_APPROVED);

							SkinnyJournalArticle skinnyJournalArticle =
								getSkinnyJournalArticle(latestJournalArticle, locale);

							skinnyJournalArticles.add(skinnyJournalArticle);
				}
			}
			catch (NoSuchArticleException nsae) {
			}
		}

		return skinnyJournalArticles;
	}

	protected SkinnyDDLRecord getSkinnyDDLRecord(DDLRecord ddlRecord)
		throws Exception {

		SkinnyDDLRecord skinnyDDLRecord = new SkinnyDDLRecord();

		Fields fields = ddlRecord.getFields();

		for (String fieldName : fields.getNames()) {

			Serializable rawFieldValue = ddlRecord.getFieldValue(fieldName);

			String fieldValue = GetterUtil.getString(rawFieldValue);

			String fieldDataType = GetterUtil.getString(
				ddlRecord.getFieldDataType(fieldName));

			if (fieldDataType.equals("date")) {
				fieldValue = _format.format(rawFieldValue);
			} else if (fieldDataType.equals("boolean")) {
				fieldValue = Boolean.toString(GetterUtil.getBoolean(rawFieldValue));
			}

			skinnyDDLRecord.addDynamicElement(fieldName, fieldValue);
		}

		return skinnyDDLRecord;
	}

	protected SkinnyJournalArticle getSkinnyJournalArticle(
			JournalArticle journalArticle, String locale)
		throws Exception {

		SkinnyJournalArticle skinnyJournalArticle = new SkinnyJournalArticle();

		String content = null;

		if (ArrayUtil.contains(journalArticle.getAvailableLocales(), locale)) {
			content = journalArticle.getContentByLocale(locale);
		}
		else {
			content = journalArticle.getContent();
		}

		Document document = SAXReaderUtil.read(content);

		Element rootElement = document.getRootElement();

		populateSkinnyJournalArticle(skinnyJournalArticle, rootElement);

		return skinnyJournalArticle;
	}

	protected void populateSkinnyJournalArticle(
		SkinnyJournalArticle skinnyJournalArticle, Element parentElement) {

		List<Element> elements = parentElement.elements();

		for (Element element : elements) {
			String elementName = element.getName();

			if (elementName.equals("dynamic-element")) {
				Element dynamicElementElement = element.element(
					"dynamic-content");

				if (dynamicElementElement != null) {
					skinnyJournalArticle.addDynamicElement(
						element.attributeValue("name"),
						dynamicElementElement.getTextTrim());
				}
			}
			else {
				populateSkinnyJournalArticle(skinnyJournalArticle, element);
			}
		}
	}

	private Format _format = FastDateFormatFactoryUtil.getSimpleDateFormat(
		"yyyy-MM-dd");

}