package ru.yandex.practicum.catsgram.dto.mapper;

import java.time.Instant;

import ru.yandex.practicum.catsgram.dto.NewUserRequest;
import ru.yandex.practicum.catsgram.dto.UpdateUserRequest;
import ru.yandex.practicum.catsgram.model.User;

public class UserMapper {

	public static User mapToUser(NewUserRequest request) {
		User user = new User();
		user.setLogin(request.getLogin());
		user.setName(request.getName());
		user.setPassword(request.getPassword());
		user.setEmail(request.getEmail());
		user.setRegistrationDate(Instant.now());

		return user;
	}

	public static User updateUserFields(User user, UpdateUserRequest request) {
		if (request.hasEmail()) {
			user.setEmail(request.getEmail());
		}
		if (request.hasPassword()) {
			user.setPassword(request.getPassword());
		}
		if (request.hasUsername()) {
			user.setName(request.getName());
		}
		if (request.hasLogin()) {
			user.setLogin(request.getLogin());
		}
		return user;
	}
}
