package ru.yandex.practicum.catsgram.dto.impl;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import ru.yandex.practicum.catsgram.dto.Dto;

@Data
public class UserDto implements Dto {
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Long id;
	private String login;
	private String name;
	private String email;
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Instant registrationDate;
}
