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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.ByteArrayInputStream;

import java.math.BigInteger;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.PublicKey;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import java.util.Date;

import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v1CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;

/**
 * @author Mika Koivisto
 */
public class CertificateUtil {

	public static X500Name createX500Name(
		String commonName, String organization, String organizationUnit,
		String locality, String state, String country) {

		X500NameBuilder x500NameBuilder = new X500NameBuilder(BCStyle.INSTANCE);

		if (Validator.isNotNull(commonName)) {
			x500NameBuilder.addRDN(BCStyle.CN, commonName);
		}

		if (Validator.isNotNull(organizationUnit)) {
			x500NameBuilder.addRDN(BCStyle.OU, organizationUnit);
		}

		if (Validator.isNotNull(organization)) {
			x500NameBuilder.addRDN(BCStyle.O, organization);
		}

		if (Validator.isNotNull(locality)) {
			x500NameBuilder.addRDN(BCStyle.L, locality);
		}

		if (Validator.isNotNull(state)) {
			x500NameBuilder.addRDN(BCStyle.ST, state);
		}

		if (Validator.isName(country)) {
			x500NameBuilder.addRDN(BCStyle.C, country);
		}

		return x500NameBuilder.build();
	}

	public static X509Certificate generateCertificate(
			KeyPair keyPair, X500Name issuerX500Name, X500Name subjectX500Name,
			Date startDate, Date endDate, String signatureAlgorithm)
		throws CertificateException {

		ByteArrayInputStream byteArrayInputStream = null;

		ASN1InputStream asn1InputStream = null;

		try {
			JcaX509CertificateConverter jcaX509CertificateConverter =
				new JcaX509CertificateConverter();

			PublicKey publicKey = keyPair.getPublic();

			byteArrayInputStream = new ByteArrayInputStream(
				publicKey.getEncoded());

			asn1InputStream = new ASN1InputStream(byteArrayInputStream);

			ASN1Sequence asn1Sequence =
				(ASN1Sequence)asn1InputStream.readObject();

			SubjectPublicKeyInfo subjectPublicKeyInfo =
				new SubjectPublicKeyInfo(asn1Sequence);

			X509v1CertificateBuilder x509v1CertificateBuilder =
				new X509v1CertificateBuilder(
					issuerX500Name,
					BigInteger.valueOf(System.currentTimeMillis()), startDate,
					endDate, subjectX500Name, subjectPublicKeyInfo);

			JcaContentSignerBuilder jcaContentSignerBuilder =
				new JcaContentSignerBuilder(signatureAlgorithm);

			ContentSigner contentSigner = jcaContentSignerBuilder.build(
				keyPair.getPrivate());

			X509CertificateHolder x509CertificateHolder =
				x509v1CertificateBuilder.build(contentSigner);

			X509Certificate x509Certificate =
				jcaX509CertificateConverter.getCertificate(
					x509CertificateHolder);

			return x509Certificate;
		}
		catch (Exception oce) {
			throw new CertificateException(oce);
		}
		finally {
			StreamUtil.cleanUp(asn1InputStream);

			StreamUtil.cleanUp(byteArrayInputStream);
		}
	}

	public static KeyPair generateKeyPair(String algorithm, int keySize)
		throws Exception {

		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(
			algorithm);

		keyPairGenerator.initialize(keySize);

		KeyPair keyPair = keyPairGenerator.genKeyPair();

		return keyPair;
	}

	public static String getFingerprint(
		String algorithm, X509Certificate x509Certificate) {

		MessageDigest messageDigest = null;

		try {
			messageDigest = MessageDigest.getInstance(algorithm);

			messageDigest.update(x509Certificate.getEncoded());
		}
		catch (CertificateEncodingException cee) {
			_log.error(cee, cee);

			return StringPool.BLANK;
		}
		catch (NoSuchAlgorithmException nsae) {
			_log.error(nsae, nsae);

			return StringPool.BLANK;
		}

		byte[] digest = messageDigest.digest();

		StringBundler sb = new StringBundler(digest.length * 2 - 1);

		for (int i = 0; i < digest.length; i++) {
			String hex = Integer.toHexString(digest[i] & 0xff);

			sb.append(StringUtil.toUpperCase(hex));

			if ((i + 1) < digest.length) {
				sb.append(CharPool.COLON);
			}
		}

		return sb.toString();
	}

	public static String getSerial(X509Certificate x509Certificate) {
		BigInteger serialNumber = x509Certificate.getSerialNumber();

		byte[] bytes = serialNumber.toByteArray();

		StringBundler sb = new StringBundler(bytes.length);

		for (byte b : bytes) {
			sb.append(Integer.toHexString(b & 0xff));
		}

		return sb.toString();
	}

	public static String getSubjectName(X509Certificate x509Certificate) {
		if (x509Certificate == null) {
			return null;
		}

		Principal principal = x509Certificate.getSubjectDN();

		if (principal != null) {
			return principal.getName();
		}

		return null;
	}

	private static Log _log = LogFactoryUtil.getLog(CertificateUtil.class);

}