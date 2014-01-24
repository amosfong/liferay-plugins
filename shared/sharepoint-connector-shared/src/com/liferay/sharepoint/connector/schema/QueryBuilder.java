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
import com.liferay.sharepoint.connector.schema.node.join.And;
import com.liferay.sharepoint.connector.schema.node.join.Or;
import com.liferay.sharepoint.connector.schema.node.operator.BeginsWith;
import com.liferay.sharepoint.connector.schema.node.operator.Contains;
import com.liferay.sharepoint.connector.schema.node.operator.Eq;
import com.liferay.sharepoint.connector.schema.node.operator.Geq;
import com.liferay.sharepoint.connector.schema.node.operator.Gt;
import com.liferay.sharepoint.connector.schema.node.operator.In;
import com.liferay.sharepoint.connector.schema.node.operator.Includes;
import com.liferay.sharepoint.connector.schema.node.operator.IsNotNull;
import com.liferay.sharepoint.connector.schema.node.operator.IsNull;
import com.liferay.sharepoint.connector.schema.node.operator.Leq;
import com.liferay.sharepoint.connector.schema.node.operator.Lt;
import com.liferay.sharepoint.connector.schema.node.operator.Neq;
import com.liferay.sharepoint.connector.schema.node.operator.NotIncludes;
import com.liferay.sharepoint.connector.schema.node.query.Query;
import com.liferay.sharepoint.connector.schema.node.value.Value;
import com.liferay.sharepoint.connector.schema.node.view.FieldRef;

/**
 * @author Iván Zaera
 */
public class QueryBuilder {

	public And and( Clause left, Clause right ) {
		return new And(left, right);
	}

	public BeginsWith beginsWith( FieldRef fieldRef, Value value ) {
		return new BeginsWith(fieldRef, value);
	}

	public Contains contains( FieldRef fieldRef, Value value ) {
		return new Contains(fieldRef, value);
	}

	public Eq eq( FieldRef fieldRef, Value value ) {
		return new Eq(fieldRef, value);
	}

	public FieldRef fieldRef( String fieldName ) {
		return new FieldRef(fieldName);
	}

	public Geq geq( FieldRef fieldRef, Value value ) {
		return new Geq(fieldRef, value);
	}

	public Gt gt( FieldRef fieldRef, Value value ) {
		return new Gt(fieldRef, value);
	}

	public In in( FieldRef fieldRef, Value...values ) {
		return new In(fieldRef, values);
	}

	public Includes includes( FieldRef fieldRef, Value value ) {
		return new Includes(fieldRef, value);
	}

	public IsNotNull isNotNull( FieldRef fieldRef ) {
		return new IsNotNull(fieldRef);
	}

	public IsNull isNull( FieldRef fieldRef ) {
		return new IsNull(fieldRef);
	}

	public Leq leq( FieldRef fieldRef, Value value ) {
		return new Leq(fieldRef, value);
	}

	public Lt lt( FieldRef fieldRef, Value value ) {
		return new Lt(fieldRef, value);
	}

	public Neq neq( FieldRef fieldRef, Value value ) {
		return new Neq(fieldRef, value);
	}

	public NotIncludes notIncludes( FieldRef fieldRef, Value value ) {
		return new NotIncludes(fieldRef, value);
	}

	public Or or( Clause left, Clause right ) {
		return new Or(left, right);
	}

	public Value value( String value ) {
		return new Value(value);
	}

	public Value value( Value.Type type, String value ) {
		return new Value(type, value);
	}

	public Query where( Clause criterion ) {
		return new Query(criterion);
	}

}