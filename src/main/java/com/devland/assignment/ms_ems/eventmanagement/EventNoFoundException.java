package com.devland.assignment.ms_ems.eventmanagement;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class EventNoFoundException extends RuntimeException {

    public EventNoFoundException(String message) {
        super(message);
    }
}
