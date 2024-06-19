package com.example.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.basic.entity.User;


public interface UserRepository extends JpaRepository<User, String>{
    public User findByUserIdAndUserPw(String userId, String userPw);
}
