package com.fromis.fromis.controller;

import com.fromis.fromis.entity.User;
import com.fromis.fromis.service.UserSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserSerivce userSerivce;

    @GetMapping("/login")
    public String login(){

        return"/user/login";
    }

    @GetMapping("/register")

    public String register(){

        return "/user/register";
    }
    @PostMapping("/register")
    public String register(User user) {

        userSerivce.save(user);

        return "redirect:/";
    }
}
