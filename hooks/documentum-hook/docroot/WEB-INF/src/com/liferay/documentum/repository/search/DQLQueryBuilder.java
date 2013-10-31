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
import com.liferay.documentum.repository.model.Constants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BooleanClause;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.QueryTerm;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.TermQuery;
import com.liferay.portal.kernel.search.TermRangeQuery;
import com.liferay.portal.kernel.search.WildcardQuery;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.documentlibrary.util.comparator.RepositoryModelCreateDateComparator;
import com.liferay.portlet.documentlibrary.util.comparator.RepositoryModelModifiedDateComparator;
import com.liferay.portlet.documentlibrary.util.comparator.RepositoryModelNameComparator;
import com.liferay.portlet.documentlibrary.util.comparator.RepositoryModelSizeComparator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Mika Koivisto
 */
public class DQLQueryBuilder {

	public static String buildFileEntriesCountQueryString(
		DocumentumRepository documentRepository, long folderId,
		String[] mimeTypes) {

		StringBundler sb = new StringBundler(4);

		sb.append("SELECT COUNT(r_object_id) AS num_hits FROM ");
		sb.append(Constants.DM_SYSOBJECT);
		sb.append(" WHERE ");
		sb.append(
			buildFileEntriesWhereClause(
				documentRepository, folderId, mimeTypes, null));

		return sb.toString();
	}

	public static String buildFileEntriesSelectQueryString(
		DocumentumRepository documentRepository, long folderId,
		String[] mimeTypes, OrderByComparator obc) {

		StringBundler sb = new StringBundler(6);

		sb.append("SELECT ");
		sb.append(Constants.R_OBJECT_ID);
		sb.append(" FROM ");
		sb.append(Constants.DM_SYSOBJECT);
		sb.append(" WHERE ");
		sb.append(
			buildFileEntriesWhereClause(
				documentRepository, folderId, mimeTypes, obc));

		return sb.toString();
	}

	public static String buildFoldersAndFileEntriesCountQueryString(
		DocumentumRepository documentRepository, long folderId,
		String[] mimeTypes) {

		StringBundler sb = new StringBundler(4);

		sb.append("SELECT COUNT(r_object_id) AS num_hits FROM ");
		sb.append(Constants.DM_SYSOBJECT);
		sb.append(" WHERE ");
		sb.append(
			buildFoldersAndFileEntriesWhereClause(
				documentRepository, folderId, mimeTypes, null));

		return sb.toString();
	}

	public static String buildFoldersAndFileEntriesSelectQueryString(
		DocumentumRepository documentRepository, long folderId,
		String[] mimeTypes, OrderByComparator obc) {

		StringBundler sb = new StringBundler(6);

		sb.append("SELECT ");
		sb.append(Constants.R_OBJECT_ID);
		sb.append(" FROM ");
		sb.append(Constants.DM_SYSOBJECT);
		sb.append(" WHERE ");
		sb.append(
			buildFoldersAndFileEntriesWhereClause(
				documentRepository, folderId, mimeTypes, obc));

		return sb.toString();
	}

	public static String buildFoldersCountQueryString(
		DocumentumRepository documentRepository, long folderId) {

		StringBundler sb = new StringBundler(4);

		sb.append("SELECT COUNT(r_object_id) AS num_hits FROM ");
		sb.append(Constants.DM_FOLDER);
		sb.append(" WHERE ");
		sb.append(buildFoldersWhereClause(documentRepository, folderId, null));

		return sb.toString();
	}

	public static String buildFoldersSelectQueryString(
		DocumentumRepository documentRepository, long folderId,
		OrderByComparator obc) {

		StringBundler sb = new StringBundler(6);

		sb.append("SELECT ");
		sb.append(Constants.R_OBJECT_ID);
		sb.append(" FROM ");
		sb.append(Constants.DM_FOLDER);
		sb.append(" WHERE ");
		sb.append(buildFoldersWhereClause(documentRepository, folderId, obc));

		return sb.toString();
	}

	public static String buildSearchCountQueryString(
		DocumentumRepository documentRepository, SearchContext searchContext,
		Query query) {

		StringBundler sb = new StringBundler();

		sb.append("SELECT COUNT(r_object_id) AS num_hits FROM ");
		sb.append(Constants.DM_DOCUMENT);

		DQLConjunction dqlConjunction = new DQLConjunction();

		QueryConfig queryConfig = searchContext.getQueryConfig();

		_traverseQuery(documentRepository, dqlConjunction, query, queryConfig);

		if (!dqlConjunction.isEmpty()) {
			sb.append(" WHERE ");
			sb.append(dqlConjunction.toQueryFragment());
		}

		return sb.toString();
	}

