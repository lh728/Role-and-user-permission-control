package com.example.dao;

import com.example.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleDao extends JpaRepository<Role, Integer> {
    List<Role> findAllByRoleName(String roleName);
    void deleteByRoleName(String roleName);
}
