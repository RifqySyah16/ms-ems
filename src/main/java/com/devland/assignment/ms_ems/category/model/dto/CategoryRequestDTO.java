package com.devland.assignment.ms_ems.category.model.dto;

import com.devland.assignment.ms_ems.category.model.Category;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequestDTO {
    private Long id;

    @NotBlank(message = "Category name is required")
    private String name;

    public Category convertToEntity() {
        return Category.builder()
                .id(this.id)
                .name(this.name)
                .build();
    }
}