	public static String buildSearchSelectQueryString(
		DocumentumRepository documentRepository, SearchContext searchContext,
		Query query) {

		StringBundler sb = new StringBundler();

		sb.append("SELECT ");
		sb.append(Constants.R_OBJECT_ID);
		sb.append(" FROM ");
		sb.append(Constants.DM_DOCUMENT);

		DQLConjunction dqlConjunction = new DQLConjunction();

		QueryConfig queryConfig = searchContext.getQueryConfig();

		_traverseQuery(documentRepository, dqlConjunction, query, queryConfig);

		if (!dqlConjunction.isEmpty()) {
			sb.append(" WHERE ");
			sb.append(dqlConjunction.toQueryFragment());
		}

		Sort[] sorts = searchContext.getSorts();

		if ((sorts != null) && (sorts.length > 0)) {
			sb.append(" ORDER BY ");

			for (int i = 0; i < sorts.length; i++) {
				Sort sort = sorts[i];

				if (i > 0) {
					sb.append(", ");
				}

				String fieldName = sort.getFieldName();

				sb.append(_dqlFields.get(fieldName));

				if (sort.isReverse()) {
					sb.append(" DESC");
				}
				else {
					sb.append(" ASC");
				}
			}
		}

		return sb.toString();
	}

	public static void main(String[] arguments) {
		System.out.println(
			buildFoldersAndFileEntriesSelectQueryString(
				null, 100, new String[] {"jpeg", "png"}, null));
	}

	protected static String buildFileEntriesWhereClause(
		DocumentumRepository documentRepository, long folderId,
		String[] mimeTypes, OrderByComparator obc) {

		StringBundler sb = new StringBundler();

		DQLConjunction dqlConjunction = new DQLConjunction();

		DQLCriterion dqlFolderCriterion = _buildFieldExpression(
			documentRepository, Field.FOLDER_ID, String.valueOf(folderId),
			DQLSimpleExpressionOperator.EQ, null);

		if (dqlFolderCriterion != null) {
			dqlConjunction.add(dqlFolderCriterion);
		}

		DQLCriterion dqlTypeCriterion = _buildFieldExpression(
			documentRepository, Constants.R_OBJECT_TYPE, Constants.DM_FOLDER,
			DQLSimpleExpressionOperator.NE, null);

		dqlConjunction.add(dqlTypeCriterion);

		if (mimeTypes != null) {
			DQLDisjunction dqlDisjunction = new DQLDisjunction();

			for (String mimeType : mimeTypes) {
				dqlDisjunction.add(
					_buildFieldExpression(
						documentRepository, Constants.A_CONTENT_TYPE, mimeType,
						DQLSimpleExpressionOperator.EQ, null));
			}

			if (!dqlDisjunction.isEmpty()) {
				dqlConjunction.add(dqlDisjunction);
			}
		}

		sb.append(dqlConjunction.toQueryFragment());

		if ((obc != null) &&
			((obc instanceof RepositoryModelCreateDateComparator) ||
			 (obc instanceof RepositoryModelModifiedDateComparator) ||
			 (obc instanceof RepositoryModelNameComparator) ||
			 (obc instanceof RepositoryModelSizeComparator))) {

			String[] orderByFields = obc.getOrderByConditionFields();

			if (orderByFields.length > 0) {
				sb.append(" ORDER BY ");
			}

			for (int i = 0; i < orderByFields.length; i++) {
				String fieldName = orderByFields[i];

				if (i > 0) {
					sb.append(", ");
				}

				sb.append(_dqlFields.get(fieldName));

				if (obc.isAscending()) {
					sb.append(" ASC");
				}
				else {
					sb.append(" DESC");
				}
			}
		}

		return sb.toString();
	}

	protected static String buildFoldersAndFileEntriesWhereClause(
		DocumentumRepository documentRepository, long folderId,
		String[] mimeTypes, OrderByComparator obc) {

		StringBundler sb = new StringBundler();

		DQLConjunction dqlConjunction = new DQLConjunction();

		DQLCriterion dqlCriterion = _buildFieldExpression(
			documentRepository, Field.FOLDER_ID, String.valueOf(folderId),
			DQLSimpleExpressionOperator.EQ, null);

		if (dqlCriterion != null) {
			dqlConjunction.add(dqlCriterion);
		}

		if (mimeTypes != null) {
			DQLDisjunction dqlDisjunction = new DQLDisjunction();

			dqlDisjunction.add(
				_buildFieldExpression(
					documentRepository, Constants.R_OBJECT_TYPE,
					Constants.DM_FOLDER, DQLSimpleExpressionOperator.EQ, null));

			for (String mimeType : mimeTypes) {
				dqlDisjunction.add(
					_buildFieldExpression(
						documentRepository, Constants.A_CONTENT_TYPE, mimeType,
						DQLSimpleExpressionOperator.EQ, null));
			}

			if (!dqlDisjunction.isEmpty()) {
				dqlConjunction.add(dqlDisjunction);
			}
		}

		sb.append(dqlConjunction.toQueryFragment());

		if ((obc != null) &&
			((obc instanceof RepositoryModelCreateDateComparator) ||
			 (obc instanceof RepositoryModelModifiedDateComparator) ||
			 (obc instanceof RepositoryModelNameComparator) ||
			 (obc instanceof RepositoryModelSizeComparator))) {

			String[] orderByFields = obc.getOrderByConditionFields();

			if (orderByFields.length > 0) {
				sb.append(" ORDER BY ");
			}

			for (int i = 0; i < orderByFields.length; i++) {
				String fieldName = orderByFields[i];

				if (i > 0) {
					sb.append(", ");
				}

				sb.append(_dqlFields.get(fieldName));

				if (obc.isAscending()) {
					sb.append(" ASC");
				}
				else {
					sb.append(" DESC");
				}
			}
		}

		return sb.toString();
	}

