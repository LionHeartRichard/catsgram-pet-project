package ru.yandex.practicum.catsgram.validation;

import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public class LocalValidator {

	public Optional<Long> validIndex(Long index) {
		index = index >= 0 ? index : null;
		return Optional.ofNullable(index);
	}

	public Optional<Integer> validIndex(Integer index) {
		index = index >= 0 ? index : null;
		return Optional.ofNullable(index);
	}
}
