package ru.yandex.practicum.catsgram.storage;

import ru.yandex.practicum.catsgram.model.User;

public interface Storage {
	void put(User user);
	User get(String email);
}
