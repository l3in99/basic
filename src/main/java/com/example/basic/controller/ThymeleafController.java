package com.example.basic.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.basic.model.Hospital;
import com.example.basic.repository.HospitalRepository;

@Controller
public class ThymeleafController {

    @Autowired
    HospitalRepository hr;

    @GetMapping("/welcome")
    public String welcome(Model model) {
        List<Hospital> elist = hr.findAll();
        model.addAttribute("elist", elist);

        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");

        Map<String, Object> map = new HashMap<>();
        map.put("k1", "값1");
        map.put("k2", "값2");

        model.addAttribute("list", list);
        model.addAttribute("map", map);

        return "welcome";

    }

    @GetMapping("user")
    public String user(Model model) {
        Map<String, Object> user = null;
        user = new HashMap<>();
        user.put("userId", "z");
        user.put("userName", "zoo");
        user.put("userAge", 25);
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("userList")
    public String userList(Model model) {
        List<Map<String, Object>> userList = new ArrayList<>();
        Map<String, Object> user = null;
        user = new HashMap<>();
        user.put("userId", "a");
        user.put("userName", "apple");
        user.put("userAge", 21);
        userList.add(user);
        user = new HashMap<>();
        user.put("userId", "b");
        user.put("userName", "banana");
        user.put("userAge", 22);
        userList.add(user);
        user = new HashMap<>();
        user.put("userId", "c");
        user.put("userName", "carrot");
        user.put("userAge", 23);
        userList.add(user);
        model.addAttribute("userList", userList);
        return "userList";
    }
}
