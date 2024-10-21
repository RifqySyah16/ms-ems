package com.devland.assignment.ms_ems.category.model.dto;

import com.devland.assignment.ms_ems.category.model.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEventRequestDTO {
    private Long id;

    public Category convertToEntity() {
        return Category.builder()
                .id(this.id)
                .build();
    }
}
