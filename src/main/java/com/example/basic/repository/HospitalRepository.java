package com.example.basic.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.basic.model.Hospital;

import java.util.List;
import java.util.Optional;



public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
    public Optional<Hospital> findById(Integer id);
    public Hospital findBySido(String sido);
    public List<Hospital> findBySidoLike(String sido, Pageable pageable);
    public List<Hospital> findByAddressLike(String address, Pageable pageable);
}