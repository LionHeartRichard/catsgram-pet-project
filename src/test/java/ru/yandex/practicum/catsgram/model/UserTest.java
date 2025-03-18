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

@Slf4j
public class UserTest {

	private static Validator validator;

	static {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void positiveTest() {
		final User user = User.builder().id(0L).login("dolores").name("Dolores").email("dolores@yandex.ru")
				.password("DoloRes@12_89").registrationDate(Instant.now()).build();
		Set<ConstraintViolation<User>> violations = validator.validate(user);

		violations.forEach(v -> log.error(v.getMessage()));

		assertTrue(violations.isEmpty());
	}

	@Test
	public void negativeTestNotValidPasswordNotSpecSymbols() {
		final User user = User.builder().id(0L).login("dolores").name("Dolores").email("dolores@yandex.ru")
				.password("DoloRes1289").registrationDate(Instant.now()).build();
		Set<ConstraintViolation<User>> violations = validator.validate(user);

		violations.forEach(v -> log.error(v.getMessage()));

		assertFalse(violations.isEmpty());
	}

	@Test
	public void negativeTestNotValidPasswordMin() {
		final User user = User.builder().id(0L).login("dolores").name("Dolores").email("dolores@yandex.ru")
				.password("DloR@1_").registrationDate(Instant.now()).build();
		Set<ConstraintViolation<User>> violations = validator.validate(user);

		violations.forEach(v -> log.error(v.getMessage()));

		assertFalse(violations.isEmpty());
	}

	@Test
	public void negativeTestNotValidPasswordMax() {
		final User user = User.builder().id(0L).login("dolores").name("Dolores").email("dolores@yandex.ru")
				.password("DoloRes@12_DoloRes@12").registrationDate(Instant.now()).build();
		Set<ConstraintViolation<User>> violations = validator.validate(user);

		violations.forEach(v -> log.error(v.getMessage()));

		assertFalse(violations.isEmpty());
	}

	@Test
	public void negativeTestNotValidPasswordSpace() {
		final User user = User.builder().id(0L).login("dolores").name("Dolores").email("dolores@yandex.ru")
				.password("Dolo Res@12").registrationDate(Instant.now()).build();
		Set<ConstraintViolation<User>> violations = validator.validate(user);

		violations.forEach(v -> log.error(v.getMessage()));

		assertFalse(violations.isEmpty());
	}

	@Test
	public void negativeTestNotValidPasswordLowerCase() {
		final User user = User.builder().id(0L).login("dolores").name("Dolores").email("dolores@yandex.ru")
				.password("dolores@12_89").registrationDate(Instant.now()).build();
		Set<ConstraintViolation<User>> violations = validator.validate(user);

		violations.forEach(v -> log.error(v.getMessage()));

		assertFalse(violations.isEmpty());
	}

	@Test
	public void negativeTestNotValidPasswordUpperCase() {
		final User user = User.builder().id(0L).login("dolores").name("Dolores").email("dolores@yandex.ru")
				.password("DOLORES@12_89").registrationDate(Instant.now()).build();
		Set<ConstraintViolation<User>> violations = validator.validate(user);

		violations.forEach(v -> log.error(v.getMessage()));

		assertFalse(violations.isEmpty());
	}
}
