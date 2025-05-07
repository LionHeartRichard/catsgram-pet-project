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
