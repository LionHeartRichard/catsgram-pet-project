package ru.yandex.practicum.catsgram.dto;

import java.time.Instant;

import lombok.Data;

@Data
public class UserDto {
	private Long id;
	private String login;
	private String name;
	private String email;
	private String password;
	private Instant registrationDate;
}
