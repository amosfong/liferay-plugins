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

import com.liferay.portal.kernel.util.Validator;

import java.io.StringWriter;

import java.util.List;

import javax.xml.namespace.QName;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import org.opensaml.Configuration;
import org.opensaml.common.SAMLObjectBuilder;
import org.opensaml.common.SAMLVersion;
import org.opensaml.common.SignableSAMLObject;
import org.opensaml.saml2.core.Assertion;
import org.opensaml.saml2.core.Attribute;
import org.opensaml.saml2.core.AttributeStatement;
import org.opensaml.saml2.core.AttributeValue;
import org.opensaml.saml2.core.Audience;
import org.opensaml.saml2.core.AudienceRestriction;
import org.opensaml.saml2.core.AuthnContext;
import org.opensaml.saml2.core.AuthnContextClassRef;
import org.opensaml.saml2.core.AuthnRequest;
import org.opensaml.saml2.core.AuthnStatement;
import org.opensaml.saml2.core.Conditions;
import org.opensaml.saml2.core.Issuer;
import org.opensaml.saml2.core.LogoutRequest;
import org.opensaml.saml2.core.LogoutResponse;
import org.opensaml.saml2.core.NameID;
import org.opensaml.saml2.core.NameIDPolicy;
import org.opensaml.saml2.core.Response;
import org.opensaml.saml2.core.SessionIndex;
import org.opensaml.saml2.core.Status;
import org.opensaml.saml2.core.StatusCode;
import org.opensaml.saml2.core.Subject;
import org.opensaml.saml2.core.SubjectConfirmation;
import org.opensaml.saml2.core.SubjectConfirmationData;
import org.opensaml.saml2.metadata.AssertionConsumerService;
import org.opensaml.saml2.metadata.EntityDescriptor;
import org.opensaml.saml2.metadata.IDPSSODescriptor;
import org.opensaml.saml2.metadata.KeyDescriptor;
import org.opensaml.saml2.metadata.SPSSODescriptor;
import org.opensaml.saml2.metadata.SingleLogoutService;
import org.opensaml.saml2.metadata.SingleSignOnService;
import org.opensaml.xml.XMLObject;
import org.opensaml.xml.XMLObjectBuilder;
import org.opensaml.xml.XMLObjectBuilderFactory;
import org.opensaml.xml.io.Marshaller;
import org.opensaml.xml.io.MarshallerFactory;
import org.opensaml.xml.io.MarshallingException;
import org.opensaml.xml.schema.XSString;
import org.opensaml.xml.security.SecurityConfiguration;
import org.opensaml.xml.security.SecurityException;
import org.opensaml.xml.security.SecurityHelper;
import org.opensaml.xml.security.credential.Credential;
import org.opensaml.xml.security.credential.UsageType;
import org.opensaml.xml.security.keyinfo.KeyInfoGenerator;
import org.opensaml.xml.security.keyinfo.KeyInfoGeneratorFactory;
import org.opensaml.xml.security.keyinfo.KeyInfoGeneratorManager;
import org.opensaml.xml.security.keyinfo.NamedKeyInfoGeneratorManager;
import org.opensaml.xml.signature.KeyInfo;
import org.opensaml.xml.signature.Signature;
import org.opensaml.xml.signature.SignatureException;
import org.opensaml.xml.signature.Signer;
import org.opensaml.xml.util.XMLObjectHelper;

/**
 * @author Mika Koivisto
 */
public class OpenSamlUtil {

	public static Assertion buildAssertion() {
		SAMLObjectBuilder<Assertion> samlObjectBuilder =
			(SAMLObjectBuilder<Assertion>)_getBuilder(
				Assertion.DEFAULT_ELEMENT_NAME);

		Assertion assertion = samlObjectBuilder.buildObject();

		assertion.setVersion(SAMLVersion.VERSION_20);

		return assertion;
	}

