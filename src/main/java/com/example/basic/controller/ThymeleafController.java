package com.example.basic.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.basic.model.Hospital;
import com.example.basic.repository.HospitalRepository;

@Controller
public class ThymeleafController {

    @GetMapping("/welcome")
    public String welcome(Model model) {
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

    @GetMapping("/user")
    public String user(Model model) {
        Map<String, Object> user = null;
        user = new HashMap<>();
        user.put("userId", "z");
        user.put("userName", "zoo");
        user.put("userAge", 25);
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/userList")
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

    @Autowired
    HospitalRepository hr;

    @GetMapping("/hospital")
    public String hospital(Model model, @RequestParam(defaultValue = "1")int page) {
        Order ord = Order.asc("sido");
        Order ord2 = Order.desc("name");
        Sort sort = Sort.by(ord, ord2);
        Pageable pagable = PageRequest.of(page,10, sort);
        Page<Hospital> list = hr.findAll(pagable);
        model.addAttribute("elist", list.getContent());

        int startPage = (page - 1) / 10 * 10 + 1;
        int endPage = startPage + 9;
        int totalPage = list.getTotalPages();
        if(endPage>totalPage) endPage = totalPage;

        model.addAttribute("totalPage", totalPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("page", page);
        return "hospital";
    }

    @GetMapping("mode")
    public String mode(
            Model model, @RequestParam Map<String, Object> map) {
        model.addAttribute("now", map.get("now") == null ? "am" : map.get("now"));
        model.addAttribute("name", map.get("name"));
        model.addAttribute("auth", map.get("auth"));
        model.addAttribute("category", map.get("category"));
        return "mode";
    }

    @GetMapping("pagination")
    public String pagination(
            Model model, @RequestParam(defaultValue = "1") int page) {
        int startPage = (page - 1) / 10 * 10 + 1;
        int endPage = startPage + 9;
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("page", page);
        return "pagination";
    }

    @GetMapping("linkUp")
    public String linkUp(
            Model model, @RequestParam(defaultValue = "1") int page) {
        int startPage = (page - 1) / 10 * 10 + 1;
        int endPage = startPage + 9;
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("page", page);
        return "linkUp";
    }

    @GetMapping("insert1")
    public String insert1() {
        return "insert1";
    }
}
