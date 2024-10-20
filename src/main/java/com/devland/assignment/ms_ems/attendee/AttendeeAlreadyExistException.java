package com.devland.assignment.ms_ems.attendee;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class AttendeeAlreadyExistException extends RuntimeException {

    public AttendeeAlreadyExistException(String message) {
        super(message);
    }
}
