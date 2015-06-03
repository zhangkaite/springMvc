package com.zkt.find.common.util;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.octo.captcha.service.CaptchaServiceException;

public class ImageCaptchaServlet extends HttpServlet {

	private static final long serialVersionUID = -5382766069139170499L;

	public void init(ServletConfig servletConfig) throws ServletException {

		super.init(servletConfig);

	}

	protected void doGet(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws ServletException,
			IOException {
		try {
			CaptchaServiceSingleton.getInstance().writeCaptchaImage(
					httpServletRequest, httpServletResponse);

		} catch (IllegalArgumentException e) {
			httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		} catch (CaptchaServiceException e) {
			httpServletResponse
					.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}
	}

	protected void doPost(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws ServletException,
			IOException {
		doGet(httpServletRequest, httpServletResponse);
	}

	public static boolean validateResponse(HttpServletRequest request,String userCaptchaResponse) {

		HttpSession session = request.getSession();
		boolean isResponseCorrect = false;
		// retrieve the response
		if (userCaptchaResponse==null) {
			return false;
		}
		String validateCode = userCaptchaResponse.trim();
		try {
		    isResponseCorrect = CaptchaServiceSingleton.getInstance()
		            .validateCaptchaResponse(validateCode, session);

		    return isResponseCorrect;
		} catch (Exception e) {
		    // should not happen, may be thrown if the id is not valid
		    return false;
		}
	}
}