package com.devland.assignment.ms_ems.admin.model;

import java.util.List;

import com.devland.assignment.ms_ems.admin.model.dto.AdminResponseDTO;
import com.devland.assignment.ms_ems.eventmanagement.model.EventManagement;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String username;

    private String password;

    @OneToMany(mappedBy = "admin")
    private List<EventManagement> eventManagements;

    public AdminResponseDTO convertToResponse() {
        return AdminResponseDTO.builder()
                .id(this.id)
                .name(this.name)
                .email(this.email)
                .username(this.username)
                .password(password)
                .build();
    }
}
