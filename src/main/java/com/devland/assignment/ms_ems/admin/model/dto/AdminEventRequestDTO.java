package com.devland.assignment.ms_ems.admin.model.dto;

import com.devland.assignment.ms_ems.admin.model.Admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminEventRequestDTO {
    private Long id;

    public Admin convertToEntity() {
        return Admin.builder()
                .id(this.id)
                .build();
    }
}
