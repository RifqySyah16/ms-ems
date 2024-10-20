package com.devland.assignment.ms_ems.eventmanagement;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devland.assignment.ms_ems.eventmanagement.model.EventManagement;
import com.devland.assignment.ms_ems.eventmanagement.model.dto.EventManagementRequestDTO;
import com.devland.assignment.ms_ems.eventmanagement.model.dto.EventManagementResponseDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping("event-managements")
public class EventManagementController {
    private final EventManagementService eventManagementService;

    @GetMapping
    public ResponseEntity<Page<EventManagementResponseDTO>> getAll(
            @RequestParam("title") Optional<String> optionalTitle,
            @RequestParam(value = "sort", defaultValue = "ASC") String sortString,
            @RequestParam(value = "order_by", defaultValue = "id") String orderBy,
            @RequestParam(value = "limit", defaultValue = "5") int limit,
            @RequestParam(value = "page", defaultValue = "1") int page) {

        Sort sort = Sort.by(Sort.Direction.valueOf(sortString), "id");
        Pageable pageable = PageRequest.of(page - 1, limit, sort);
        Page<EventManagement> pageEventManagements = this.eventManagementService.getAll(optionalTitle, pageable);
        Page<EventManagementResponseDTO> eventManagementResponseDTOs = pageEventManagements
                .map(EventManagement::convertToResponse);

        return ResponseEntity.ok(eventManagementResponseDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventManagementResponseDTO> getOne(@PathVariable("id") Long id) {
        EventManagement existingEventManagement = this.eventManagementService.getOne(id);
        EventManagementResponseDTO eventManagementResponseDTO = existingEventManagement.convertToResponse();

        return ResponseEntity.ok(eventManagementResponseDTO);
    }

    @GetMapping("/remaining-capacity/{id}")
    public ResponseEntity<Integer> getRemainingCapacity(@PathVariable("id") Long id) {
        EventManagement existingEventManagement = this.eventManagementService.getOne(id);
        int remainingCapacity = existingEventManagement.getRemainingCapacity();

        return ResponseEntity.ok(remainingCapacity);
    }

    @PostMapping
    public ResponseEntity<EventManagementResponseDTO> create(
            @RequestBody @Valid EventManagementRequestDTO eventManagementRequestDTO) {
        EventManagement newEventManagement = eventManagementRequestDTO.convertToEntity();

        EventManagement savedEventManagement = this.eventManagementService.create(newEventManagement);
        EventManagementResponseDTO eventManagementResponseDTO = savedEventManagement.convertToResponse();

        return ResponseEntity.status(HttpStatus.CREATED).body(eventManagementResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventManagementResponseDTO> update(@PathVariable("id") Long id,
            @RequestBody EventManagementRequestDTO eventManagementRequestDTO) {
        EventManagement updatedEventManagement = eventManagementRequestDTO.convertToEntity();
        updatedEventManagement.setId(id);
        EventManagement savedEventManagement = this.eventManagementService.update(updatedEventManagement);
        EventManagementResponseDTO eventManagementResponseDTO = savedEventManagement.convertToResponse();

        return ResponseEntity.ok(eventManagementResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        this.eventManagementService.delete(id);

        return ResponseEntity.ok().build();
    }
}
