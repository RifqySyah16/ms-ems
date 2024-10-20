package com.devland.assignment.ms_ems.attendee;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class AttendeeNotFoundException extends RuntimeException {

    public AttendeeNotFoundException(String message) {
        super(message);
    }
}
