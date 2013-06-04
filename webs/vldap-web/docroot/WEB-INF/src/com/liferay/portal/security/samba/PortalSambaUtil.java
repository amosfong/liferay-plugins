package com.liferay.portal.security.samba;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.model.User;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.model.ExpandoColumnConstants;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.security.MessageDigest;

import jcifs.util.DES;
import jcifs.util.MD4;

public class PortalSambaUtil {

	public static void checkAttributes() {
		_checkAttribute("sambaLMPassword");
		_checkAttribute("sambaNTPassword");
	}

	public static String getLMPassword(User user)
		throws Exception {

		ExpandoBridge expandoBridge = user.getExpandoBridge();

		String lmPassword = (String) expandoBridge.getAttribute(
			"sambaLMPassword", false);

		return lmPassword;
	}

	public static String getNTPassword(User user)
		throws Exception {

		ExpandoBridge expandoBridge = user.getExpandoBridge();

		String ntPassword = (String) expandoBridge.getAttribute(
			"sambaNTPassword", false);

		return ntPassword;
	}

	public static void setLMPassword(User user, String password)
		throws Exception {

		ExpandoBridge expandoBridge = user.getExpandoBridge();

		String lmPassword = _getLMPassword(password);

		expandoBridge.setAttribute("sambaLMPassword", lmPassword, false);
	}

	public static void setNTPassword(User user, String password)
		throws Exception {

		ExpandoBridge expandoBridge = user.getExpandoBridge();

		String ntPassword = _getNTPassword(password);

		expandoBridge.setAttribute("sambaNTPassword", ntPassword, false);
	}

	private static void _checkAttribute(String attributeName) {
		long[] companyIds = PortalUtil.getCompanyIds();

		String className = User.class.getName();

		for (long companyId : companyIds) {
			ExpandoBridge expandoBridge =
				ExpandoBridgeFactoryUtil.getExpandoBridge(
					companyId, className);

			if (!expandoBridge.hasAttribute(attributeName)) {
				try {
					expandoBridge.addAttribute(attributeName, false);
				}
				catch (Exception e) {
					_log.warn(e, e);
				}
			}

			UnicodeProperties properties = expandoBridge.getAttributeProperties(
				attributeName);

			properties.put(
				ExpandoColumnConstants.PROPERTY_HIDDEN, StringPool.TRUE);

			expandoBridge.setAttributeProperties(
				attributeName, properties, false);
		}
	}

	private static String _getLMPassword(String password)
		throws Exception {

        byte[] passwordBytes = password.toUpperCase().getBytes("US-ASCII");

        byte[][] encryptionKeys = new byte[2][7];

		System.arraycopy(
			passwordBytes, 0, encryptionKeys[0], 0,
			Math.min(7, passwordBytes.length));

		if (passwordBytes.length > 7) {
			System.arraycopy(
				passwordBytes, 7, encryptionKeys[1], 0,
				Math.min(7, passwordBytes.length - 7));
        }

        byte[][] encryptedValues = new byte[2][8];

		DES des1 = new DES(encryptionKeys[0]);
		des1.encrypt(_SAMBA_LM_CONSTANT, encryptedValues[0]);

		DES des2 = new DES(encryptionKeys[1]);
		des2.encrypt(_SAMBA_LM_CONSTANT, encryptedValues[1]);

		byte[] lmPasswordBytes = new byte[16];
		System.arraycopy(encryptedValues[0], 0, lmPasswordBytes, 0, 8);
		System.arraycopy(encryptedValues[1], 0, lmPasswordBytes, 8, 8);

		String lmPassword = StringUtil.bytesToHexString(lmPasswordBytes);
		lmPassword = lmPassword.toUpperCase();

		return lmPassword;
	}

	private static String _getNTPassword(String password)
		throws Exception {

		byte[] passwordBytes = password.getBytes("UTF-16LE");

		MessageDigest digest = new MD4();
		byte[] ntPasswordBytes = digest.digest(passwordBytes);

		String ntPassword = StringUtil.bytesToHexString(ntPasswordBytes);
		ntPassword = ntPassword.toUpperCase();

		return ntPassword;
	}

	// KGS!@#$%
	private static final byte[] _SAMBA_LM_CONSTANT = {
		(byte)0x4b, (byte)0x47, (byte)0x53, (byte)0x21,
		(byte)0x40, (byte)0x23, (byte)0x24, (byte)0x25
	};

	private static final Log _log = LogFactoryUtil.getLog(
		PortalSambaUtil.class);

}