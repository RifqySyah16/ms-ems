package com.devland.assignment.ms_ems.attendee.model.dto;

import com.devland.assignment.ms_ems.attendee.model.Attendee;
import com.devland.assignment.ms_ems.eventmanagement.model.EventManagement;
import com.devland.assignment.ms_ems.eventmanagement.model.dto.EventManagementRequestDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttendeeRequestDTO {
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Address is required")
    private String address;

    @Valid
    private EventManagementRequestDTO eventManagementRequestDTO;

    public Attendee convertToEntity() {
        EventManagement eventManagement = this.eventManagementRequestDTO.convertToEntity();

        return Attendee.builder()
                .id(this.id)
                .name(this.name)
                .address(this.address)
                .eventManagement(eventManagement)
                .build();
    }
}
