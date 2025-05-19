package ru.yandex.practicum.catsgram.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import ru.yandex.practicum.catsgram.validation.impl.LoginValidator;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Constraint(validatedBy = LoginValidator.class)
@Target({FIELD, METHOD, CONSTRUCTOR, PARAMETER, LOCAL_VARIABLE})
@Retention(RUNTIME)
public @interface Login {

	String message() default "Login cannot be empty or contain spaces!";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
