package com.example.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.basic.entity.Player;

public interface PlayerRepository
extends JpaRepository<Player, Integer> {
}