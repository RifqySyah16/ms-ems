package com.devland.assignment.ms_ems.attendee;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devland.assignment.ms_ems.attendee.model.Attendee;
import com.devland.assignment.ms_ems.eventmanagement.EventManagementService;
import com.devland.assignment.ms_ems.eventmanagement.model.EventManagement;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttendeeService {
    private final AttendeeRepository attendeeRepository;
    private final EventManagementService eventManagementService;

    public Page<Attendee> getAll(Optional<String> optionalName, Pageable pageable) {
        if (optionalName.isPresent()) {
            return this.attendeeRepository.findAllByNameContainsIgnoreCase(optionalName.get(), pageable);
        }

        return this.attendeeRepository.findAll(pageable);
    }

    public Attendee getOne(Long id) {
        return this.attendeeRepository.findById(id)
                .orElseThrow(() -> new AttendeeNotFoundException("Attendee not found"));
    }

    public Attendee create(Attendee newAttendee) {
        Optional<Attendee> existingAttendee = this.attendeeRepository.findByName(newAttendee.getName());
        if (existingAttendee.isPresent()) {
            throw new AttendeeAlreadyExistException("Attendee already exist");
        }

        EventManagement existingEventManagement = this.eventManagementService
                .getOne(newAttendee.getEventManagement().getId());  
        this.capacityValidator(existingEventManagement);
        newAttendee.setEventManagement(existingEventManagement);
        

        return this.attendeeRepository.save(newAttendee);
    }

    public Attendee update(Attendee updatedAttendee) {
        Attendee existingAttendee = this.getOne(updatedAttendee.getId());
        updatedAttendee.setId(existingAttendee.getId());

        return this.attendeeRepository.save(updatedAttendee);
    }

    public void delete(Long id) {
        Attendee existingAttendee = this.getOne(id);
        this.attendeeRepository.deleteById(existingAttendee.getId());
    }

    private void capacityValidator(EventManagement existingEventManagement) {
        int currentAttendeeCount = existingEventManagement.getAttendees().size();
        if (currentAttendeeCount >= existingEventManagement.getCapacity()) {
            throw new EventCapacityExceededException("Event capacity exceeded");
        }
    }
}
