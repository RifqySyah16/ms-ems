package com.devland.assignment.ms_ems.eventmanagement;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devland.assignment.ms_ems.admin.AdminService;
import com.devland.assignment.ms_ems.admin.model.Admin;
import com.devland.assignment.ms_ems.category.CategoryService;
import com.devland.assignment.ms_ems.category.model.Category;
import com.devland.assignment.ms_ems.eventmanagement.model.EventManagement;
import com.devland.assignment.ms_ems.tag.TagService;
import com.devland.assignment.ms_ems.tag.model.Tag;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventManagementService {
    private final EventManagementRepository eventManagementRepository;
    private final AdminService adminService;
    private final CategoryService categoryService;
    private final TagService tagService;

    public Page<EventManagement> getAll(Optional<String> optionalTitle, Pageable pageable) {
        if (optionalTitle.isPresent()) {
            return this.eventManagementRepository.findAllByTitleContainsIgnoreCase(optionalTitle.get(), pageable);
        }

        return this.eventManagementRepository.findAll(pageable);
    }

    public EventManagement getOne(Long id) {
        return this.eventManagementRepository.findById(id)
                .orElseThrow(() -> new EventNoFoundException("Event No Found"));
    }

    public EventManagement create(EventManagement newEventManagement) {
        Optional<EventManagement> existingEventManagement = this.eventManagementRepository
                .findByTitle(newEventManagement.getTitle());
        if (existingEventManagement.isPresent()) {
            throw new EventAlreadyExistException("Event already exist");
        }

        Admin existingAdmin = this.adminService.getOne(newEventManagement.getAdmin().getId());
        newEventManagement.setAdmin(existingAdmin);

        Category existingCategory = this.categoryService.getOne(newEventManagement.getCategory().getId());
        newEventManagement.setCategory(existingCategory);

        List<Tag> existingTags = this.tagService.findAllIn(newEventManagement.getTags());
        newEventManagement.setTags(existingTags);

        return this.eventManagementRepository.save(newEventManagement);
    }

    public EventManagement update(EventManagement updatedEventManagement) {
        EventManagement existingEventManagement = this.getOne(updatedEventManagement.getId());
        updatedEventManagement.setId(existingEventManagement.getId());

        return this.eventManagementRepository.save(updatedEventManagement);
    }

    public void delete(Long id) {
        EventManagement existingEventManagement = this.getOne(id);
        this.eventManagementRepository.deleteById(existingEventManagement.getId());
    }
}
