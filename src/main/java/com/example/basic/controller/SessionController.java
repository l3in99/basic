package com.example.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.basic.model.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class SessionController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(User user, HttpSession session) {
        session.setAttribute("user", user);
        return "redirect:/main";
    }

    @GetMapping("/main")
    public String main() {
        return "main";
    }
}
