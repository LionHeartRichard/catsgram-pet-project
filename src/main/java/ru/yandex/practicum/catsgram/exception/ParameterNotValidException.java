package ru.yandex.practicum.catsgram.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ParameterNotValidException extends IllegalArgumentException {

	private final String parameter;
	private final String reason;
}
