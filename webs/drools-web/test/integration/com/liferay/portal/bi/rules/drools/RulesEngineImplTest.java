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

package com.liferay.portal.bi.rules.drools;

import com.liferay.portal.kernel.bi.rules.Fact;
import com.liferay.portal.kernel.bi.rules.Query;
import com.liferay.portal.kernel.bi.rules.RulesResourceRetriever;
import com.liferay.portal.kernel.resource.StringResourceRetriever;
import com.liferay.portal.kernel.test.TestCase;

import java.io.File;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Michael C. Han
 * @author Brian Wing Shun Chan
 */
public class RulesEngineImplTest extends TestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		_rulesEngineImpl = new RulesEngineImpl();

		_rulesEngineImpl.setDefaultRulesLanguage("DRL");

		File rulesFile = new File(
			"webs/drools-web/test/integration/com/liferay/portal/bi/rules/" +
				"drools/test.drl");

		String rules = FileUtils.readFileToString(rulesFile);

		_rulesResourceRetriever = new RulesResourceRetriever(
			new StringResourceRetriever(rules));
	}

	@Test
	public void testAdd() throws Exception {
		_rulesEngineImpl.add("testDomainName", _rulesResourceRetriever);

		assertTrue(_rulesEngineImpl.containsRuleDomain("testDomainName"));
	}

	@Test
	public void testExecuteWithPrecompiledRuleAge30() throws Exception {
		UserProfile userProfile = new UserProfile();

		userProfile.setAge(18);

		List<Fact<?>> facts = new ArrayList<Fact<?>>();

		facts.add(new Fact<UserProfile>("userProfile", userProfile));

		_rulesEngineImpl.add("testDomainName", _rulesResourceRetriever);

		_rulesEngineImpl.execute("testDomainName", facts);

		assertEquals(30, userProfile.getAge());
	}

	@Test
	public void testExecuteWithPrecompiledRuleAge50() throws Exception {
		_rulesEngineImpl.add("testDomainName", _rulesResourceRetriever);

		UserProfile userProfile = new UserProfile();

		userProfile.setAge(50);

		List<Fact<?>> facts = new ArrayList<Fact<?>>();

		facts.add(new Fact<UserProfile>("userProfile", userProfile));

		_rulesEngineImpl.execute("testDomainName", facts);

		assertEquals(50, userProfile.getAge());
	}

	@Test
	public void testExecuteWithResultsWithTemporaryRuleAge30()
		throws Exception {

		UserProfile userProfile = new UserProfile();

		userProfile.setAge(18);

		List<Fact<?>> facts = new ArrayList<Fact<?>>();

		facts.add(new Fact<UserProfile>("userProfile", userProfile));

		Map<String, ?> results = _rulesEngineImpl.execute(
			_rulesResourceRetriever, facts, Query.createStandardQuery());

		assertEquals(1, results.size());

		userProfile = (UserProfile)results.get("userProfile");

		assertEquals(30, userProfile.getAge());
	}

	@Test
	public void testExecuteWithResultsWithTemporaryRuleAge50()
		throws Exception {

		UserProfile userProfile = new UserProfile();

		userProfile.setAge(50);

		List<Fact<?>> facts = new ArrayList<Fact<?>>();

		facts.add(new Fact<UserProfile>("userProfile", userProfile));

		Map<String, ?> results = _rulesEngineImpl.execute(
			_rulesResourceRetriever, facts, Query.createStandardQuery());

		assertEquals(1, results.size());

		userProfile = (UserProfile)results.get("userProfile");

		assertEquals(50, userProfile.getAge());
	}

	@Test
	public void testExecuteWithTemporaryRuleAge30() throws Exception {
		UserProfile userProfile = new UserProfile();

		userProfile.setAge(18);

		List<Fact<?>> facts = new ArrayList<Fact<?>>();

		facts.add(new Fact<UserProfile>("userProfile", userProfile));

		_rulesEngineImpl.execute(_rulesResourceRetriever, facts);

		assertEquals(30, userProfile.getAge());
	}

	@Test
	public void testExecuteWithTemporaryRuleAge50() throws Exception {
		UserProfile userProfile = new UserProfile();

		userProfile.setAge(50);

		List<Fact<?>> facts = new ArrayList<Fact<?>>();

		facts.add(new Fact<UserProfile>("userProfile", userProfile));

		_rulesEngineImpl.execute(_rulesResourceRetriever, facts);

		assertEquals(50, userProfile.getAge());
	}

	private RulesEngineImpl _rulesEngineImpl;
	private RulesResourceRetriever _rulesResourceRetriever;

}