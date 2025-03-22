package ru.yandex.practicum.catsgram.dal;

import java.util.Collection;
import java.util.Optional;

public interface Repositories<T> {

	Optional<T> add(T t);

	void remove(T t);

	Optional<T> update(T t);

	Optional<T> findById(Long id);

	Collection<T> findAll();
}
