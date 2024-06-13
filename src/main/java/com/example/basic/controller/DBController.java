package com.example.basic.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.basic.dao.DemoDao;
import com.example.basic.mapper.DemoMapper;
import com.example.basic.mapper.EmpMapper;

@RestController
public class DBController {
    @Autowired
    DemoDao demoDao;

    @GetMapping("/jdbc/demo")
    public List<Map<String, Object>> jdbcDemo() {
        return demoDao.select();
    }

    @Autowired
    DemoMapper demoMapper;

    @GetMapping("/mybatis/demo")
    public List<Map<String, Object>> mybatisDemo() {
        return demoMapper.select();
    }

    @Autowired EmpMapper empMapper;
    @GetMapping("/mybatis/emp")
    public List<Map<String, Object>> mybatisemp() {
        return empMapper.select();
    }

}
