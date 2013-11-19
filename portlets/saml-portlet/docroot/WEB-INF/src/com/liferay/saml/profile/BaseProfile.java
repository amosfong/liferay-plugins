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

package com.liferay.saml.profile;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.CookieKeys;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.saml.SamlException;
import com.liferay.saml.binding.SamlBinding;
import com.liferay.saml.metadata.MetadataManagerUtil;
import com.liferay.saml.model.SamlSpSession;
import com.liferay.saml.service.SamlSpSessionLocalServiceUtil;
import com.liferay.saml.util.OpenSamlUtil;
import com.liferay.saml.util.PortletWebKeys;
import com.liferay.saml.util.SamlUtil;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.opensaml.common.IdentifierGenerator;
import org.opensaml.common.SAMLObject;
import org.opensaml.common.binding.BasicSAMLMessageContext;
import org.opensaml.common.binding.SAMLMessageContext;
import org.opensaml.common.xml.SAMLConstants;
import org.opensaml.saml2.core.NameID;
import org.opensaml.saml2.metadata.Endpoint;
import org.opensaml.saml2.metadata.EntityDescriptor;
import org.opensaml.saml2.metadata.IDPSSODescriptor;
import org.opensaml.saml2.metadata.RoleDescriptor;
import org.opensaml.saml2.metadata.SPSSODescriptor;
import org.opensaml.saml2.metadata.provider.MetadataProvider;
import org.opensaml.ws.message.decoder.MessageDecoder;
import org.opensaml.ws.message.encoder.MessageEncoder;
import org.opensaml.ws.message.encoder.MessageEncodingException;
import org.opensaml.ws.security.SecurityPolicyResolver;
import org.opensaml.ws.transport.http.HttpServletRequestAdapter;
import org.opensaml.ws.transport.http.HttpServletResponseAdapter;
import org.opensaml.xml.XMLObject;
import org.opensaml.xml.io.MarshallingException;

/**
 * @author Mika Koivisto
 */
public abstract class BaseProfile {

	public SAMLMessageContext<?, ?, ?> decodeSamlMessage(
			HttpServletRequest request, HttpServletResponse response,
			SamlBinding samlBinding, boolean requireSignature)
		throws Exception {

		SAMLMessageContext<SAMLObject, SAMLObject, NameID> samlMessageContext =
			(SAMLMessageContext<SAMLObject, SAMLObject, NameID>)
				getSamlMessageContext(request, response);

		samlMessageContext.setCommunicationProfileId(
			samlBinding.getCommunicationProfileId());

		SecurityPolicyResolver securityPolicyResolver =
			MetadataManagerUtil.getSecurityPolicyResolver(
				samlMessageContext.getCommunicationProfileId(),
				requireSignature);

		samlMessageContext.setSecurityPolicyResolver(securityPolicyResolver);

		MessageDecoder messageDecoder = samlBinding.getMessageDecoder();

		messageDecoder.decode(samlMessageContext);

		if (_log.isDebugEnabled()) {
			SAMLObject samlObject = samlMessageContext.getInboundSAMLMessage();

			_log.debug(
				"Received message using binding " +
					samlMessageContext.getCommunicationProfileId() + " " +
						OpenSamlUtil.marshall(samlObject));
		}

		EntityDescriptor entityDescriptor =
			samlMessageContext.getPeerEntityMetadata();

		if (entityDescriptor == null) {
			throw new SamlException(
				"Unable to resolve metadata for issuer " +
					samlMessageContext.getInboundMessageIssuer());
		}

		samlMessageContext.setPeerEntityId(entityDescriptor.getEntityID());

		RoleDescriptor roleDescriptor = null;

		if (SamlUtil.isRoleIdp()) {
			roleDescriptor = entityDescriptor.getSPSSODescriptor(
				SAMLConstants.SAML20P_NS);
		}
		else if (SamlUtil.isRoleSp()) {
			roleDescriptor = entityDescriptor.getIDPSSODescriptor(
				SAMLConstants.SAML20P_NS);
		}

		samlMessageContext.setPeerEntityRoleMetadata(roleDescriptor);

		return samlMessageContext;
	}

	public String generateIdentifier(int length) {
		return _identifierGenerator.generateIdentifier(length);
	}

	public IdentifierGenerator getIdentifierGenerator() {
		return _identifierGenerator;
	}

	public SamlBinding getSamlBinding(String communicationProfileId)
		throws PortalException {

		for (SamlBinding samlBinding : _samlBindings) {
			if (communicationProfileId.equals(
					samlBinding.getCommunicationProfileId())) {

				return samlBinding;
			}
		}

		throw new SamlException(
			"Unsupported binding " + communicationProfileId);
	}

