package com.example.dynamicjsonpropertynaming.user.controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dynamicjsonpropertynaming.user.dto.CreateUserRequest;
import com.example.dynamicjsonpropertynaming.user.exception.InvalidUserDataException;

@RestController
@RequestMapping("${api.path.user}")
public class UserController {

    @PostMapping
    public String createUser(@Validated @RequestBody CreateUserRequest user, BindingResult result) {

        if (result.hasErrors())
        throw new InvalidUserDataException(result.getFieldErrors());

        return "sibal";
    }
}
