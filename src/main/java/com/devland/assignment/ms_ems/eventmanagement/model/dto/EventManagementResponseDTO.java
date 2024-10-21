package com.devland.assignment.ms_ems.eventmanagement.model.dto;

import java.sql.Timestamp;
import java.util.List;

import com.devland.assignment.ms_ems.admin.model.dto.AdminResponseDTO;
import com.devland.assignment.ms_ems.category.model.dto.CategoryResponseDTO;
import com.devland.assignment.ms_ems.tag.model.dto.TagResponseDTO;

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
public class EventManagementResponseDTO {
    private Long id;
    private String title;
    private String description;
    private String location;
    private int capacity;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private AdminResponseDTO adminResponseDTO;
    private CategoryResponseDTO categoryResponseDTO;
    private List<TagResponseDTO> tagResponseDTOs;
}
