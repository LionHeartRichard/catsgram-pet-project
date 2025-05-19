package ru.yandex.practicum.catsgram.validation;

import org.springframework.stereotype.Component;

import ru.yandex.practicum.catsgram.exception.ConditionsNotMetException;

@Component
public class LocalValidator {

	public void validIndex(long idx) {
		if (idx < 0)
			throw new ConditionsNotMetException(String.format("Index value need is positive! Index:%d", idx));
	}

	public void validIndex(int idx) {
		if (idx < 0)
			throw new ConditionsNotMetException(String.format("Index value need is positive! Index:%d", idx));
	}

	public void validIndex(long idx, String message) {
		if (idx < 0)
			throw new ConditionsNotMetException(message);
	}

	public void validIndex(int idx, String message) {
		if (idx < 0)
			throw new ConditionsNotMetException(message);
	}

}
