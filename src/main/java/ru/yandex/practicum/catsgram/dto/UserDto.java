package ru.yandex.practicum.catsgram.dto;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UserDto {
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Long id;
	private String login;
	private String name;
	private String email;
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Instant registrationDate;
}
