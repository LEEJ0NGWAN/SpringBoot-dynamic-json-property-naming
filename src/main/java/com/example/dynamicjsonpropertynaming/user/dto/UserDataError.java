package com.example.dynamicjsonpropertynaming.user.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class UserDataError {

    private List<Error> errors;

    @Getter @Setter @Builder
    public static class Error {

        private String field;
        private String message;
    }
}
