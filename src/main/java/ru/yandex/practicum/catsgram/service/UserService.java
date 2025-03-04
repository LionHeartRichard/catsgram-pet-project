package ru.yandex.practicum.catsgram.service;

import java.time.Instant;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ru.yandex.practicum.catsgram.exception.ConditionsNotMetException;
import ru.yandex.practicum.catsgram.exception.DuplicatedDataException;
import ru.yandex.practicum.catsgram.exception.NotFoundException;
import ru.yandex.practicum.catsgram.model.User;

@Service
public class UserService {

	private final Map<Long, User> users = new HashMap<>();

	public User create(final User user) {
		if (user == null || user.getEmail().isBlank())
			throw new ConditionsNotMetException("Имейл должен быть указан");
		final String email = user.getEmail();
		if (!users.isEmpty() && users.values().stream().anyMatch(u -> u.getEmail().equals(email)))
			throw new DuplicatedDataException("Этот имейл уже используется");
		user.setId(nextId());
		user.setRegistrationDate(Instant.now());
		users.put(user.getId(), user);
		return user;
	}

	public Collection<User> findAll() {
		return users.values();
	}

	public User update(final User newUser) {
		if (newUser == null || newUser.getId() == null)
			throw new ConditionsNotMetException("Id должен быть указан");
		if (users.containsKey(newUser.getId())) {
			User user = users.get(newUser.getId());
			if (!user.getEmail().equals(newUser.getEmail())) {
				String email = newUser.getEmail();
				if (!users.isEmpty() && users.values().stream().anyMatch(u -> u.getEmail().equals(email)))
					throw new DuplicatedDataException("Этот имейл уже используется");
			}
			if (newUser.getEmail() != null)
				user.setEmail(newUser.getEmail());
			if (newUser.getPassword() != null)
				user.setPassword(newUser.getPassword());
			if (newUser.getUsername() != null)
				user.setUsername(newUser.getUsername());
			users.put(user.getId(), user);
			return user;
		}
		throw new NotFoundException(String.format("Пользователь с id = %d не найден", newUser.getId()));
	}

	public User findUserById(final Long id) {
		final User user = users.get(id);
		if (user == null)
			throw new NotFoundException(String.format("Пользователь с id = %d не найден", id));
		return user;
	}

	public User findUserByEmail(final String email) {
		final Optional<User> optionalUser = users.entrySet().stream().filter(e -> e.getValue().getEmail().equals(email))
				.findFirst().map(e -> e.getValue());
		if (optionalUser.isEmpty())
			throw new NotFoundException(String.format("Пользователь с %s не найден", email));
		return optionalUser.get();
	}

	private Long nextId() {
		Long id = users.keySet().stream().mapToLong(k -> k).max().orElse(0);
		return ++id;
	}

}
