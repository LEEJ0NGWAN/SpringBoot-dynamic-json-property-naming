package com.example.dynamicjsonpropertynaming.user.exception;

import java.util.List;

import org.springframework.validation.FieldError;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
public class InvalidUserDataException extends RuntimeException {

    private final List<FieldError> errors;
}