	public static AssertionConsumerService buildAssertionConsumerService(
		String binding, int index, boolean isDefault, String location) {

		SAMLObjectBuilder<AssertionConsumerService> samlObjectBuilder =
			(SAMLObjectBuilder<AssertionConsumerService>)_getBuilder(
				AssertionConsumerService.DEFAULT_ELEMENT_NAME);

		AssertionConsumerService assertionConsumerService =
			samlObjectBuilder.buildObject();

		assertionConsumerService.setBinding(binding);
		assertionConsumerService.setIndex(Integer.valueOf(index));
		assertionConsumerService.setIsDefault(Boolean.valueOf(isDefault));
		assertionConsumerService.setLocation(location);

		return assertionConsumerService;
	}

	public static Attribute buildAttribute() {
		SAMLObjectBuilder<Attribute> samlObjectBuilder =
			(SAMLObjectBuilder<Attribute>)_getBuilder(
				Attribute.DEFAULT_ELEMENT_NAME);

		return samlObjectBuilder.buildObject();
	}

	public static Attribute buildAttribute(String name, String value) {
		return buildAttribute(name, Attribute.UNSPECIFIED, value);
	}

	public static Attribute buildAttribute(
		String name, String nameFormat, String value) {

		Attribute attribute = OpenSamlUtil.buildAttribute();

		attribute.setName(name);
		attribute.setNameFormat(nameFormat);

		XMLObject xmlObject = OpenSamlUtil.buildAttributeValue(value);

		List<XMLObject> xmlObjects = attribute.getAttributeValues();

		xmlObjects.add(xmlObject);

		return attribute;
	}

	public static AttributeStatement buildAttributeStatement() {
		SAMLObjectBuilder<AttributeStatement> samlObjectBuilder =
			(SAMLObjectBuilder<AttributeStatement>)_getBuilder(
				AttributeStatement.DEFAULT_ELEMENT_NAME);

		return samlObjectBuilder.buildObject();
	}

	public static XMLObject buildAttributeValue(String value) {
		XMLObjectBuilder<XSString> xmlObjectBuilder = _getBuilder(
			XSString.TYPE_NAME);

		XSString xsString = xmlObjectBuilder.buildObject(
			AttributeValue.DEFAULT_ELEMENT_NAME, XSString.TYPE_NAME);

		xsString.setValue(value);

		return xsString;
	}

	public static Audience buildAudience() {
		SAMLObjectBuilder<Audience> samlObjectBuilder =
			(SAMLObjectBuilder<Audience>)_getBuilder(
				Audience.DEFAULT_ELEMENT_NAME);

		return samlObjectBuilder.buildObject();
	}

	public static AudienceRestriction buildAudienceRestriction() {
		SAMLObjectBuilder<AudienceRestriction> samlObjectBuilder =
			(SAMLObjectBuilder<AudienceRestriction>)_getBuilder(
				AudienceRestriction.DEFAULT_ELEMENT_NAME);

		return samlObjectBuilder.buildObject();
	}

	public static AuthnContext buildAuthnContext() {
		SAMLObjectBuilder<AuthnContext> samlObjectBuilder =
			(SAMLObjectBuilder<AuthnContext>)_getBuilder(
				AuthnContext.DEFAULT_ELEMENT_NAME);

		return samlObjectBuilder.buildObject();
	}

	public static AuthnContextClassRef buildAuthnContextClassRef() {
		SAMLObjectBuilder<AuthnContextClassRef> samlObjectBuilder =
			(SAMLObjectBuilder<AuthnContextClassRef>)_getBuilder(
				AuthnContextClassRef.DEFAULT_ELEMENT_NAME);

		return samlObjectBuilder.buildObject();
	}

