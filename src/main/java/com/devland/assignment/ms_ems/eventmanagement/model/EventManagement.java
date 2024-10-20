package com.devland.assignment.ms_ems.eventmanagement.model;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.devland.assignment.ms_ems.admin.model.Admin;
import com.devland.assignment.ms_ems.admin.model.dto.AdminResponseDTO;
import com.devland.assignment.ms_ems.attendee.model.Attendee;
import com.devland.assignment.ms_ems.category.model.Category;
import com.devland.assignment.ms_ems.category.model.dto.CategoryResponseDTO;
import com.devland.assignment.ms_ems.eventmanagement.model.dto.EventManagementResponseDTO;
import com.devland.assignment.ms_ems.tag.model.Tag;
import com.devland.assignment.ms_ems.tag.model.dto.TagResponseDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
public class EventManagement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String location;

    private int capacity;

    @CreationTimestamp
    private Timestamp cretaedAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @OneToMany(mappedBy = "event")
    private List<Attendee> attendees;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany
    @JoinTable(name = "event_tags", joinColumns = @JoinColumn(name = "event_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags;

    public EventManagementResponseDTO convertToResponse() {
        AdminResponseDTO adminResponseDTO = this.admin.convertToResponse();
        CategoryResponseDTO categoryResponseDTO = this.category.convertToResponse();
        List<TagResponseDTO> tagResponseDTOs = this.tags.stream().map(Tag::covertToResponse).toList();

        return EventManagementResponseDTO.builder()
                .id(this.id)
                .title(this.title)
                .description(this.description)
                .location(this.location)
                .capacity(this.capacity)
                .createdAt(this.updatedAt)
                .updatedAt(this.updatedAt)
                .adminResponseDTOn(adminResponseDTO)
                .categoryResponseDTO(categoryResponseDTO)
                .tagResponseDTOs(tagResponseDTOs)
                .build();
    }

    public int getRemainingCapacity() {
        return this.capacity - this.attendees.size();
    }
}