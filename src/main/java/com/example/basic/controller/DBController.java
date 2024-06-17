package com.example.basic.controller;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.basic.dao.DemoDao;
import com.example.basic.entity.Player;
import com.example.basic.entity.Team;
import com.example.basic.mapper.DemoMapper;
import com.example.basic.mapper.EmpMapper;
import com.example.basic.model.Hospital;
import com.example.basic.model.TableExam1;
import com.example.basic.model.major;
import com.example.basic.repository.HospitalRepository;
import com.example.basic.repository.PlayerRepository;
import com.example.basic.repository.TableExam1Repository;
import com.example.basic.repository.TeamRepository;
import com.example.basic.repository.majorRepository;

@RestController
public class DBController {
    @Autowired DemoDao demoDao;
    @Autowired TableExam1Repository tableExam1Repository;
    @Autowired HospitalRepository hr;
    @Autowired TeamRepository tr;
    @Autowired PlayerRepository pr;

    @GetMapping("/team")
    public List<Team> team() {
        return tr.findAll();
    }

    @GetMapping("/player")
    public List<Player> player() {
        return pr.findAll();
    }
    
    @GetMapping("hospital/list")
    public List<Hospital> hoslist(){
        return hr.findAll();
    }

    @Autowired majorRepository mr; 
    @GetMapping("major/add")
    public major majoradd(@ModelAttribute major major){
        major.setEbtbDate(new Date());
        return mr.save(major);
    }

    @GetMapping("major/list")
    public List<major> majorlist(){
        return mr.findAll();
    }

    @GetMapping("/table1/add")
    public String table1Add(@RequestParam String title) {
        TableExam1 t1 = new TableExam1();
        t1.setTitle(title);
        tableExam1Repository.save(t1);
        return "입력 완료";
    }

    @GetMapping("/table1/remove")
    public String table1Remove(@RequestParam int id) {
        Optional<TableExam1> t = tableExam1Repository.findById(id);
        tableExam1Repository.delete(t.get());
        return "삭제 완료";
    }

    @GetMapping("/table1/list")
    public List<TableExam1> table1find() {
        List<TableExam1> list = tableExam1Repository.findAll();
        return list;
    }

    @GetMapping("/table1/{id}")
    public TableExam1 table1find2(@PathVariable int id) {
        Optional<TableExam1> exam1 = tableExam1Repository.findById(id);
        return exam1.get();
    }

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
    public List<Map<String, Object>> mybatisemp(@RequestParam("ename") String ename) {
        return empMapper.select(ename);
    }

    @GetMapping("/mybatis/demo/add")
    public String mybatisdemoadd(@RequestParam Map<String, Object> map) {

        try {
            demoMapper.insert(map);
        } catch (Exception e) {
            return "데이터 추가 실패";
        }
        return "데이터 추가 성공";
    }

}
