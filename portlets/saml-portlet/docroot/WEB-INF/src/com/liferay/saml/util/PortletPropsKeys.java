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

package com.liferay.saml.util;

/**
 * @author Mika Koivisto
 */
public interface PortletPropsKeys {

	public static final String SAML_ENABLED = "saml.enabled";

	public static final String SAML_ENTITY_ID = "saml.entity.id";

	public static final String SAML_IDP_ASSERTION_LIFETIME =
		"saml.idp.assertion.lifetime";

	public static final String SAML_IDP_AUTHN_REQUEST_SIGNATURE_REQUIRED =
		"saml.idp.authn.request.signature.required";

	public static final String SAML_IDP_METADATA_ATTRIBUTE_NAMES =
		"saml.idp.metadata.attribute.names";

	public static final String SAML_IDP_METADATA_ATTRIBUTE_RESOLVER =
		"saml.idp.metadata.attribute.resolver";

	public static final String SAML_IDP_METADATA_ATTRIBUTES_ENABLED =
		"saml.idp.metadata.attributes.enabled";

	public static final String SAML_IDP_METADATA_ATTRIBUTES_NAMESPACE_ENABLED =
		"saml.idp.metadata.attributes.namespace.enabled";

	public static final String SAML_IDP_METADATA_NAME_ID_ATTRIBUTE =
		"saml.idp.metadata.name.id.attribute";

	public static final String SAML_IDP_METADATA_NAME_ID_FORMAT =
		"saml.idp.metadata.name.id.format";

	public static final String SAML_IDP_METADATA_NAME_ID_RESOLVER =
		"saml.idp.metadata.name.id.resolver";

	public static final String SAML_IDP_METADATA_SALESFORCE_ATTRIBUTES_ENABLED =
		"saml.idp.metadata.salesforce.attributes.enabled";

	public static final String SAML_IDP_METADATA_SALESFORCE_LOGOUT_URL =
		"saml.idp.metadata.salesforce.logout.url";

	public static final String SAML_IDP_METADATA_SALESFORCE_SSO_START_PAGE =
		"saml.idp.metadata.salesforce.sso.start.page";

	public static final String SAML_IDP_METADATA_SESSION_KEEP_ALIVE_URL =
		"saml.idp.metadata.session.keep.alive.url";

	public static final String SAML_IDP_SESSION_MAXIMUM_AGE =
		"saml.idp.session.maximum.age";

	public static final String SAML_IDP_SESSION_TIMEOUT =
		"saml.idp.session.timeout";

	public static final String SAML_KEYSTORE_CREDENTIAL_PASSWORD =
		"saml.keystore.credential.password";

	public static final String SAML_KEYSTORE_MANAGER_IMPL =
		"saml.keystore.manager.impl";

	public static final String SAML_KEYSTORE_PASSWORD =
		"saml.keystore.password";

	public static final String SAML_KEYSTORE_PATH = "saml.keystore.path";

	public static final String SAML_KEYSTORE_TYPE = "saml.keystore.type";

	public static final String SAML_METADATA_MAX_REFRESH_DELAY =
		"saml.metadata.max.refresh.delay";

	public static final String SAML_METADATA_MIN_REFRESH_DELAY =
		"saml.metadata.min.refresh.delay";

	public static final String SAML_METADATA_PATHS = "saml.metadata.paths";

	public static final String SAML_REPLAY_CACHE_DURATION =
		"saml.replay.cache.duration";

	public static final String SAML_ROLE = "saml.role";

	public static final String SAML_SIGN_METADATA = "saml.sign.metadata";

	public static final String SAML_SP_ASSERTION_SIGNATURE_REQUIRED =
		"saml.sp.assertion.signature.required";

	public static final String SAML_SP_CLOCK_SKEW = "saml.sp.clock.skew";

	public static final String SAML_SP_DEFAULT_IDP_ENTITY_ID =
		"saml.sp.default.idp.entity.id";

	public static final String SAML_SP_LDAP_IMPORT_ENABLED =
		"saml.sp.ldap.import.enabled";

	public static final String SAML_SP_NAME_ID_FORMAT =
		"saml.sp.name.id.format";

	public static final String SAML_SP_SIGN_AUTHN_REQUEST =
		"saml.sp.sign.authn.request";

	public static final String SAML_SP_USER_ATTRIBUTE_MAPPINGS =
		"saml.sp.user.attribute.mappings";

	public static final String SAML_SSL_REQUIRED = "saml.ssl.required";

}