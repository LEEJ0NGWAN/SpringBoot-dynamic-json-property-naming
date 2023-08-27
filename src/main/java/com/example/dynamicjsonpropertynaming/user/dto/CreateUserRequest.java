package com.example.dynamicjsonpropertynaming.user.dto;

import javax.validation.constraints.NotBlank;

import com.example.dynamicjsonpropertynaming.user.deserializer.CreateUserRequestDeserializer;
import com.example.dynamicjsonpropertynaming.user.validation.PhoneNumber;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

@Getter @Setter @ToString @FieldNameConstants
@JsonDeserialize(using = CreateUserRequestDeserializer.class)
public class CreateUserRequest {

    @NotBlank
    private String lastName;

    @NotBlank
    private String firstName;

    @PhoneNumber
    private String phoneNumber;
}
