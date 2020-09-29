package web.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.service.UserService;


@Controller
public class UserController {
    private UserService userServiceEntityImpl;

    public UserController(UserService userServiceEntityImpl) {
        this.userServiceEntityImpl = userServiceEntityImpl;
    }

    @GetMapping("/user")
    public String homePage(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", userDetails);
        return "user";
    }
}
