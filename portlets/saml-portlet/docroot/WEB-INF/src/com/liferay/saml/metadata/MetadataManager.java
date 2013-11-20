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
public interface MetadataManager {

	public int getAssertionLifetime(String entityId);

	public String[] getAttributeNames(String entityId);

	public long getClockSkew();

	public String getDefaultIdpEntityId();

	public EntityDescriptor getEntityDescriptor(HttpServletRequest request)
		throws MetadataProviderException;

	public String getLocalEntityId();

	public MetadataProvider getMetadataProvider()
		throws MetadataProviderException;

	public String getNameIdAttribute(String entityId);

	public String getNameIdFormat(String entityId);

	public SecurityPolicyResolver getSecurityPolicyResolver(
			String communicationProfileId, boolean requireSignature)
		throws MetadataProviderException;

	public String getSessionKeepAliveURL(String entityId);

	public long getSessionMaximumAge();

	public long getSessionTimeout();

	public SignatureTrustEngine getSignatureTrustEngine()
		throws MetadataProviderException;

	public Credential getSigningCredential() throws SecurityException;

	public String getUserAttributeMappings(String entityId);

	public boolean isAttributesEnabled(String entityId);

	public boolean isAttributesNamespaceEnabled(String entityId);

	public boolean isSignAuthnRequests();

	public boolean isSignMetadata();

	public boolean isSSLRequired();

	public boolean isWantAssertionsSigned();

	public boolean isWantAuthnRequestSigned();

}