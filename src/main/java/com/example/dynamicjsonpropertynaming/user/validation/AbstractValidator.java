package com.example.dynamicjsonpropertynaming.user.validation;

import java.lang.annotation.Annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public abstract class AbstractValidator<A extends Annotation>
implements ConstraintValidator<A, String> {

    private static final String blankErrorMessage = "must not be blank";

    protected static void changeMessage(ConstraintValidatorContext context, String message) {

        if (context==null||message==null) return;

        context.disableDefaultConstraintViolation();
        context
        .buildConstraintViolationWithTemplate(message)
        .addConstraintViolation();
    }

    protected static void blankErrorMessage(ConstraintValidatorContext context) {

        changeMessage(context, blankErrorMessage);
    }
}
