package com.example.basic.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Data
public class major {
    @Id
    @GeneratedValue
    Integer id;
    @Column(length = 255, nullable = true)
    String name;
    @Column(nullable = false)
    Integer maxPrsn;
    @Column(nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    Date ebtbDate;
}
