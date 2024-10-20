package com.devland.assignment.ms_ems.eventmanagement;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devland.assignment.ms_ems.eventmanagement.model.EventManagement;

public interface EventManagementRepository extends JpaRepository<EventManagement, Long> {

    Page<EventManagement> findAllByTitleContainsIgnoreCase(String title, Pageable pageable);

    Optional<EventManagement> findByTitle(String title);

}
