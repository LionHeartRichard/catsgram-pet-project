package ru.yandex.practicum.catsgram.storage.impl;

import java.util.HashMap;
import java.util.Map;

import ru.yandex.practicum.catsgram.model.User;
import ru.yandex.practicum.catsgram.storage.Storage;

// Реализация интерфейса, которая сохраняет данные в хеш-таблице в памяти
public class MemoryStorage implements Storage {
	private Map<String, User> users = new HashMap<>();

	/*
	 * Добавление нового пользователя. Должна быть передана электронная почта
	 * пользователя, которая не должна повторяться
	 */
	@Override
	public void put(final User user) {
		if (user.getDetails() == null || user.getDetails().getEmail() == null) {
			throw new IllegalStateException("Email should be passed");
		}
		final String email = user.getDetails().getEmail().toLowerCase();
		if (users.containsKey(email)) {
			throw new IllegalStateException("User already exists");
		}
		users.put(email, user);
	}

	// Получение информации о пользователе
	@Override
	public User get(final String email) {
		if (email == null) {
			return null;
		}
		return users.get(email.toLowerCase());
	}
}
