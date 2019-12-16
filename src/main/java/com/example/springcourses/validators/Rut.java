package com.example.springcourses.validators;

import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = RutValidator.class)
@Documented
public @interface Rut {

    String message() default "Rut is invalid.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
