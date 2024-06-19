package com.example.basic.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.basic.model.Emp;
import com.example.basic.model.json;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class RequestController {
    @Autowired
    JdbcTemplate jt;

    @GetMapping("req/http")
    public String http(HttpServletRequest request) {
        String name = request.getParameter("name");
        String pageNum = request.getParameter("pageNum");
        return name + ", " + pageNum;
    }

    @GetMapping("req/param1")
    public String param1(
            @RequestParam("key1") String key1,
            @RequestParam("key2") String key2) {
        return key1 + ", " + key2;
    }

    @GetMapping("req/emp")
    public List<Map<String, Object>> reqEmp(
            @RequestParam(value = "page", defaultValue = "1", required = false) String page,
            @RequestParam(value = "ename", defaultValue = "", required = false) String ename) {
        int pageNum = Integer.parseInt(page) * 5 - 5;
        String sql = "select * from emp" + " where ename like concat('%', '" + ename + "', '%')" + " limit " + pageNum
                + " , 5";
        return jt.queryForList(sql);
    }

    @GetMapping("req/path/{path1}/{path2}")
    public String path(
            @PathVariable("path1") String path1,
            @PathVariable("path2") String path2) {
        return path1 + ", " + path2;
    }

    @GetMapping("req/emp2")
    public List<Map<String, Object>> reqEmp2(
            @ModelAttribute("emp") Emp emp) {
        int pageNum = Integer.parseInt(emp.getPage()) * 5 - 5;
        String sql = "select * from emp" + " where ename like concat('%', '" + emp.getEname() + "', '%')" + " limit "
                + pageNum
                + " , 5";
        return jt.queryForList(sql);
    }

    @GetMapping("/req/data")
    public Map<String, String> getData(@RequestParam Map<String, String> map) {
        return map;
    }

    @CrossOrigin
    @GetMapping("/req/get")
    public Map<String, String> get(@RequestParam Map<String, String> map) {
        return map;
    }

    @CrossOrigin
    @PostMapping("/req/post")
    public Map<String, String> post(@RequestParam Map<String, String> map) {
        return map;
    }

    @CrossOrigin
    @PostMapping("/req/json")
    public json json(@RequestBody json J) {
        return J;
    }
}
