package com.example.dao;

import com.example.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorityDao extends JpaRepository<Authority, Integer> {
    List<Authority> findAllByAuthorityName(String authorityName);
    void deleteByAuthorityName(String authorityName);
}
