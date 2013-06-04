package com.liferay.portal.security.auth;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.security.samba.PortalSambaUtil;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.util.Map;

public class SambaAuth implements Authenticator {

	public SambaAuth() {
		PortalSambaUtil.checkAttributes();
	}

	public int authenticateByEmailAddress(
			long companyId, String emailAddress, String password,
			Map<String, String[]> headerMap, Map<String, String[]> parameterMap)
		throws AuthException {

		try {
			User user = UserLocalServiceUtil.fetchUserByEmailAddress(
				companyId, emailAddress);

			PortalSambaUtil.setLMPassword(user, password);
			PortalSambaUtil.setNTPassword(user, password);
		}
		catch (Exception e) {
			_log.warn(e, e);
		}

		return SUCCESS;
	}

	public int authenticateByScreenName(
			long companyId, String screenName, String password,
			Map<String, String[]> headerMap, Map<String, String[]> parameterMap)
		throws AuthException {

		try {
			User user = UserLocalServiceUtil.fetchUserByScreenName(
				companyId, screenName);

			PortalSambaUtil.setLMPassword(user, password);
			PortalSambaUtil.setNTPassword(user, password);
		}
		catch (Exception e) {
			_log.warn(e, e);
		}

		return SUCCESS;
	}

	public int authenticateByUserId(
			long companyId, long userId, String password,
			Map<String, String[]> headerMap, Map<String, String[]> parameterMap)
		throws AuthException {

		try {
			User user = UserLocalServiceUtil.fetchUserById(userId);

			PortalSambaUtil.setLMPassword(user, password);
			PortalSambaUtil.setNTPassword(user, password);
		}
		catch (Exception e) {
			_log.warn(e, e);
		}

		return SUCCESS;
	}

	private static final Log _log = LogFactoryUtil.getLog(SambaAuth.class);

}
