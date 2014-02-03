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

import com.liferay.sharepoint.connector.schema.marker.Clause;
import com.liferay.sharepoint.connector.schema.node.query.FieldRef;
import com.liferay.sharepoint.connector.schema.node.query.Query;
import com.liferay.sharepoint.connector.schema.node.query.Value;
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

	public AndJoin and(Clause leftClause, Clause rightClause) {
		return new AndJoin(leftClause, rightClause);
	}

	public BeginsWithOperator beginsWith(FieldRef fieldRef, Value value) {
		return new BeginsWithOperator(fieldRef, value);
	}

	public ContainsOperator contains(FieldRef fieldRef, Value value) {
		return new ContainsOperator(fieldRef, value);
	}

	public EqOperator eq(FieldRef fieldRef, Value value) {
		return new EqOperator(fieldRef, value);
	}

	public FieldRef fieldRef(String fieldName) {
		return new FieldRef(fieldName);
	}

	public GeqOperator geq(FieldRef fieldRef, Value value) {
		return new GeqOperator(fieldRef, value);
	}

	public GtOperator gt(FieldRef fieldRef, Value value) {
		return new GtOperator(fieldRef, value);
	}

	public InOperator in(FieldRef fieldRef, Value...values) {
		return new InOperator(fieldRef, values);
	}

	public IncludesOperator includes(FieldRef fieldRef, Value value) {
		return new IncludesOperator(fieldRef, value);
	}

	public IsNotNullOperator isNotNull(FieldRef fieldRef) {
		return new IsNotNullOperator(fieldRef);
	}

	public IsNullOperator isNull(FieldRef fieldRef) {
		return new IsNullOperator(fieldRef);
	}

	public LeqOperator leq(FieldRef fieldRef, Value value) {
		return new LeqOperator(fieldRef, value);
	}

	public LtOperator lt(FieldRef fieldRef, Value value) {
		return new LtOperator(fieldRef, value);
	}

	public NeqOperator neq(FieldRef fieldRef, Value value) {
		return new NeqOperator(fieldRef, value);
	}

	public NotIncludesOperator notIncludes(FieldRef fieldRef, Value value) {
		return new NotIncludesOperator(fieldRef, value);
	}

	public OrJoin or(Clause leftClause, Clause righClause) {
		return new OrJoin(leftClause, righClause);
	}

	public Value value(String value) {
		return new Value(value);
	}

	public Value value(Value.Type type, String value) {
		return new Value(type, value);
	}

	public Query where(Clause clause) {
		return new Query(clause);
	}

}