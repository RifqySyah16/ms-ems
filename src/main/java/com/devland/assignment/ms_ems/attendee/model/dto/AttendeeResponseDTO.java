package com.devland.assignment.ms_ems.attendee.model.dto;

import com.devland.assignment.ms_ems.eventmanagement.model.dto.EventManagementResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttendeeResponseDTO {
    private Long id;
    private String name;
    private String address;
    private EventManagementResponseDTO eventManagementResponseDTO;
}
