package ru.yandex.practicum.catsgram.dto;

import lombok.Data;

@Data
public class UpdateUserRequest {
	private final String name;
	private final String email;
	private final String password;
	private final String login;

	public boolean hasUsername() {
		return !(name == null || name.isBlank());
	}

	public boolean hasEmail() {
		return !(email == null || email.isBlank());
	}

	public boolean hasPassword() {
		return !(password == null || password.isBlank());
	}

	public boolean hasLogin() {
		return !(login == null || login.isBlank());
	}
}
