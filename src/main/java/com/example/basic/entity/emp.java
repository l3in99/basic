package com.example.basic.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class emp {
    @Id
    int EMPNO;
    String ename;
    String job;
    int mgr;
    Date hiredate;
    int sal;
    int comm;
    byte deptno;
}