	public SAMLMessageContext<?, ?, ?> getSamlMessageContext(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		SAMLMessageContext<SAMLObject, SAMLObject, NameID> samlMessageContext =
			new BasicSAMLMessageContext<SAMLObject, SAMLObject, NameID>();

		HttpServletRequestAdapter httpServletRequestAdapter =
			new HttpServletRequestAdapter(request);

		samlMessageContext.setInboundMessageTransport(
			httpServletRequestAdapter);

		samlMessageContext.setInboundSAMLProtocol(SAMLConstants.SAML20P_NS);

		RoleDescriptor roleDescriptor = null;

		EntityDescriptor entityDescriptor =
			MetadataManagerUtil.getEntityDescriptor(request);

		samlMessageContext.setLocalEntityMetadata(entityDescriptor);

		if (SamlUtil.isRoleIdp()) {
			roleDescriptor = entityDescriptor.getIDPSSODescriptor(
				SAMLConstants.SAML20P_NS);
		}
		else if (SamlUtil.isRoleSp()) {
			roleDescriptor = entityDescriptor.getSPSSODescriptor(
				SAMLConstants.SAML20P_NS);
		}

		samlMessageContext.setLocalEntityId(roleDescriptor.getID());

		if (SamlUtil.isRoleIdp()) {
			samlMessageContext.setLocalEntityRole(
				IDPSSODescriptor.DEFAULT_ELEMENT_NAME);
		}
		else if (SamlUtil.isRoleSp()) {
			samlMessageContext.setLocalEntityRole(
				SPSSODescriptor.DEFAULT_ELEMENT_NAME);
		}

		samlMessageContext.setLocalEntityRoleMetadata(roleDescriptor);

		MetadataProvider metadataProvider =
			MetadataManagerUtil.getMetadataProvider();

		samlMessageContext.setMetadataProvider(metadataProvider);

		HttpServletResponseAdapter httpServletResponseAdapter =
			new HttpServletResponseAdapter(response, request.isSecure());

		samlMessageContext.setOutboundMessageTransport(
			httpServletResponseAdapter);

		if (SamlUtil.isRoleIdp()) {
			samlMessageContext.setPeerEntityRole(
				SPSSODescriptor.DEFAULT_ELEMENT_NAME);
		}
		else if (SamlUtil.isRoleSp()) {
			samlMessageContext.setPeerEntityRole(
				IDPSSODescriptor.DEFAULT_ELEMENT_NAME);
		}

		return samlMessageContext;
	}

	public SAMLMessageContext<?, ?, ?> getSamlMessageContext(
			HttpServletRequest request, HttpServletResponse response,
			String peerEntityId)
		throws Exception {

		SAMLMessageContext<?, ?, ?> samlMessageContext = getSamlMessageContext(
			request, response);

		samlMessageContext.setPeerEntityId(peerEntityId);

		MetadataProvider metadataProvider =
			samlMessageContext.getMetadataProvider();

		EntityDescriptor entityDescriptor =
			metadataProvider.getEntityDescriptor(peerEntityId);

		if (entityDescriptor == null) {
			throw new SamlException("Unknown peer entity ID " + peerEntityId);
		}

		samlMessageContext.setPeerEntityMetadata(entityDescriptor);

		RoleDescriptor roleDescriptor = null;

		if (SamlUtil.isRoleIdp()) {
			roleDescriptor = entityDescriptor.getSPSSODescriptor(
				SAMLConstants.SAML20P_NS);
		}
		else if (SamlUtil.isRoleSp()) {
			roleDescriptor = entityDescriptor.getIDPSSODescriptor(
				SAMLConstants.SAML20P_NS);
		}

		samlMessageContext.setPeerEntityRoleMetadata(roleDescriptor);

		return samlMessageContext;
	}

	public SamlSpSession getSamlSpSession(HttpServletRequest request)
		throws SystemException {

		String samlSpSessionKey = getSamlSpSessionKey(request);

		if (Validator.isNotNull(samlSpSessionKey)) {
			SamlSpSession samlSpSession =
				SamlSpSessionLocalServiceUtil.
					fetchSamlSpSessionBySamlSpSessionKey(samlSpSessionKey);

			if (samlSpSession != null) {
				return samlSpSession;
			}
		}

		HttpSession session = request.getSession();

		SamlSpSession samlSpSession =
			SamlSpSessionLocalServiceUtil.fetchSamlSpSessionByJSessionId(
				session.getId());

		return samlSpSession;
	}

	public String getSamlSpSessionKey(HttpServletRequest request) {
		return CookieKeys.getCookie(
			request, PortletWebKeys.SAML_SP_SESSION_KEY);
	}

	public String getSamlSsoSessionId(HttpServletRequest request) {
		return CookieKeys.getCookie(
			request, PortletWebKeys.SAML_SSO_SESSION_ID);
	}

	public void sendSamlMessage(SAMLMessageContext<?, ?, ?> samlMessageContext)
		throws PortalException {

		Endpoint endpoint = samlMessageContext.getPeerEntityEndpoint();

		SamlBinding samlBinding = getSamlBinding(endpoint.getBinding());

		if (_log.isDebugEnabled()) {
			try {
				XMLObject xmlObject =
					samlMessageContext.getOutboundSAMLMessage();

				String samlMessage = OpenSamlUtil.marshall(xmlObject);

				_log.debug(
					"Sending SAML message " + samlMessage + " to " +
						endpoint.getLocation() + " with binding " +
							endpoint.getBinding());
			}
			catch (MarshallingException e) {
			}
		}

		MessageEncoder messageEncoder = samlBinding.getMessageEncoder();

		try {
			messageEncoder.encode(samlMessageContext);
		}
		catch (MessageEncodingException mee) {
			throw new SamlException(
				"Unable to send SAML message to " + endpoint.getLocation() +
					" with binding " + endpoint.getBinding(),
				mee);
		}
	}

	public void setIdentifierGenerator(
		IdentifierGenerator identifierGenerator) {

		_identifierGenerator = identifierGenerator;
	}

	public void setSamlBindings(List<SamlBinding> samlBindings) {
		_samlBindings = samlBindings;
	}

	private static Log _log = LogFactoryUtil.getLog(BaseProfile.class);

	private IdentifierGenerator _identifierGenerator;
	private List<SamlBinding> _samlBindings;

}