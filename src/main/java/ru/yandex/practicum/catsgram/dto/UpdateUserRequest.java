package ru.yandex.practicum.catsgram.dto;

import lombok.Data;

@Data
public class UpdateUserRequest {
	private String name;
	private String email;
	private String password;
	private String login;

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
