package com.devland.assignment.ms_ems.admin;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class AdminAlreadyExistException extends RuntimeException {

    public AdminAlreadyExistException(String message) {
        super(message);
    }
}
