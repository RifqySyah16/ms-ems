package com.devland.assignment.ms_ems.eventmanagement.model.dto;

import com.devland.assignment.ms_ems.eventmanagement.model.EventManagement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventAttendeeRequestDTO {
    private Long id;

    public EventManagement convertToEntity() {
        return EventManagement.builder()
                .id(this.id)
                .build();
    }
}
