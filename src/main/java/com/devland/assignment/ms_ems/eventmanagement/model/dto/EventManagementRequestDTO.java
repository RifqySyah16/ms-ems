package com.devland.assignment.ms_ems.eventmanagement.model.dto;

import java.util.List;

import com.devland.assignment.ms_ems.admin.model.Admin;
import com.devland.assignment.ms_ems.admin.model.dto.AdminEventRequestDTO;
import com.devland.assignment.ms_ems.category.model.Category;
import com.devland.assignment.ms_ems.category.model.dto.CategoryEventRequestDTO;
import com.devland.assignment.ms_ems.eventmanagement.model.EventManagement;
import com.devland.assignment.ms_ems.tag.model.Tag;
import com.devland.assignment.ms_ems.tag.model.dto.TagEventRequestDTO;

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

    @Valid
    private AdminEventRequestDTO adminEventRequestDTO;

    @Valid
    private CategoryEventRequestDTO categoryEventRequestDTO;

    @Valid
    private List<TagEventRequestDTO> tagEventRequestDTOs;

    public EventManagement convertToEntity() {
        Admin admin = this.adminEventRequestDTO.convertToEntity();
        Category category = this.categoryEventRequestDTO.convertToEntity();
        List<Tag> tags = this.tagEventRequestDTOs.stream().map(TagEventRequestDTO::converttoEntity).toList();

        return EventManagement.builder()
                .id(this.id)
                .title(this.title)
                .description(this.description)
                .location(this.location)
                .capacity(this.capacity)
                .admin(admin)
                .category(category)
                .tags(tags)
                .build();
    }
}
