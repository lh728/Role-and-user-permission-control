package com.example.dao;

import com.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDao extends JpaRepository<User, Integer> {
    List<User> findAllByUserName(String userName);
    void deleteByUserName(String userName);
    List<User> findAllByUserId(Integer userId);
}

