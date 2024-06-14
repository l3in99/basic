package com.example.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.basic.model.Hospital;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {

}