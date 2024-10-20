package com.devland.assignment.ms_ems.tag;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devland.assignment.ms_ems.tag.model.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {

    Page<Tag> findAllByNameContainsIgnoreCase(String name, Pageable pageable);

    Optional<Tag> findByName(String name);

}
