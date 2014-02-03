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

package com.liferay.sharepoint.connector.schema;

import com.liferay.sharepoint.connector.schema.node.query.QueryClause;
import com.liferay.sharepoint.connector.schema.node.query.QueryFieldRef;
import com.liferay.sharepoint.connector.schema.node.query.QueryNode;
import com.liferay.sharepoint.connector.schema.node.query.QueryValue;
import com.liferay.sharepoint.connector.schema.node.query.join.AndJoin;
import com.liferay.sharepoint.connector.schema.node.query.join.OrJoin;
import com.liferay.sharepoint.connector.schema.node.query.operator.BeginsWithOperator;
import com.liferay.sharepoint.connector.schema.node.query.operator.ContainsOperator;
import com.liferay.sharepoint.connector.schema.node.query.operator.EqOperator;
import com.liferay.sharepoint.connector.schema.node.query.operator.GeqOperator;
import com.liferay.sharepoint.connector.schema.node.query.operator.GtOperator;
import com.liferay.sharepoint.connector.schema.node.query.operator.InOperator;
import com.liferay.sharepoint.connector.schema.node.query.operator.IncludesOperator;
import com.liferay.sharepoint.connector.schema.node.query.operator.IsNotNullOperator;
import com.liferay.sharepoint.connector.schema.node.query.operator.IsNullOperator;
import com.liferay.sharepoint.connector.schema.node.query.operator.LeqOperator;
import com.liferay.sharepoint.connector.schema.node.query.operator.LtOperator;
import com.liferay.sharepoint.connector.schema.node.query.operator.NeqOperator;
import com.liferay.sharepoint.connector.schema.node.query.operator.NotIncludesOperator;

/**
 * @author Iván Zaera
 */
public class QueryBuilder {

	public AndJoin and(QueryClause leftClause, QueryClause rightClause) {
		return new AndJoin(leftClause, rightClause);
	}

	public BeginsWithOperator beginsWith(QueryFieldRef fieldRef, QueryValue value) {
		return new BeginsWithOperator(fieldRef, value);
	}

	public ContainsOperator contains(QueryFieldRef fieldRef, QueryValue value) {
		return new ContainsOperator(fieldRef, value);
	}

	public EqOperator eq(QueryFieldRef fieldRef, QueryValue value) {
		return new EqOperator(fieldRef, value);
	}

	public QueryFieldRef fieldRef(String fieldName) {
		return new QueryFieldRef(fieldName);
	}

	public GeqOperator geq(QueryFieldRef fieldRef, QueryValue value) {
		return new GeqOperator(fieldRef, value);
	}

	public GtOperator gt(QueryFieldRef fieldRef, QueryValue value) {
		return new GtOperator(fieldRef, value);
	}

	public InOperator in(QueryFieldRef fieldRef, QueryValue...values) {
		return new InOperator(fieldRef, values);
	}

	public IncludesOperator includes(QueryFieldRef fieldRef, QueryValue value) {
		return new IncludesOperator(fieldRef, value);
	}

	public IsNotNullOperator isNotNull(QueryFieldRef fieldRef) {
		return new IsNotNullOperator(fieldRef);
	}

	public IsNullOperator isNull(QueryFieldRef fieldRef) {
		return new IsNullOperator(fieldRef);
	}

	public LeqOperator leq(QueryFieldRef fieldRef, QueryValue value) {
		return new LeqOperator(fieldRef, value);
	}

	public LtOperator lt(QueryFieldRef fieldRef, QueryValue value) {
		return new LtOperator(fieldRef, value);
	}

	public NeqOperator neq(QueryFieldRef fieldRef, QueryValue value) {
		return new NeqOperator(fieldRef, value);
	}

	public NotIncludesOperator notIncludes(QueryFieldRef fieldRef, QueryValue value) {
		return new NotIncludesOperator(fieldRef, value);
	}

	public OrJoin or(QueryClause leftClause, QueryClause righClause) {
		return new OrJoin(leftClause, righClause);
	}

	public QueryValue value(String value) {
		return new QueryValue(value);
	}

	public QueryValue value(QueryValue.Type type, String value) {
		return new QueryValue(type, value);
	}

	public QueryNode where(QueryClause clause) {
		return new QueryNode(clause);
	}

}