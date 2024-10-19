package com.devland.assignment.ms_ems.tag.model.dto;

import com.devland.assignment.ms_ems.tag.model.Tag;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TagRequestDTO {
    private Long id;

    @NotBlank(message = "Tag name is required")
    private String name;

    public Tag convertToEntity() {
        return Tag.builder()
                .id(this.id)
                .name(this.name)
                .build();
    }
}
