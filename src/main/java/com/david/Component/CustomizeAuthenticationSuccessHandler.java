package com.david.Component;

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

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {


        boolean admin = false;

        logger.info("AT onAuthenticationSuccess(...) function!");

        UserDetailsServiceImpl.UserAdapter user = (UserDetailsServiceImpl.UserAdapter) authentication.getPrincipal();

        logger.info("Username:"+user.getUsername());
        logger.info("Locked?:"+user.isLocked());


        if(user.isLocked()){
            //TODO handle locked user
            logger.info("User is locked.");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            authentication.setAuthenticated(false);
            response.getWriter().write("Dein Account wurde leider gesperrt!:C");
        }else{
            //set our response to OK status
            response.setStatus(HttpServletResponse.SC_OK);
            logger.info("User isn't locked.");
            response.sendRedirect("/");
        }
    }
}
