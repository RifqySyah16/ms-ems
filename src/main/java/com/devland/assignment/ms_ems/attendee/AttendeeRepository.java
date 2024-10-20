package com.devland.assignment.ms_ems.attendee;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devland.assignment.ms_ems.attendee.model.Attendee;

public interface AttendeeRepository extends JpaRepository<Attendee, Long> {

    Page<Attendee> findAllByNameContainsIgnoreCase(String name, Pageable pageable);

    Optional<Attendee> findByName(String name);

}
