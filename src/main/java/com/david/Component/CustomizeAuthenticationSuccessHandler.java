package com.david.Component;

import com.david.Entity.UserAdapter;
import com.david.Service.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    /**
     * A logger used to print messages to the servers output.
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Event called when the authentication has been successful.
     *
     * @param request        The incoming HTTP request.
     * @param response       The outgoing HTTP response.
     * @param authentication An authentication object used to access authentication features.
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        logger.info("AT onAuthenticationSuccess(...) function!");

        UserAdapter user = (UserAdapter) authentication.getPrincipal();

        logger.info("Username:" + user.getUsername());
        logger.info("Locked?:" + user.isLocked());


        if (user.isLocked()) {
            logger.info("User is locked.");
            authentication.setAuthenticated(false);
            response.sendRedirect("/");
        } else {
            //set our response to OK status
            response.setStatus(HttpServletResponse.SC_OK);
            logger.info("User isn't locked.");
            response.sendRedirect("/");
        }
    }
}
