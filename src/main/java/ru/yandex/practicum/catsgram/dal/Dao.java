package ru.yandex.practicum.catsgram.dal;

import java.util.Collection;
import java.util.Optional;

public interface Dao<T> {

	Optional<Long> create(T t);

	Collection<T> read();

	Optional<Integer> update(T t);

	Optional<Integer> delete(T t);

	Optional<T> findById(Long id);
}
