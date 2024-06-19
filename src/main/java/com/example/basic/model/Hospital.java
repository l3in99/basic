package com.example.basic.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Hospital {
    @Id
    Integer id;
    String sido;
    String name;
    Integer medical;
    Integer room;
    String tel;
    String address;
}
