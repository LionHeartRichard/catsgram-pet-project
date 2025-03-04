package ru.yandex.practicum.catsgram.model;

import java.time.Instant;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder(toBuilder = true)
@EqualsAndHashCode(of = "id")
public class User {
	private Long id;
	private String username;
	private String email;
	private String password;
	private Instant registrationDate;
}
