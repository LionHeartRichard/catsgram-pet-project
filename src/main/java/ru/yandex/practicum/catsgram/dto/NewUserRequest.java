package ru.yandex.practicum.catsgram.dto;

import java.time.Instant;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import ru.yandex.practicum.catsgram.validation.Login;
import ru.yandex.practicum.catsgram.validation.Password;

@Validated
@Data
public class NewUserRequest {
	@Login
	private final String login;
	private final String name;
	@Email
	private final String email;
	@Password
	private final String password;
	@PastOrPresent
	private final Instant registrationDate;
}
