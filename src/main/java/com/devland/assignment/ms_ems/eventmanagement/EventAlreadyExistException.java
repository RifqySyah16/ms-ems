package com.devland.assignment.ms_ems.eventmanagement;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class EventAlreadyExistException extends RuntimeException {

    public EventAlreadyExistException(String message) {
        super(message);
    }
}
