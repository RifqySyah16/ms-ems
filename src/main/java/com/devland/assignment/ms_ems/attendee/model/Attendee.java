package com.devland.assignment.ms_ems.attendee.model;

import com.devland.assignment.ms_ems.attendee.model.dto.AttendeeResponseDTO;
import com.devland.assignment.ms_ems.eventmanagement.model.EventManagement;
import com.devland.assignment.ms_ems.eventmanagement.model.dto.EventManagementResponseDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Attendee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private EventManagement eventManagement;

    public AttendeeResponseDTO convertToResponse() {
        EventManagementResponseDTO eventManagementResponseDTO = this.eventManagement.convertToResponse();

        return AttendeeResponseDTO.builder()
                .id(this.id)
                .name(this.name)
                .address(this.address)
                .eventManagementResponseDTO(eventManagementResponseDTO)
                .build();
    }
}