package com.devland.assignment.ms_ems.category;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devland.assignment.ms_ems.category.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Page<Category> findAllByNameContainsIgnoreCase(String name, Pageable pageable);

    Optional<Category> findByName(String name);

}
