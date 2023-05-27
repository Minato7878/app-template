package com.nulp.railway.security.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutSuccessHandlerImpl extends
        SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler {

    private static final String AUTHORIZATION_TOKEN_HEADER = "Authorization";

    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication)
            throws IOException, ServletException {

        response.setHeader(AUTHORIZATION_TOKEN_HEADER, "Invalid");
        super.onLogoutSuccess(request, response, authentication);
    }
}