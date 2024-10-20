package com.devland.assignment.ms_ems.attendee;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devland.assignment.ms_ems.attendee.model.Attendee;
import com.devland.assignment.ms_ems.attendee.model.dto.AttendeeRequestDTO;
import com.devland.assignment.ms_ems.attendee.model.dto.AttendeeResponseDTO;

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
@RequestMapping("/attendees")
public class AttendeeController {
    private final AttendeeService attendeeService;

    @GetMapping
    public ResponseEntity<Page<AttendeeResponseDTO>> getAll(
            @RequestParam("name") Optional<String> optionalName,
            @RequestParam(value = "sort", defaultValue = "ASC") String sortString,
            @RequestParam(value = "order_by", defaultValue = "id") String orderBy,
            @RequestParam(value = "limit", defaultValue = "5") int limit,
            @RequestParam(value = "page", defaultValue = "1") int page) {

        Sort sort = Sort.by(Sort.Direction.valueOf(sortString), "id");
        Pageable pageable = PageRequest.of(page - 1, limit, sort);
        Page<Attendee> pageAttendees = this.attendeeService.getAll(optionalName, pageable);
        Page<AttendeeResponseDTO> attendeeResponseDTOs = pageAttendees.map(Attendee::convertToResponse);

        return ResponseEntity.ok(attendeeResponseDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttendeeResponseDTO> getOne(@PathVariable("id") Long id) {
        Attendee existingAttendee = this.attendeeService.getOne(id);
        AttendeeResponseDTO attendeeResponseDTO = existingAttendee.convertToResponse();

        return ResponseEntity.ok(attendeeResponseDTO);
    }

    @PostMapping
    public ResponseEntity<AttendeeResponseDTO> create(@RequestBody AttendeeRequestDTO attendeeRequestDTO) {
        Attendee newAttendee = attendeeRequestDTO.convertToEntity();

        Attendee savAttendee = this.attendeeService.create(newAttendee);
        AttendeeResponseDTO attendeeResponseDTO = savAttendee.convertToResponse();

        return ResponseEntity.status(HttpStatus.CREATED).body(attendeeResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AttendeeResponseDTO> update(@PathVariable("id") Long id,
            @RequestBody AttendeeRequestDTO attendeeRequestDTO) {
        Attendee updatedAttendee = attendeeRequestDTO.convertToEntity();
        updatedAttendee.setId(id);
        Attendee savedAttendee = this.attendeeService.update(updatedAttendee);
        AttendeeResponseDTO attendeeResponseDTO = savedAttendee.convertToResponse();

        return ResponseEntity.ok(attendeeResponseDTO);
    }

    @DeleteMapping("id")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        this.attendeeService.delete(id);

        return ResponseEntity.ok().build();
    }
}
