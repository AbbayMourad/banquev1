package com.banque.banquev1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.security.RolesAllowed;

@Controller
@RequestMapping("/")
public class TemplateController {

    @GetMapping("login")
    public String getLoginView() {
        return "login";
    }

    @GetMapping("home")
    public String getHomeView() {
        return "home";
    }

    @RolesAllowed({"ADMIN"})
    @RequestMapping("admin")
    public String getAdminView() {
        return "admin";
    }

    @RolesAllowed({"USER"})
    @GetMapping("user")
    public String getUserView() {
        return "user";
    }

    @GetMapping("error")
    public String getErreurView() {
        return "error";
    }


}
