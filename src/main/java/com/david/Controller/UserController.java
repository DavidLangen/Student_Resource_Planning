package com.david.Controller;

import com.david.Entity.LoginUser;
import com.david.Service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.stream.Collectors;

@Controller
public class UserController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @GetMapping("/user")
    public String allUser(Model model, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("user", userDetailsService.getAllUser(page));
        model.addAttribute("currentPage", page);
        return "user";
    }

    @GetMapping("/lockUserById")
    public String lockUserById(long id){
        LoginUser user = userDetailsService.getUserById(id);
        user.toggleLocking();
        userDetailsService.saveUser(user);

        return "redirect:/user";
    }

}
