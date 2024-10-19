package com.devland.assignment.ms_ems.admin;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devland.assignment.ms_ems.admin.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    Page<Admin> findAllByNameContainsIgnoreCase(String name, Pageable pageable);

    Optional<Admin> findByUsername(String username);

}
