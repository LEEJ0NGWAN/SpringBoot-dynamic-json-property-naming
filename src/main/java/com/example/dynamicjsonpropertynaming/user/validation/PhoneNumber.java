package com.example.dynamicjsonpropertynaming.user.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = PhoneNumberValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumber {

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    int size() default 11;
    String prefix() default "010";
    String message() default "must be a number of length {size} and started with {prefix}";
}

class PhoneNumberValidator extends AbstractValidator<PhoneNumber> {

    private int phoneNumberSize;
    private String phoneNumberPrefix;

    @Override
    public void initialize(PhoneNumber constraintAnnotation) {

        phoneNumberSize = constraintAnnotation.size();
        phoneNumberPrefix = constraintAnnotation.prefix();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value==null||value.isEmpty()) {

            blankErrorMessage(context);
            return false;
        }

        boolean result =
        value.length()==phoneNumberSize&&
        value.startsWith(phoneNumberPrefix);

        if (result)
        for (int i = phoneNumberPrefix.length(); i<phoneNumberSize; i++)
        if (!(result&=Character.isDigit(value.charAt(i)))) break;

        return result;
    }
}
