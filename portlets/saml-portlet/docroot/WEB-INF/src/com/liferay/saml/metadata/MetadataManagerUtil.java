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

package com.liferay.saml.metadata;

import javax.servlet.http.HttpServletRequest;

import org.opensaml.saml2.metadata.EntityDescriptor;
import org.opensaml.saml2.metadata.provider.MetadataProvider;
import org.opensaml.saml2.metadata.provider.MetadataProviderException;
import org.opensaml.ws.security.SecurityPolicyResolver;
import org.opensaml.xml.security.SecurityException;
import org.opensaml.xml.security.credential.Credential;
import org.opensaml.xml.signature.SignatureTrustEngine;

/**
 * @author Mika Koivisto
 */
public class MetadataManagerUtil {

	public static int getAssertionLifetime(String entityId) {
		return getMetadataManager().getAssertionLifetime(entityId);
	}

	public static String[] getAttributeNames(String entityId) {
		return getMetadataManager().getAttributeNames(entityId);
	}

	public static long getClockSkew() {
		return getMetadataManager().getClockSkew();
	}

	public static String getDefaultIdpEntityId() {
		return getMetadataManager().getDefaultIdpEntityId();
	}

	public static EntityDescriptor getEntityDescriptor(
			HttpServletRequest request)
		throws MetadataProviderException {

		return getMetadataManager().getEntityDescriptor(request);
	}

	public static String getLocalEntityId() {
		return getMetadataManager().getLocalEntityId();
	}

	public static MetadataManager getMetadataManager() {
		return _metadataManager;
	}

	public static MetadataProvider getMetadataProvider()
		throws MetadataProviderException {

		return getMetadataManager().getMetadataProvider();
	}

	public static String getNameIdAttribute(String entityId) {
		return getMetadataManager().getNameIdAttribute(entityId);
	}

	public static String getNameIdFormat(String entityId) {
		return getMetadataManager().getNameIdFormat(entityId);
	}

	public static SecurityPolicyResolver getSecurityPolicyResolver(
			String communicationProfileId, boolean requireSignature)
		throws MetadataProviderException {

		return getMetadataManager().getSecurityPolicyResolver(
			communicationProfileId, requireSignature);
	}

	public static String getSessionKeepAliveURL(String entityId) {
		return getMetadataManager().getSessionKeepAliveURL(entityId);
	}

	public static long getSessionMaximumAge() {
		return getMetadataManager().getSessionMaximumAge();
	}

	public static long getSessionTimeout() {
		return getMetadataManager().getSessionTimeout();
	}

	public static SignatureTrustEngine getSignatureTrustEngine()
		throws MetadataProviderException {

		return getMetadataManager().getSignatureTrustEngine();
	}

	public static Credential getSigningCredential() throws SecurityException {
		return getMetadataManager().getSigningCredential();
	}

	public static String getUserAttributeMappings(String entityId) {
		return getMetadataManager().getUserAttributeMappings(entityId);
	}

	public static boolean isAttributesEnabled(String entityId) {
		return getMetadataManager().isAttributesEnabled(entityId);
	}

	public static boolean isAttributesNamespaceEnabled(String entityId) {
		return getMetadataManager().isAttributesNamespaceEnabled(entityId);
	}

	public static boolean isSignAuthnRequests() {
		return getMetadataManager().isSignAuthnRequests();
	}

	public static boolean isSignMetadata() {
		return getMetadataManager().isSignMetadata();
	}

	public static boolean isSSLRequired() {
		return getMetadataManager().isSSLRequired();
	}

	public static boolean isWantAssertionsSigned() {
		return getMetadataManager().isWantAssertionsSigned();
	}

	public static boolean isWantAuthnRequestSigned() {
		return getMetadataManager().isWantAuthnRequestSigned();
	}

	public void setMetadataManager(MetadataManager metadataManager) {
		_metadataManager = metadataManager;
	}

	private static MetadataManager _metadataManager;

}