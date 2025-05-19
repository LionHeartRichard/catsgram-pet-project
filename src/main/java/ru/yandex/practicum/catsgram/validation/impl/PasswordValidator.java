package ru.yandex.practicum.catsgram.validation.impl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraintvalidation.SupportedValidationTarget;
import jakarta.validation.constraintvalidation.ValidationTarget;
import ru.yandex.practicum.catsgram.util.PatternUtil;
import ru.yandex.practicum.catsgram.validation.Password;

@SupportedValidationTarget(ValidationTarget.ANNOTATED_ELEMENT)
public class PasswordValidator implements ConstraintValidator<Password, String> {

	@Override
	public boolean isValid(String password, ConstraintValidatorContext context) {
		if (password == null || password.isEmpty())
			return false;
		return password.matches(PatternUtil.PASSWORD);
	}

}
