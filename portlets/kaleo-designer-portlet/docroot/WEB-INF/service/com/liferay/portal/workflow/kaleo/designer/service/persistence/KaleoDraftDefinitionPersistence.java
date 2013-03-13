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

package com.liferay.portal.workflow.kaleo.designer.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;
import com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition;

/**
 * The persistence interface for the kaleo draft definition service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Eduardo Lundgren
 * @see KaleoDraftDefinitionPersistenceImpl
 * @see KaleoDraftDefinitionUtil
 * @generated
 */
public interface KaleoDraftDefinitionPersistence extends BasePersistence<KaleoDraftDefinition> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link KaleoDraftDefinitionUtil} to access the kaleo draft definition persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the kaleo draft definitions where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching kaleo draft definitions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the kaleo draft definitions where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.designer.model.impl.KaleoDraftDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of kaleo draft definitions
	* @param end the upper bound of the range of kaleo draft definitions (not inclusive)
	* @return the range of matching kaleo draft definitions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the kaleo draft definitions where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.designer.model.impl.KaleoDraftDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of kaleo draft definitions
	* @param end the upper bound of the range of kaleo draft definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo draft definitions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first kaleo draft definition in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo draft definition
	* @throws com.liferay.portal.workflow.kaleo.designer.NoSuchKaleoDraftDefinitionException if a matching kaleo draft definition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.designer.NoSuchKaleoDraftDefinitionException;

	/**
	* Returns the first kaleo draft definition in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo draft definition, or <code>null</code> if a matching kaleo draft definition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition fetchByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last kaleo draft definition in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo draft definition
	* @throws com.liferay.portal.workflow.kaleo.designer.NoSuchKaleoDraftDefinitionException if a matching kaleo draft definition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition findByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.designer.NoSuchKaleoDraftDefinitionException;

	/**
	* Returns the last kaleo draft definition in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo draft definition, or <code>null</code> if a matching kaleo draft definition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition fetchByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the kaleo draft definitions before and after the current kaleo draft definition in the ordered set where companyId = &#63;.
	*
	* @param kaleoDraftDefinitionId the primary key of the current kaleo draft definition
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo draft definition
	* @throws com.liferay.portal.workflow.kaleo.designer.NoSuchKaleoDraftDefinitionException if a kaleo draft definition with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition[] findByCompanyId_PrevAndNext(
		long kaleoDraftDefinitionId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.designer.NoSuchKaleoDraftDefinitionException;

	/**
	* Removes all the kaleo draft definitions where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of kaleo draft definitions where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching kaleo draft definitions
	* @throws SystemException if a system exception occurred
	*/
	public int countByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the kaleo draft definitions where companyId = &#63; and name = &#63; and version = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param version the version
	* @return the matching kaleo draft definitions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition> findByC_N_V(
		long companyId, java.lang.String name, int version)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the kaleo draft definitions where companyId = &#63; and name = &#63; and version = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.designer.model.impl.KaleoDraftDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param name the name
	* @param version the version
	* @param start the lower bound of the range of kaleo draft definitions
	* @param end the upper bound of the range of kaleo draft definitions (not inclusive)
	* @return the range of matching kaleo draft definitions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition> findByC_N_V(
		long companyId, java.lang.String name, int version, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the kaleo draft definitions where companyId = &#63; and name = &#63; and version = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.designer.model.impl.KaleoDraftDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param name the name
	* @param version the version
	* @param start the lower bound of the range of kaleo draft definitions
	* @param end the upper bound of the range of kaleo draft definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo draft definitions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition> findByC_N_V(
		long companyId, java.lang.String name, int version, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first kaleo draft definition in the ordered set where companyId = &#63; and name = &#63; and version = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo draft definition
	* @throws com.liferay.portal.workflow.kaleo.designer.NoSuchKaleoDraftDefinitionException if a matching kaleo draft definition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition findByC_N_V_First(
		long companyId, java.lang.String name, int version,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.designer.NoSuchKaleoDraftDefinitionException;

	/**
	* Returns the first kaleo draft definition in the ordered set where companyId = &#63; and name = &#63; and version = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo draft definition, or <code>null</code> if a matching kaleo draft definition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition fetchByC_N_V_First(
		long companyId, java.lang.String name, int version,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last kaleo draft definition in the ordered set where companyId = &#63; and name = &#63; and version = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo draft definition
	* @throws com.liferay.portal.workflow.kaleo.designer.NoSuchKaleoDraftDefinitionException if a matching kaleo draft definition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition findByC_N_V_Last(
		long companyId, java.lang.String name, int version,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.designer.NoSuchKaleoDraftDefinitionException;

	/**
	* Returns the last kaleo draft definition in the ordered set where companyId = &#63; and name = &#63; and version = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo draft definition, or <code>null</code> if a matching kaleo draft definition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition fetchByC_N_V_Last(
		long companyId, java.lang.String name, int version,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the kaleo draft definitions before and after the current kaleo draft definition in the ordered set where companyId = &#63; and name = &#63; and version = &#63;.
	*
	* @param kaleoDraftDefinitionId the primary key of the current kaleo draft definition
	* @param companyId the company ID
	* @param name the name
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo draft definition
	* @throws com.liferay.portal.workflow.kaleo.designer.NoSuchKaleoDraftDefinitionException if a kaleo draft definition with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition[] findByC_N_V_PrevAndNext(
		long kaleoDraftDefinitionId, long companyId, java.lang.String name,
		int version,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.designer.NoSuchKaleoDraftDefinitionException;

	/**
	* Removes all the kaleo draft definitions where companyId = &#63; and name = &#63; and version = &#63; from the database.
	*
	* @param companyId the company ID
	* @param name the name
	* @param version the version
	* @throws SystemException if a system exception occurred
	*/
	public void removeByC_N_V(long companyId, java.lang.String name, int version)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of kaleo draft definitions where companyId = &#63; and name = &#63; and version = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param version the version
	* @return the number of matching kaleo draft definitions
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_N_V(long companyId, java.lang.String name, int version)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the kaleo draft definition where companyId = &#63; and name = &#63; and version = &#63; and draftVersion = &#63; or throws a {@link com.liferay.portal.workflow.kaleo.designer.NoSuchKaleoDraftDefinitionException} if it could not be found.
	*
	* @param companyId the company ID
	* @param name the name
	* @param version the version
	* @param draftVersion the draft version
	* @return the matching kaleo draft definition
	* @throws com.liferay.portal.workflow.kaleo.designer.NoSuchKaleoDraftDefinitionException if a matching kaleo draft definition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition findByC_N_V_D(
		long companyId, java.lang.String name, int version, int draftVersion)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.designer.NoSuchKaleoDraftDefinitionException;

	/**
	* Returns the kaleo draft definition where companyId = &#63; and name = &#63; and version = &#63; and draftVersion = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param companyId the company ID
	* @param name the name
	* @param version the version
	* @param draftVersion the draft version
	* @return the matching kaleo draft definition, or <code>null</code> if a matching kaleo draft definition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition fetchByC_N_V_D(
		long companyId, java.lang.String name, int version, int draftVersion)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the kaleo draft definition where companyId = &#63; and name = &#63; and version = &#63; and draftVersion = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param companyId the company ID
	* @param name the name
	* @param version the version
	* @param draftVersion the draft version
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching kaleo draft definition, or <code>null</code> if a matching kaleo draft definition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition fetchByC_N_V_D(
		long companyId, java.lang.String name, int version, int draftVersion,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the kaleo draft definition where companyId = &#63; and name = &#63; and version = &#63; and draftVersion = &#63; from the database.
	*
	* @param companyId the company ID
	* @param name the name
	* @param version the version
	* @param draftVersion the draft version
	* @return the kaleo draft definition that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition removeByC_N_V_D(
		long companyId, java.lang.String name, int version, int draftVersion)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.designer.NoSuchKaleoDraftDefinitionException;

	/**
	* Returns the number of kaleo draft definitions where companyId = &#63; and name = &#63; and version = &#63; and draftVersion = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param version the version
	* @param draftVersion the draft version
	* @return the number of matching kaleo draft definitions
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_N_V_D(long companyId, java.lang.String name,
		int version, int draftVersion)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the kaleo draft definition in the entity cache if it is enabled.
	*
	* @param kaleoDraftDefinition the kaleo draft definition
	*/
	public void cacheResult(
		com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition kaleoDraftDefinition);

	/**
	* Caches the kaleo draft definitions in the entity cache if it is enabled.
	*
	* @param kaleoDraftDefinitions the kaleo draft definitions
	*/
	public void cacheResult(
		java.util.List<com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition> kaleoDraftDefinitions);

	/**
	* Creates a new kaleo draft definition with the primary key. Does not add the kaleo draft definition to the database.
	*
	* @param kaleoDraftDefinitionId the primary key for the new kaleo draft definition
	* @return the new kaleo draft definition
	*/
	public com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition create(
		long kaleoDraftDefinitionId);

	/**
	* Removes the kaleo draft definition with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoDraftDefinitionId the primary key of the kaleo draft definition
	* @return the kaleo draft definition that was removed
	* @throws com.liferay.portal.workflow.kaleo.designer.NoSuchKaleoDraftDefinitionException if a kaleo draft definition with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition remove(
		long kaleoDraftDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.designer.NoSuchKaleoDraftDefinitionException;

	public com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition updateImpl(
		com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition kaleoDraftDefinition)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the kaleo draft definition with the primary key or throws a {@link com.liferay.portal.workflow.kaleo.designer.NoSuchKaleoDraftDefinitionException} if it could not be found.
	*
	* @param kaleoDraftDefinitionId the primary key of the kaleo draft definition
	* @return the kaleo draft definition
	* @throws com.liferay.portal.workflow.kaleo.designer.NoSuchKaleoDraftDefinitionException if a kaleo draft definition with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition findByPrimaryKey(
		long kaleoDraftDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.designer.NoSuchKaleoDraftDefinitionException;

	/**
	* Returns the kaleo draft definition with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param kaleoDraftDefinitionId the primary key of the kaleo draft definition
	* @return the kaleo draft definition, or <code>null</code> if a kaleo draft definition with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition fetchByPrimaryKey(
		long kaleoDraftDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the kaleo draft definitions.
	*
	* @return the kaleo draft definitions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the kaleo draft definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.designer.model.impl.KaleoDraftDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kaleo draft definitions
	* @param end the upper bound of the range of kaleo draft definitions (not inclusive)
	* @return the range of kaleo draft definitions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the kaleo draft definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.designer.model.impl.KaleoDraftDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kaleo draft definitions
	* @param end the upper bound of the range of kaleo draft definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of kaleo draft definitions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the kaleo draft definitions from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of kaleo draft definitions.
	*
	* @return the number of kaleo draft definitions
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}