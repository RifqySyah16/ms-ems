package com.devland.assignment.ms_ems.tag.model.dto;

import com.devland.assignment.ms_ems.tag.model.Tag;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TagEventRequestDTO {
    private Long id;

    public Tag converttoEntity() {
        return Tag.builder()
                .id(this.id)
                .build();
    }
}
