package ru.yandex.practicum.catsgram.model;

import java.time.Instant;

import org.springframework.validation.annotation.Validated;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.yandex.practicum.catsgram.validation.Login;
import ru.yandex.practicum.catsgram.validation.Password;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.PastOrPresent;

@Validated
@Data
@Builder(toBuilder = true)
@EqualsAndHashCode(of = "id")
public class User {
	private Long id;
	@Login
	private String login;
	private String name;
	@Email
	private String email;
	@Password
	private String password;
	@PastOrPresent
	private Instant registrationDate;
}
