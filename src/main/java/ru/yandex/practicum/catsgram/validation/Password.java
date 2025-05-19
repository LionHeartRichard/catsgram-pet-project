package ru.yandex.practicum.catsgram.validation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;

import static java.lang.annotation.ElementType.PARAMETER;

import ru.yandex.practicum.catsgram.validation.impl.PasswordValidator;

@Constraint(validatedBy = PasswordValidator.class)
@Target({FIELD, METHOD, CONSTRUCTOR, PARAMETER, LOCAL_VARIABLE})
@Retention(RUNTIME)
public @interface Password {

	String message() default "Invalid password! Password need have eight characters or more\n"
			+ "Include a capital letter\n" + "Use at least one lowercase letter\n" + "Consists of at least one digit\n"
			+ "Need to have one special symbol (i.e., @, #, $, %, etc.)\n" + "Doesn’t contain space, tab, etc. ";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
