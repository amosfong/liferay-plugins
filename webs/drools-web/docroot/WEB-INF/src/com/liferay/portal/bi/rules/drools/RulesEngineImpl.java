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
import com.liferay.portal.kernel.bi.rules.QueryType;
import com.liferay.portal.kernel.bi.rules.RulesEngine;
import com.liferay.portal.kernel.bi.rules.RulesEngineException;
import com.liferay.portal.kernel.bi.rules.RulesLanguage;
import com.liferay.portal.kernel.bi.rules.RulesResourceRetriever;
import com.liferay.portal.kernel.resource.ResourceRetriever;
import com.liferay.portal.kernel.util.AggregateClassLoader;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseConfiguration;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderConfiguration;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.command.Command;
import org.drools.command.CommandFactory;
import org.drools.io.Resource;
import org.drools.io.ResourceFactory;
import org.drools.runtime.ExecutionResults;
import org.drools.runtime.StatelessKnowledgeSession;
import org.drools.runtime.rule.QueryResults;
import org.drools.runtime.rule.QueryResultsRow;

/**
 * @author Michael C. Han
 * @author Vihang Pathak
 * @author Brian Wing Shun Chan
 */
public class RulesEngineImpl implements RulesEngine {

	public void add(
			String domainName, RulesResourceRetriever rulesResourceRetriever,
			ClassLoader... classloaders)
		throws RulesEngineException {

		KnowledgeBase knowledgeBase = _knowledgeBaseMap.get(domainName);

		if (knowledgeBase == null) {
			knowledgeBase = createKnowledgeBase(
				rulesResourceRetriever, classloaders);

			_knowledgeBaseMap.put(domainName, knowledgeBase);
		}
	}

	@Override
	public boolean containsRuleDomain(String domainName) {
		return _knowledgeBaseMap.containsKey(domainName);
	}

	public void execute(
			RulesResourceRetriever rulesResourceRetriever, List<Fact<?>> facts,
			ClassLoader... classloaders)
		throws RulesEngineException {

		KnowledgeBase knowledgeBase = createKnowledgeBase(
			rulesResourceRetriever, classloaders);

		execute(facts, knowledgeBase);
	}

	public Map<String, ?> execute(
			RulesResourceRetriever rulesResourceRetriever, List<Fact<?>> facts,
			Query query, ClassLoader... classloaders)
		throws RulesEngineException {

		KnowledgeBase knowledgeBase = createKnowledgeBase(
			rulesResourceRetriever, classloaders);

		return execute(facts, knowledgeBase, query);
	}

	public void execute(
			String domainName, List<Fact<?>> facts, ClassLoader... classloaders)
		throws RulesEngineException {

		KnowledgeBase knowledgeBase = _knowledgeBaseMap.get(domainName);

		if (knowledgeBase == null) {
			throw new RulesEngineException(
				"No rules found for domain " + domainName);
		}

		execute(facts, knowledgeBase);
	}

	public Map<String, ?> execute(
			String domainName, List<Fact<?>> facts, Query query,
			ClassLoader... classloaders)
		throws RulesEngineException {

		KnowledgeBase knowledgeBase = _knowledgeBaseMap.get(domainName);

		if (knowledgeBase == null) {
			throw new RulesEngineException(
				"No rules found for domain " + domainName);
		}

		return execute(facts, knowledgeBase, query);
	}

	@Override
	public void remove(String domainName) {
		_knowledgeBaseMap.remove(domainName);
	}

	public void setDefaultRulesLanguage(String defaultRulesLanguage) {
		_defaultResourceType = ResourceType.getResourceType(
			defaultRulesLanguage);
	}

	public void setRulesLanguageMapping(Map<String, String> rulesLanguageMap) {
		for (Map.Entry<String, String> entry : rulesLanguageMap.entrySet()) {
			RulesLanguage rulesLanguage = RulesLanguage.valueOf(entry.getKey());
			ResourceType resourceType = ResourceType.getResourceType(
				entry.getValue());

			_resourceTypeMap.put(rulesLanguage, resourceType);
		}
	}

	public void update(
			String domainName, RulesResourceRetriever rulesResourceRetriever,
			ClassLoader... classloaders)
		throws RulesEngineException {

		remove(domainName);

		add(domainName, rulesResourceRetriever, classloaders);
	}

	protected ResourceType convertRulesLanguage(String rulesLanguage) {
		if (Validator.isNull(rulesLanguage)) {
			return _defaultResourceType;
		}

		ResourceType resourceType = _resourceTypeMap.get(
			RulesLanguage.valueOf(rulesLanguage));

		if (resourceType == null) {
			throw new IllegalArgumentException(
				rulesLanguage + " not supported by the Drools");
		}

		return resourceType;
	}

