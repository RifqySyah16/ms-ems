package com.devland.assignment.ms_ems.category;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devland.assignment.ms_ems.category.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
