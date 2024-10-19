package com.devland.assignment.ms_ems.category.model;

import com.devland.assignment.ms_ems.category.model.dto.CategoryResponseDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public CategoryResponseDTO convertToResponse() {
        return CategoryResponseDTO.builder()
                .id(this.id)
                .name(this.name)
                .build();
    }
}