	protected KnowledgeBase createKnowledgeBase(
			RulesResourceRetriever retriever, ClassLoader... classloaders)
		throws RulesEngineException {

		try {
			ClassLoader classLoader = resolveClassLoaders(classloaders);

			KnowledgeBaseConfiguration knowledgeBaseConfiguration =
				KnowledgeBaseFactory.newKnowledgeBaseConfiguration(
					null, classLoader);

			KnowledgeBuilderConfiguration knowledgeBuilderConfiguration =
				KnowledgeBuilderFactory.newKnowledgeBuilderConfiguration(
					null, classLoader);

			KnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase(
				knowledgeBaseConfiguration);

			KnowledgeBuilder knowledgeBuilder =
				KnowledgeBuilderFactory.newKnowledgeBuilder(
					knowledgeBuilderConfiguration);

			ResourceType resourceType = convertRulesLanguage(
				retriever.getRulesLanguage());

			Set<ResourceRetriever> resourceRetrievers =
				retriever.getResourceRetrievers();

			for (ResourceRetriever resourceRetriever : resourceRetrievers) {
				Resource resource = ResourceFactory.newInputStreamResource(
					resourceRetriever.getInputStream());

				knowledgeBuilder.add(resource, resourceType);
			}

			if (knowledgeBuilder.hasErrors()) {
				throw new RulesEngineException(
					knowledgeBuilder.getErrors().toString());
			}

			knowledgeBase.addKnowledgePackages(
				knowledgeBuilder.getKnowledgePackages());

			return knowledgeBase;
		}
		catch (Exception e) {
			throw new RulesEngineException(
				"Unable to create knowledge base", e);
		}
	}

	protected void execute(List<Fact<?>> facts, KnowledgeBase knowledgeBase) {
		execute(facts, knowledgeBase, null);
	}

	protected Map<String, ?> execute(
		List<Fact<?>> facts, KnowledgeBase knowledgeBase, Query query) {

		QueryType queryType = null;

		if (query != null) {
			queryType = query.getQueryType();
		}

		if ((query != null) && queryType.equals(QueryType.CUSTOM) &&
			Validator.isNull(query.getQueryName())) {

			throw new IllegalArgumentException(
				"Cannot execute a custom query without a query string");
		}

		StatelessKnowledgeSession statelessKnowledgeSession =
			knowledgeBase.newStatelessKnowledgeSession();

		List<Command<?>> commands = new ArrayList<Command<?>>();

		List<String> identifiers = new ArrayList<String>(facts.size());

		for (Fact<?> fact : facts) {
			String identifier = fact.getIdentifier();

			Command<?> command = CommandFactory.newInsert(
				fact.getFactObject(), identifier);

			commands.add(command);

			identifiers.add(identifier);
		}

		if ((query != null) && queryType.equals(QueryType.CUSTOM)) {
			Command<?> command = CommandFactory.newQuery(
				query.getIdentifier(), query.getQueryName(),
				query.getArguments());

			commands.add(command);
		}

		ExecutionResults executionResults = statelessKnowledgeSession.execute(
			CommandFactory.newBatchExecution(commands));

		return processQueryResults(query, identifiers, executionResults);
	}

	protected Map<String, ?> processQueryResults(
		Query query, List<String> identifiers,
		ExecutionResults executionResults) {

		if (query == null) {
			return Collections.emptyMap();
		}

		Map<String, Object> returnValues = new HashMap<String, Object>();

		QueryType queryType = query.getQueryType();

		if (queryType.equals(QueryType.STANDARD)) {
			for (String identifier : identifiers) {
				Object fact = executionResults.getValue(identifier);

				returnValues.put(identifier, fact);
			}

			return returnValues;
		}

		QueryResults queryResults = (QueryResults)executionResults.getValue(
			query.getIdentifier());

		String[] queryResultsIdentifiers = queryResults.getIdentifiers();

		for (QueryResultsRow queryResultRow : queryResults) {
			for (String identifier : queryResultsIdentifiers) {
				Object returnValue = queryResultRow.get(identifier);

				if (returnValue != null) {
					returnValues.put(identifier, returnValue);
				}
			}
		}

		return returnValues;
	}

	protected ClassLoader resolveClassLoaders(ClassLoader... classloaders) {
		ClassLoader classLoader = AggregateClassLoader.getAggregateClassLoader(
			classloaders);

		if (classLoader != null) {
			classLoader = AggregateClassLoader.getAggregateClassLoader(
				new ClassLoader[] {getClass().getClassLoader(), classLoader});
		}

		return classLoader;
	}

	private ResourceType _defaultResourceType;
	private Map<String, KnowledgeBase> _knowledgeBaseMap =
		new ConcurrentHashMap<String, KnowledgeBase>();
	private Map<RulesLanguage, ResourceType> _resourceTypeMap =
		new ConcurrentHashMap<RulesLanguage, ResourceType>();

}