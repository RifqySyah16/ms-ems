package com.devland.assignment.ms_ems.eventmanagement.model.dto;

import java.util.List;

import com.devland.assignment.ms_ems.category.model.Category;
import com.devland.assignment.ms_ems.category.model.dto.CategoryRequestDTO;
import com.devland.assignment.ms_ems.eventmanagement.model.EventManagement;
import com.devland.assignment.ms_ems.tag.model.Tag;
import com.devland.assignment.ms_ems.tag.model.dto.TagRequestDTO;

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
    private CategoryRequestDTO categoryRequestDTO;

    @Valid
    private List<TagRequestDTO> tagRequestDTOs;

    public EventManagement convertToEntity() {
        Category category = this.categoryRequestDTO.convertToEntity();
        List<Tag> tags = this.tagRequestDTOs.stream().map(TagRequestDTO::convertToEntity).toList();

        return EventManagement.builder()
                .id(this.id)
                .title(this.title)
                .description(this.description)
                .location(this.location)
                .capacity(this.capacity)
                .category(category)
                .tags(tags)
                .build();
    }
}