	public static AuthnRequest buildAuthnRequest(
		SPSSODescriptor spSsoDescriptor,
		AssertionConsumerService assertionConsumerService,
		SingleSignOnService singleSignOnService, NameIDPolicy nameIdPolicy) {

		SAMLObjectBuilder<AuthnRequest> samlObjectBuilder =
			(SAMLObjectBuilder<AuthnRequest>)_getBuilder(
				AuthnRequest.DEFAULT_ELEMENT_NAME);

		AuthnRequest authnRequest = samlObjectBuilder.buildObject();

		DateTime now = new DateTime(DateTimeZone.UTC);

		authnRequest.setForceAuthn(false);
		authnRequest.setIsPassive(false);
		authnRequest.setIssueInstant(now);

		Issuer issuer = buildIssuer(spSsoDescriptor.getID());

		authnRequest.setIssuer(issuer);

		authnRequest.setAssertionConsumerServiceURL(
			assertionConsumerService.getLocation());
		authnRequest.setDestination(singleSignOnService.getLocation());
		authnRequest.setNameIDPolicy(nameIdPolicy);
		authnRequest.setProtocolBinding(assertionConsumerService.getBinding());
		authnRequest.setVersion(SAMLVersion.VERSION_20);

		return authnRequest;
	}

	public static AuthnStatement buildAuthnStatement() {
		SAMLObjectBuilder<AuthnStatement> samlObjectBuilder =
			(SAMLObjectBuilder<AuthnStatement>)_getBuilder(
				AuthnStatement.DEFAULT_ELEMENT_NAME);

		return samlObjectBuilder.buildObject();
	}

	public static Conditions buildConditions() {
		SAMLObjectBuilder<Conditions> samlObjectBuilder =
			(SAMLObjectBuilder<Conditions>)_getBuilder(
				Conditions.DEFAULT_ELEMENT_NAME);

		return samlObjectBuilder.buildObject();
	}

	public static EntityDescriptor buildEntityDescriptor() {
		SAMLObjectBuilder<EntityDescriptor> samlObjectBuilder =
			(SAMLObjectBuilder<EntityDescriptor>)_getBuilder(
				EntityDescriptor.DEFAULT_ELEMENT_NAME);

		return samlObjectBuilder.buildObject();
	}

	public static IDPSSODescriptor buildIdpSsoDescriptor() {
		SAMLObjectBuilder<IDPSSODescriptor> samlObjectBuilder =
			(SAMLObjectBuilder<IDPSSODescriptor>)_getBuilder(
				IDPSSODescriptor.DEFAULT_ELEMENT_NAME);

		return samlObjectBuilder.buildObject();
	}

	public static Issuer buildIssuer(String value) {
		SAMLObjectBuilder<Issuer> samlObjectBuilder =
			(SAMLObjectBuilder<Issuer>)_getBuilder(Issuer.DEFAULT_ELEMENT_NAME);

		Issuer issuer = samlObjectBuilder.buildObject();

		issuer.setValue(value);

		return issuer;
	}

	public static KeyDescriptor buildKeyDescriptor(
		UsageType useType, KeyInfo keyInfo) {

		SAMLObjectBuilder<KeyDescriptor> samlObjectBuilder =
			(SAMLObjectBuilder<KeyDescriptor>)_getBuilder(
				KeyDescriptor.DEFAULT_ELEMENT_NAME);

		KeyDescriptor keyDescriptor = samlObjectBuilder.buildObject();

		keyDescriptor.setKeyInfo(keyInfo);
		keyDescriptor.setUse(useType);

		return keyDescriptor;
	}

	public static KeyInfo buildKeyInfo(Credential credential)
		throws SecurityException {

		SecurityConfiguration securityConfiguration =
			Configuration.getGlobalSecurityConfiguration();

		NamedKeyInfoGeneratorManager namedKeyInfoGeneratorManager =
			securityConfiguration.getKeyInfoGeneratorManager();

		KeyInfoGeneratorManager keyInfoGeneratorManager =
			namedKeyInfoGeneratorManager.getDefaultManager();

		KeyInfoGeneratorFactory keyInfoGeneratorFactory =
			keyInfoGeneratorManager.getFactory(credential);

		KeyInfoGenerator keyInfoGenerator =
			keyInfoGeneratorFactory.newInstance();

		return keyInfoGenerator.generate(credential);
	}

