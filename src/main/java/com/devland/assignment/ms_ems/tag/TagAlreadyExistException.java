package com.devland.assignment.ms_ems.tag;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class TagAlreadyExistException extends RuntimeException {

    public TagAlreadyExistException(String message) {
        super(message);
    }
}