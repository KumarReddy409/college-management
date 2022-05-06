package com.student.exception;

import lombok.Value;
import org.springframework.http.HttpStatus;


public record StudentException(HttpStatus httpStatus, String message) {
}