	public static LogoutRequest buildLogoutRequest() {
		SAMLObjectBuilder<LogoutRequest> samlObjectBuilder =
			(SAMLObjectBuilder<LogoutRequest>)_getBuilder(
				LogoutRequest.DEFAULT_ELEMENT_NAME);

		return samlObjectBuilder.buildObject();
	}

	public static LogoutResponse buildLogoutResponse() {
		SAMLObjectBuilder<LogoutResponse> samlObjectBuilder =
			(SAMLObjectBuilder<LogoutResponse>)_getBuilder(
				LogoutResponse.DEFAULT_ELEMENT_NAME);

		return samlObjectBuilder.buildObject();
	}

	public static NameID buildNameId(String nameIdFormat, String nameIdValue) {
		return buildNameId(nameIdFormat, nameIdValue, null);
	}

	public static NameID buildNameId(
		String nameIdFormat, String nameIdValue, String spNameIdQualifier) {

		SAMLObjectBuilder<NameID> samlObjectBuilder =
			(SAMLObjectBuilder<NameID>)_getBuilder(NameID.DEFAULT_ELEMENT_NAME);

		NameID nameId = samlObjectBuilder.buildObject();

		nameId.setFormat(nameIdFormat);
		nameId.setValue(nameIdValue);

		if (Validator.isNotNull(spNameIdQualifier)) {
			nameId.setSPNameQualifier(spNameIdQualifier);
		}

		return nameId;
	}

	public static NameIDPolicy buildNameIdPolicy() {
		SAMLObjectBuilder<NameIDPolicy> samlObjectBuilder =
			(SAMLObjectBuilder<NameIDPolicy>)_getBuilder(
				NameIDPolicy.DEFAULT_ELEMENT_NAME);

		return samlObjectBuilder.buildObject();
	}

	public static Response buildResponse() {
		SAMLObjectBuilder<Response> samlObjectBuilder =
			(SAMLObjectBuilder<Response>)_getBuilder(
				Response.DEFAULT_ELEMENT_NAME);

		Response response = samlObjectBuilder.buildObject();

		response.setVersion(SAMLVersion.VERSION_20);

		return response;
	}

	public static SessionIndex buildSessionIndex(String sessionIndexString) {
		SAMLObjectBuilder<SessionIndex> samlObjectBuilder =
			(SAMLObjectBuilder<SessionIndex>)_getBuilder(
				SessionIndex.DEFAULT_ELEMENT_NAME);

		SessionIndex sessionIndex = samlObjectBuilder.buildObject();

		sessionIndex.setSessionIndex(sessionIndexString);

		return sessionIndex;
	}

	public static Signature buildSignature(Credential credential) {
		XMLObjectBuilder<Signature> samlObjectBuilder = _getBuilder(
			Signature.DEFAULT_ELEMENT_NAME);

		Signature signature = samlObjectBuilder.buildObject(
			Signature.DEFAULT_ELEMENT_NAME);

		signature.setSigningCredential(credential);

		return signature;
	}

	public static SingleLogoutService buildSingleLogoutService(
		String binding, String location) {

		SAMLObjectBuilder<SingleLogoutService> samlObjectBuilder =
			(SAMLObjectBuilder<SingleLogoutService>)_getBuilder(
				SingleLogoutService.DEFAULT_ELEMENT_NAME);

		SingleLogoutService singleLogoutService =
			samlObjectBuilder.buildObject();

		singleLogoutService.setBinding(binding);
		singleLogoutService.setLocation(location);

		return singleLogoutService;
	}

