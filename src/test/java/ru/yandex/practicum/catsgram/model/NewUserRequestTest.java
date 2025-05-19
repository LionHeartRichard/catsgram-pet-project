package ru.yandex.practicum.catsgram.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.util.Set;

import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.catsgram.dto.NewUserRequest;

@Slf4j
public class NewUserRequestTest {

	private static Validator validator;

	static {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();

	}

	@Test
	public void createUserPositiveTest() {
		final NewUserRequest request = new NewUserRequest("dolores", "Dolores", "dolores@yandex.ru", "DoloRes@12_89",
				Instant.now());

		Set<ConstraintViolation<NewUserRequest>> violations = validator.validate(request);

		assertTrue(violations.isEmpty());
	}

	@Test
	public void negativeTestNotValidPasswordNotSpecSymbols() {
		final NewUserRequest request = new NewUserRequest("dolores", "Dolores", "dolores@yandex.ru", "DoloRes1289",
				Instant.now());

		Set<ConstraintViolation<NewUserRequest>> violations = validator.validate(request);
		violations.forEach(v -> log.error(v.getMessage()));

		assertFalse(violations.isEmpty());
	}

	@Test
	public void negativeTestNotValidPasswordMin() {
		final NewUserRequest request = new NewUserRequest("dolores", "Dolores", "dolores@yandex.ru", "DloR@1_",
				Instant.now());

		Set<ConstraintViolation<NewUserRequest>> violations = validator.validate(request);
		violations.forEach(v -> log.error(v.getMessage()));

		assertFalse(violations.isEmpty());
	}

	@Test
	public void negativeTestNotValidPasswordMax() {

		final NewUserRequest request = new NewUserRequest("dolores", "Dolores", "dolores@yandex.ru",
				"DolodfgdfgdfgdfgdsfhfshsdhsdfhdfhdfhdsfhRes@12_89", Instant.now());

		Set<ConstraintViolation<NewUserRequest>> violations = validator.validate(request);
		violations.forEach(v -> log.error(v.getMessage()));

		assertFalse(violations.isEmpty());
	}

	@Test
	public void negativeTestNotValidPasswordSpace() {
		final NewUserRequest request = new NewUserRequest("dolores", "Dolores", "dolores@yandex.ru", "DoloRes1289",
				Instant.now());

		Set<ConstraintViolation<NewUserRequest>> violations = validator.validate(request);
		violations.forEach(v -> log.error(v.getMessage()));

		assertFalse(violations.isEmpty());
	}

	@Test
	public void negativeTestNotValidPasswordLowerCase() {
		final NewUserRequest request = new NewUserRequest("dolores", "Dolores", "dolores@yandex.ru", "DloR@1_",
				Instant.now());

		Set<ConstraintViolation<NewUserRequest>> violations = validator.validate(request);
		violations.forEach(v -> log.error(v.getMessage()));

		assertFalse(violations.isEmpty());
	}

	@Test
	public void negativeTestNotValidPasswordUpperCase() {
		final NewUserRequest request = new NewUserRequest("dolores", "Dolores", "dolores@yandex.ru",
				"DoloRes@12_DoloRes@12", Instant.now());

		Set<ConstraintViolation<NewUserRequest>> violations = validator.validate(request);
		violations.forEach(v -> log.error(v.getMessage()));

		assertFalse(violations.isEmpty());
	}

}
