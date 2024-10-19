package com.devland.assignment.ms_ems.eventmanagement;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devland.assignment.ms_ems.eventmanagement.model.EventManagement;

public interface EventManagementRepository extends JpaRepository<EventManagement, Long> {

}