	public static SingleSignOnService buildSingleSignOnService(
		String binding, String location) {

		SAMLObjectBuilder<SingleSignOnService> samlObjectBuilder =
			(SAMLObjectBuilder<SingleSignOnService>)_getBuilder(
				SingleSignOnService.DEFAULT_ELEMENT_NAME);

		SingleSignOnService singleSignOnService =
			samlObjectBuilder.buildObject();

		singleSignOnService.setBinding(binding);
		singleSignOnService.setLocation(location);

		return singleSignOnService;
	}

	public static SPSSODescriptor buildSpSsoDescriptor() {
		SAMLObjectBuilder<SPSSODescriptor> samlObjectBuilder =
			(SAMLObjectBuilder<SPSSODescriptor>)_getBuilder(
				SPSSODescriptor.DEFAULT_ELEMENT_NAME);

		return samlObjectBuilder.buildObject();
	}

	public static Status buildStatus(StatusCode statusCode) {
		SAMLObjectBuilder<Status> samlObjectBuilder =
			(SAMLObjectBuilder<Status>)_getBuilder(Status.DEFAULT_ELEMENT_NAME);

		Status status = samlObjectBuilder.buildObject();

		status.setStatusCode(statusCode);

		return status;
	}

	public static StatusCode buildStatusCode(String value) {
		SAMLObjectBuilder<StatusCode> samlObjectBuilder =
			(SAMLObjectBuilder<StatusCode>)_getBuilder(
				StatusCode.DEFAULT_ELEMENT_NAME);

		StatusCode statusCode = samlObjectBuilder.buildObject();

		statusCode.setValue(value);

		return statusCode;
	}

	public static Subject buildSubject(NameID nameID) {
		SAMLObjectBuilder<Subject> samlObjectBuilder =
			(SAMLObjectBuilder<Subject>)_getBuilder(
				Subject.DEFAULT_ELEMENT_NAME);

		Subject subject = samlObjectBuilder.buildObject();

		subject.setNameID(nameID);

		return subject;
	}

	public static SubjectConfirmation buildSubjectConfirmation() {
		SAMLObjectBuilder<SubjectConfirmation> samlObjectBuilder =
			(SAMLObjectBuilder<SubjectConfirmation>)_getBuilder(
				SubjectConfirmation.DEFAULT_ELEMENT_NAME);

		SubjectConfirmation subjectConfirmation =
			samlObjectBuilder.buildObject();

		return subjectConfirmation;
	}

	public static SubjectConfirmationData buildSubjectConfirmationData() {
		SAMLObjectBuilder<SubjectConfirmationData> samlObjectBuilder =
			(SAMLObjectBuilder<SubjectConfirmationData>)_getBuilder(
				SubjectConfirmationData.DEFAULT_ELEMENT_NAME);

		SubjectConfirmationData subjectConfirmationData =
			samlObjectBuilder.buildObject();

		return subjectConfirmationData;
	}

	public static String marshall(XMLObject xmlObject)
		throws MarshallingException {

		StringWriter stringWriter = new StringWriter();

		XMLObjectHelper.marshallToWriter(xmlObject, stringWriter);

		return stringWriter.toString();
	}

	public static void signObject(
			SignableSAMLObject signableObject, Credential credential)
		throws MarshallingException, SecurityException, SignatureException {

		Signature signature = buildSignature(credential);

		SecurityHelper.prepareSignatureParams(
			signature, credential, null, null);

		signableObject.setSignature(signature);

		MarshallerFactory marshallerFactory =
			Configuration.getMarshallerFactory();

		Marshaller marshaller = marshallerFactory.getMarshaller(signableObject);

		marshaller.marshall(signableObject);

		Signer.signObject(signature);
	}

	@SuppressWarnings("rawtypes")
	private static XMLObjectBuilder _getBuilder(QName qName) {
		return _xmlObjectBuilderFactory.getBuilder(qName);
	}

	private static XMLObjectBuilderFactory _xmlObjectBuilderFactory =
		Configuration.getBuilderFactory();

}