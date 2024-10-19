package com.devland.assignment.ms_ems.admin.model.dto;

import com.devland.assignment.ms_ems.admin.model.Admin;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminRequestDTO {
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;

    public Admin convertToEntity() {
        return Admin.builder()
                .id(this.id)
                .name(this.name)
                .email(this.email)
                .username(this.username)
                .password(this.password)
                .build();
    }
}
