package com.devland.assignment.ms_ems.eventmanagement.model.dto;

import java.time.LocalDateTime;

import com.devland.assignment.ms_ems.category.model.Category;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventManagementRequestDTO {
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Location is required")
    private String location;

    @NotNull(message = "Capacity is required")
    private int capacity;

    @NotNull(message = "Event start time is required")
    private LocalDateTime eventStart;

    @NotNull(message = "Event end time is required")
    private LocalDateTime eventEnd;

    @Valid
    private Category category;
}
