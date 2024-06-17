package com.example.basic.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Player {
    @Id
    int playerId;
    String playerName;
    @ManyToOne
    @JoinColumn(name="team_id")
    Team team;
    }