	protected static String buildFoldersWhereClause(
		DocumentumRepository documentRepository, long folderId,
		OrderByComparator obc) {

		StringBundler sb = new StringBundler();

		DQLConjunction dqlConjunction = new DQLConjunction();

		DQLCriterion dqlCriterion = _buildFieldExpression(
			documentRepository, Field.FOLDER_ID, String.valueOf(folderId),
			DQLSimpleExpressionOperator.EQ, null);

		dqlConjunction.add(dqlCriterion);

		sb.append(dqlConjunction.toQueryFragment());

		if ((obc != null) &&
			((obc instanceof RepositoryModelCreateDateComparator) ||
			 (obc instanceof RepositoryModelModifiedDateComparator) ||
			 (obc instanceof RepositoryModelNameComparator))) {

			String[] orderByFields = obc.getOrderByConditionFields();

			if (orderByFields.length > 0) {
				sb.append(" ORDER BY ");
			}

			for (int i = 0; i < orderByFields.length; i++) {
				String fieldName = orderByFields[i];

				if (i > 0) {
					sb.append(", ");
				}

				sb.append(_dqlFields.get(fieldName));

				if (obc.isAscending()) {
					sb.append(" ASC");
				}
				else {
					sb.append(" DESC");
				}
			}
		}

		return sb.toString();
	}

