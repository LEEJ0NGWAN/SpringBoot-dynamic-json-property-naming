package com.example.dynamicjsonpropertynaming.user.advice;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestAttributes;

import com.example.dynamicjsonpropertynaming.user.dto.UserDataError;
import com.example.dynamicjsonpropertynaming.user.dto.UserDataError.Error;
import com.example.dynamicjsonpropertynaming.user.exception.InvalidUserDataException;

import static org.springframework.web.context.request.RequestContextHolder.getRequestAttributes;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestControllerAdvice
public class UserErrorHandler {

    @ExceptionHandler(InvalidUserDataException.class)
    public UserDataError invalidUserDataError(InvalidUserDataException exception) {

        final RequestAttributes requestAttributes = getRequestAttributes();

        final List<Error> errors = exception
        .getErrors()
        .stream()
        .map(error -> {

            final String fieldName = error.getField();
            final Object dynamicField = requestAttributes==null? null:
            requestAttributes.getAttribute(fieldName, RequestAttributes.SCOPE_REQUEST);

            return UserDataError.Error
            .builder()
            .field(dynamicField==null? fieldName: dynamicField.toString())
            .message(error.getDefaultMessage())
            .build();
        })
        .collect(toList());

        return UserDataError
        .builder()
        .errors(errors)
        .build();
    }
}
