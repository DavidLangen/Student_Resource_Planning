package com.david.Controller;

import com.david.Entity.LoginUser;
import com.david.Service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This controller handles incoming requests concerning the user.
 *
 * @author David Langen
 */
@Controller
public class UserController {

    /**
     * Wrapper-Class for the User Repository
     */
    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    /**
     * This controller method handles get-requests to "/user".
     * It responds with an index view of user.
     *
     * @param model The model used in Thymeleaf templating.
     * @param page  Page number of the currently displayed page.
     * @return The "user"-view.
     */
    @GetMapping("/user")
    public String allUser(Model model, @RequestParam(defaultValue = "0") int page) {
        Page<LoginUser> allUser = userDetailsService.getAllUser(page);
        model.addAttribute("user", allUser);
        model.addAttribute("currentPage", page);
        return "user";
    }

    /**
     * This controller method handles get-requests to "/user/lock/".
     * It is responsible for lock or unlock LoginUser.
     * This depends on the state of the User.
     * It redirects to "/user".
     *
     * @param id The id of the course to be deleted.
     * @return A redirect to "/user".
     */
    @GetMapping("/user/lock/")
    public String lockUserById(long id) {
        LoginUser user = userDetailsService.getUserById(id);
        user.toggleLocking();
        userDetailsService.saveUser(user);
        return "redirect:/user";
    }

    /**
     * This controller method handles post-requests to "/user/create".
     * It is responsible for creating LoginUser. It responds with a redirect to
     * "/user"
     *
     * @param user The parsed LoginUser object to be created.
     * @return A redirect to "/user".
     */
    @PostMapping(value = "/user/create")
    public String createUser(@ModelAttribute LoginUser user) {
        userDetailsService.saveUser(user);
        return "redirect:/user";
    }
}