	private static DQLCriterion _buildFieldExpression(
		DocumentumRepository documentRepository, String field, String value,
		DQLSimpleExpressionOperator dqlSimpleExpressionOperator,
		QueryConfig queryConfig) {

		DQLCriterion dqlCriterion = null;

		boolean wildcard =
			DQLSimpleExpressionOperator.LIKE == dqlSimpleExpressionOperator;

		if (field.equals(Field.CREATE_DATE) ||
			field.equals(Field.MODIFIED_DATE)) {

			dqlCriterion = new DQLDateExpression(
				_dqlFields.get(field), value, dqlSimpleExpressionOperator);
		}
		else if (field.equals(Field.FOLDER_ID)) {
			long folderId = GetterUtil.getLong(value);

			try {
				String objectId = documentRepository.toFolderObjectId(folderId);

				if (objectId != null) {
					boolean decend = false;

					if (queryConfig != null) {
						decend = queryConfig.isSearchSubfolders();
					}

					dqlCriterion = new DQLInFolderExpression(objectId, decend);
				}
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		else if (field.equals(Field.USER_ID)) {
			try {
				long userId = GetterUtil.getLong(value);

				User user = UserLocalServiceUtil.getUserById(userId);

				String screenName = DQLParameterValueUtil.formatParameterValue(
					field, user.getScreenName(), wildcard);

				dqlCriterion = new DQLSimpleExpression(
					Constants.R_CREATOR_NAME, screenName,
					dqlSimpleExpressionOperator);
			}
			catch (PortalException pe) {
				if (_log.isWarnEnabled()) {
					_log.warn(pe, pe);
				}
			}
			catch (SystemException se) {
				_log.error(se, se);
			}
		}
		else if (field.equals(Field.USER_NAME)) {
			value = DQLParameterValueUtil.formatParameterValue(
				field, value, wildcard);

			dqlCriterion = new DQLSimpleExpression(
				Constants.R_CREATOR_NAME, value, dqlSimpleExpressionOperator);
		}
		else {
			value = DQLParameterValueUtil.formatParameterValue(
				field, value, wildcard);

			String dqlField = _dqlFields.get(field);

			if (Validator.isNull(dqlField)) {
				dqlField = field;
			}

			dqlCriterion = new DQLSimpleExpression(
				dqlField, value, dqlSimpleExpressionOperator);
		}

		return dqlCriterion;
	}

	private static void _traverseQuery(
		DocumentumRepository documentRepository,
		DQLJunction criterionDQLJunction, Query query,
		QueryConfig queryConfig) {

		if (query instanceof BooleanQuery) {
			BooleanQuery booleanQuery = (BooleanQuery)query;

			List<BooleanClause> booleanClauses = booleanQuery.clauses();
			DQLConjunction anyDQLConjunction = new DQLConjunction();
			DQLConjunction notDQLConjunction = new DQLConjunction();
			DQLDisjunction dqlDisjunction = new DQLDisjunction();

			for (BooleanClause booleanClause : booleanClauses) {
				DQLJunction dqlJunction = dqlDisjunction;

				BooleanClauseOccur booleanClauseOccur =
					booleanClause.getBooleanClauseOccur();

				if (booleanClauseOccur.equals(BooleanClauseOccur.MUST)) {
					dqlJunction = anyDQLConjunction;
				}
				else if (booleanClauseOccur.equals(
							BooleanClauseOccur.MUST_NOT)) {

					dqlJunction = notDQLConjunction;
				}

				Query booleanClauseQuery = booleanClause.getQuery();

				_traverseQuery(
					documentRepository, dqlJunction, booleanClauseQuery,
					queryConfig);
			}

			if (!anyDQLConjunction.isEmpty()) {
				criterionDQLJunction.add(anyDQLConjunction);
			}

			if (!dqlDisjunction.isEmpty()) {
				criterionDQLJunction.add(dqlDisjunction);
			}

			if (!notDQLConjunction.isEmpty()) {
				criterionDQLJunction.add(
					new DQLNotExpression(notDQLConjunction));
			}
		}
		else if (query instanceof TermQuery) {
			TermQuery termQuery = (TermQuery)query;

			QueryTerm queryTerm = termQuery.getQueryTerm();

			if (!_supportedFields.contains(queryTerm.getField())) {
				return;
			}

			DQLCriterion dqlExpression = _buildFieldExpression(
				documentRepository, queryTerm.getField(), queryTerm.getValue(),
				DQLSimpleExpressionOperator.EQ, queryConfig);

			if (dqlExpression != null) {
				criterionDQLJunction.add(dqlExpression);
			}
		}
		else if (query instanceof TermRangeQuery) {
			TermRangeQuery termRangeQuery = (TermRangeQuery)query;

			if (!_supportedFields.contains(termRangeQuery.getField())) {
				return;
			}

			String fieldName = termRangeQuery.getField();

			String dqlField = _dqlFields.get(fieldName);
			String dqlLowerTerm = DQLParameterValueUtil.formatParameterValue(
				fieldName, termRangeQuery.getLowerTerm());
			String dqlUpperTerm = DQLParameterValueUtil.formatParameterValue(
				fieldName, termRangeQuery.getUpperTerm());

			DQLCriterion dqlCriterion = new DQLBetweenExpression(
				dqlField, dqlLowerTerm, dqlUpperTerm,
				termRangeQuery.includesLower(), termRangeQuery.includesUpper());

			criterionDQLJunction.add(dqlCriterion);
		}
		else if (query instanceof WildcardQuery) {
			WildcardQuery wildcardQuery = (WildcardQuery)query;

			QueryTerm queryTerm = wildcardQuery.getQueryTerm();

			if (!_supportedFields.contains(queryTerm.getField())) {
				return;
			}

			DQLCriterion dqlCriterion = _buildFieldExpression(
				documentRepository, queryTerm.getField(), queryTerm.getValue(),
				DQLSimpleExpressionOperator.LIKE, queryConfig);

			if (dqlCriterion != null) {
				criterionDQLJunction.add(dqlCriterion);
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(DQLQueryBuilder.class);

	private static Map<String, String> _dqlFields;
	private static Set<String> _supportedFields;

	static {
		_dqlFields = new HashMap<String, String>();

		_dqlFields.put(Field.CREATE_DATE, Constants.R_CREATION_DATE);
		_dqlFields.put(Field.MODIFIED_DATE, Constants.R_MODIFY_DATE);
		_dqlFields.put(Field.NAME, Constants.OBJECT_NAME);
		_dqlFields.put(Field.TITLE, Constants.OBJECT_NAME);
		_dqlFields.put(Field.USER_NAME, Constants.R_CREATOR_NAME);
		_dqlFields.put("modifiedDate", Constants.R_MODIFY_DATE);
		_dqlFields.put("size_", Constants.R_CONTENT_SIZE);

		_supportedFields = new HashSet<String>();

		_supportedFields.add(Field.CREATE_DATE);
		_supportedFields.add(Field.FOLDER_ID);
		_supportedFields.add(Field.MODIFIED_DATE);
		_supportedFields.add(Field.NAME);
		_supportedFields.add(Field.TITLE);
		_supportedFields.add(Field.USER_ID);
		_supportedFields.add(Field.USER_NAME);
	}

}