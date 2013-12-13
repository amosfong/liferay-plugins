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

package com.liferay.documentum.repository.search;

import com.liferay.documentum.repository.DocumentumRepository;
import com.liferay.portal.kernel.bean.BeanLocator;
import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.repository.search.RepositorySearchQueryBuilderUtil;
import com.liferay.portal.kernel.search.BaseSearchEngine;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.util.Props;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.repository.search.RepositorySearchQueryBuilderImpl;
import com.liferay.portal.search.generic.BooleanClauseFactoryImpl;
import com.liferay.portal.search.generic.BooleanQueryFactoryImpl;
import com.liferay.portal.search.generic.TermQueryFactoryImpl;
import com.liferay.portal.search.generic.TermRangeQueryFactoryImpl;
import com.liferay.portal.search.lucene.LuceneHelperUtil;
import com.liferay.portlet.documentlibrary.service.DLAppService;
import com.liferay.portlet.documentlibrary.service.DLAppServiceUtil;

import java.lang.reflect.Field;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.KeywordAnalyzer;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Mika Koivisto
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(LuceneHelperUtil.class)
public class DQLQueryBuilderTest extends PowerMockito {

	@Before
	public void setUp() {
		_beanLocator = mock(BeanLocator.class);

		PortalBeanLocatorUtil.setBeanLocator(_beanLocator);

		Props props = mock(Props.class);

		PropsUtil.setProps(props);

		when(
			props.get(PropsKeys.INDEX_READ_ONLY)
		).thenReturn(
			"true"
		);

		_documentumRepository = mock(DocumentumRepository.class);

		PowerMockito.mockStatic(LuceneHelperUtil.class);

		when(
			LuceneHelperUtil.getVersion()
		).thenReturn(
			org.apache.lucene.util.Version.LUCENE_35
		);

		RepositorySearchQueryBuilderImpl repositorySearchQueryBuilderImpl =
			new RepositorySearchQueryBuilderImpl();

		repositorySearchQueryBuilderImpl.setAnalyzer(new KeywordAnalyzer());

		RepositorySearchQueryBuilderUtil repositorySearchQueryBuilderUtil =
			new RepositorySearchQueryBuilderUtil();

		repositorySearchQueryBuilderUtil.setRepositorySearchQueryBuilder(
			repositorySearchQueryBuilderImpl);

		BaseSearchEngine searchEngine = new BaseSearchEngine();

		searchEngine.setBooleanClauseFactory(new BooleanClauseFactoryImpl());
		searchEngine.setBooleanQueryFactory(new BooleanQueryFactoryImpl());
		searchEngine.setLuceneBased(false);
		searchEngine.setTermQueryFactory(new TermQueryFactoryImpl());
		searchEngine.setTermRangeQueryFactory(new TermRangeQueryFactoryImpl());

		SearchEngineUtil.setSearchEngine(
			SearchEngineUtil.GENERIC_ENGINE_ID, searchEngine);
	}

	@After
	public void tearDown() {
		for (Class<?> serviceUtilClass : _serviceUtilClasses) {
			try {
				Field field = serviceUtilClass.getDeclaredField("_service");

				field.setAccessible(true);

				field.set(serviceUtilClass, null);
			}
			catch (Exception e) {
			}
		}
	}

	@Test
	public void testBooleanQuery() throws Exception {
		SearchContext searchContext = getSearchContext();

		searchContext.setKeywords("+test* -test.doc");

		BooleanQuery searchQuery =
			RepositorySearchQueryBuilderUtil.getFullQuery(searchContext);

		String dql = DQLQueryBuilder.buildSearchSelectQueryString(
			_documentumRepository, searchContext, searchQuery);

		assertQueryEquals(
			"(object_name LIKE 'test%' AND NOT(object_name = 'test.doc')) OR " +
				"(r_creator_name LIKE 'test%' AND NOT(r_creator_name = " +
					"'test.doc'))",
			dql);
	}

	@Test
	public void testFolderQuery() throws Exception {
		getService(DLAppServiceUtil.class, DLAppService.class);

		when(
			_documentumRepository.toFolderObjectId(Mockito.eq(1000l))
		).thenReturn(
			"1000"
		);

		SearchContext searchContext = getSearchContext();

		searchContext.setFolderIds(new long[] {1000});
		searchContext.setKeywords("test");

		BooleanQuery searchQuery =
			RepositorySearchQueryBuilderUtil.getFullQuery(searchContext);

		String dql = DQLQueryBuilder.buildSearchSelectQueryString(
			_documentumRepository, searchContext, searchQuery);

		assertQueryEquals(
			"(FOLDER(ID('1000'))) AND ((object_name = 'test') OR " +
				"(r_creator_name = 'test'))",
			dql);
	}

	@Test
	public void testSubfolderQuery() throws Exception {
		getService(DLAppServiceUtil.class, DLAppService.class);

		when(
			_documentumRepository.toFolderObjectId(Mockito.eq(1000l))
		).thenReturn(
			"1000"
		);

		SearchContext searchContext = getSearchContext();

		searchContext.setFolderIds(new long[] {1000});
		searchContext.setKeywords("test");

		BooleanQuery searchQuery =
			RepositorySearchQueryBuilderUtil.getFullQuery(searchContext);

		QueryConfig queryConfig = searchContext.getQueryConfig();

		queryConfig.setSearchSubfolders(true);

		String dql = DQLQueryBuilder.buildSearchSelectQueryString(
			_documentumRepository, searchContext, searchQuery);

		assertQueryEquals(
			"(FOLDER(ID('1000'), DESCEND)) AND ((object_name = 'test') OR " +
				"(r_creator_name = 'test'))",
			dql);
	}

	protected void assertQueryEquals(String where, String query) {
		Assert.assertEquals(_QUERY_PREFIX + where + _QUERY_POSTFIX, query);
	}

	protected SearchContext getSearchContext() {
		SearchContext searchContext = new SearchContext();

		searchContext.setSearchEngineId(SearchEngineUtil.GENERIC_ENGINE_ID);

		return searchContext;
	}

	protected <T> T getService(
		Class<?> serviceUtilClass, Class<T> serviceClass) {

		_serviceUtilClasses.add(serviceUtilClass);

		T service = mock(serviceClass);

		when(
			_beanLocator.locate(
				Mockito.eq(serviceClass.getName()))
		).thenReturn(
			service
		);

		return service;
	}

	private static final String _QUERY_POSTFIX = ")";

	private static final String _QUERY_PREFIX =
		"SELECT r_object_id FROM dm_document WHERE (";

	private BeanLocator _beanLocator;
	private DocumentumRepository _documentumRepository;
	private List<Class<?>> _serviceUtilClasses = new ArrayList<Class<?>>();

}