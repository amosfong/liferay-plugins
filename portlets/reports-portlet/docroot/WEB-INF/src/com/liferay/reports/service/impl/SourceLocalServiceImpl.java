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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Junction;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.SystemEventConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.reports.SourceDriverClassNameException;
import com.liferay.reports.SourceLoginException;
import com.liferay.reports.SourceNameException;
import com.liferay.reports.SourceURLException;
import com.liferay.reports.model.Source;
import com.liferay.reports.service.base.SourceLocalServiceBaseImpl;
import com.liferay.reports.util.ReportsUtil;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 * @author Gavin Wan
 */
public class SourceLocalServiceImpl extends SourceLocalServiceBaseImpl {

	public Source addSource(
			long userId, long groupId, Map<Locale, String> nameMap,
			String driverClassName, String driverUrl, String driverUserName,
			String driverPassword, ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Source

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		validate(
			nameMap, driverClassName, driverUrl, driverUserName,
			driverPassword);

		long sourceId = counterLocalService.increment();

		Source source = sourceLocalService.createSource(sourceId);

		source.setUuid(serviceContext.getUuid());
		source.setGroupId(groupId);
		source.setCompanyId(user.getCompanyId());
		source.setUserId(user.getUserId());
		source.setUserName(user.getFullName());
		source.setCreateDate(serviceContext.getCreateDate(now));
		source.setModifiedDate(serviceContext.getModifiedDate(now));
		source.setNameMap(nameMap);
		source.setDriverClassName(driverClassName);
		source.setDriverUrl(driverUrl);
		source.setDriverUserName(driverUserName);
		source.setDriverPassword(driverPassword);

		sourcePersistence.update(source);

		// Resources

		resourceLocalService.addModelResources(source, serviceContext);

		return source;
	}

	@Override
	public Source deleteSource(long sourceId)
		throws PortalException, SystemException {

		Source source = sourcePersistence.findByPrimaryKey(sourceId);

		return sourceLocalService.deleteSource(source);
	}

	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public Source deleteSource(Source source)
		throws PortalException, SystemException {

		// Source

		sourcePersistence.remove(source);

		// Resources

		resourceLocalService.deleteResource(
			source.getCompanyId(), Source.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL, source.getSourceId());

		return source;
	}

	@Override
	public void deleteSources(long groupId)
		throws PortalException, SystemException {

		List<Source> sources = sourcePersistence.findByGroupId(groupId);

		for (Source source : sources) {
			sourceLocalService.deleteSource(source);
		}
	}

	@Override
	public Source getSource(long sourceId)
		throws PortalException, SystemException {

		return sourcePersistence.findByPrimaryKey(sourceId);
	}

	public List<Source> getSources(
			long groupId, String name, String driverUrl, boolean andSearch,
			int start, int end, OrderByComparator orderByComparator)
		throws SystemException {

		DynamicQuery dynamicQuery = buildDynamicQuery(
			groupId, name, driverUrl, andSearch);

		return dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	public int getSourcesCount(
			long groupId, String name, String driverUrl, boolean andSearch)
		throws SystemException {

		DynamicQuery dynamicQuery = buildDynamicQuery(
			groupId, name, driverUrl, andSearch);

		return (int)dynamicQueryCount(dynamicQuery);
	}

	public Source updateSource(
			long sourceId, Map<Locale, String> nameMap, String driverClassName,
			String driverUrl, String driverUserName, String driverPassword,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Source

		Source source = sourcePersistence.findByPrimaryKey(sourceId);

		if (Validator.isNull(driverPassword)) {
			driverPassword = source.getDriverPassword();
		}

		validate(
			nameMap, driverClassName, driverUrl, driverUserName,
			driverPassword);

		source.setModifiedDate(serviceContext.getModifiedDate(null));
		source.setNameMap(nameMap);
		source.setDriverClassName(driverClassName);
		source.setDriverUrl(driverUrl);
		source.setDriverUserName(driverUserName);
		source.setDriverPassword(driverPassword);

		sourcePersistence.update(source);

		return source;
	}

	protected DynamicQuery buildDynamicQuery(
		long groupId, String name, String driverUrl, boolean andSearch) {

		Junction junction = null;

		if (andSearch) {
			junction = RestrictionsFactoryUtil.conjunction();
		}
		else {
			junction = RestrictionsFactoryUtil.disjunction();
		}

		if (Validator.isNotNull(name)) {
			Property property = PropertyFactoryUtil.forName("name");

			String value = StringPool.PERCENT + name + StringPool.PERCENT;

			junction.add(property.like(value));
		}

		if (Validator.isNotNull(driverUrl)) {
			Property property = PropertyFactoryUtil.forName("driverUrl");

			String value = StringPool.PERCENT + driverUrl + StringPool.PERCENT;

			junction.add(property.like(value));
		}

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Source.class, getClassLoader());

		if (groupId > 0) {
			Property property = PropertyFactoryUtil.forName("groupId");

			dynamicQuery.add(property.eq(groupId));
		}

		dynamicQuery.add(junction);

		return dynamicQuery;
	}

	protected void validate(
			Map<Locale, String> nameMap, String driverClassName,
			String driverUrl, String driverUserName, String driverPassword)
		throws PortalException {

		Locale locale = LocaleUtil.getDefault();

		String name = nameMap.get(locale);

		if (Validator.isNull(name)) {
			throw new SourceNameException();
		}

		if (Validator.isNull(driverClassName)) {
			throw new SourceDriverClassNameException();
		}

		try {
			Class.forName(
				driverClassName, true, PortalClassLoaderUtil.getClassLoader());
		}
		catch (ClassNotFoundException cnfe) {
			throw new SourceDriverClassNameException();
		}

		if (Validator.isNull(driverUrl)) {
			throw new SourceURLException();
		}

		if (Validator.isNull(driverUserName)) {
			throw new SourceLoginException();
		}

		if (Validator.isNull(driverPassword)) {
			throw new SourceLoginException();
		}

		ReportsUtil.validateJDBCConnection(
			driverClassName, driverUrl, driverUserName, driverPassword);
	}

}