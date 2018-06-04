package com.david.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * This class handles incoming requests from the login page.
 */
@Controller
public class LoginController {

    /**
     * This controller method handles requests to "/login".
     *
     * @param model  The model used in Thymeleaf templating.
     * @param error  The error message to be displayed in case of login errors.
     * @param logout The message to be displayed in case of logging out.
     * @return The login view.
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("errorMsg", "Your username and password are invalid.");

        if (logout != null)
            model.addAttribute("msg", "You have been logged out successfully.");

        return "login";
    }
}
