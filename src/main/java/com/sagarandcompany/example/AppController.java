package com.sagarandcompany.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AppController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String handleLoginGetRequest () {
        return "user-login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String handleLoginPostRequest (User user, Model model,
                                          HttpServletRequest request) {

        User loggedUser = userService.loginUser(user);
        if (loggedUser != null) {
            request.getSession(true)
                   .setAttribute("user", loggedUser);
            return "redirect:/app/main-page";
        } else {
            model.addAttribute("error", "Not a valid user");
            return "user-login";
        }
    }

    @RequestMapping(value = "app/**", method = RequestMethod.GET)
    public String handleAppRequest (Model model, HttpServletRequest request) {
        model.addAttribute("uri", request.getRequestURI());
        model.addAttribute("user", request.getAttribute("user"));
        return "app-page";
    }
}