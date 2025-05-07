package ru.yandex.practicum.catsgram.model;

import java.time.Instant;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@EqualsAndHashCode(of = {"email"})
public class User {
	private Long id;
	private String login;
	private String name;
	private String email;
	private String password;
	private Instant registrationDate;
}
