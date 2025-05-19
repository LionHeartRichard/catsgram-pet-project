package ru.yandex.practicum.catsgram.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import ru.yandex.practicum.catsgram.exception.ConditionsNotMetException;
import ru.yandex.practicum.catsgram.exception.DuplicatedDataException;
import ru.yandex.practicum.catsgram.exception.NotFoundException;
import ru.yandex.practicum.catsgram.exception.ParameterNotValidException;
import ru.yandex.practicum.catsgram.helper.ErrorResponse;

@RestControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponse handleNotFound(RuntimeException e) {
		return new ErrorResponse(e.getMessage());
	}

	@ExceptionHandler(DuplicatedDataException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public ErrorResponse handleDuplicatedData(RuntimeException e) {
		return new ErrorResponse(e.getMessage());
	}

	@ExceptionHandler(ConditionsNotMetException.class)
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	public ErrorResponse handleConditionsNotMet(RuntimeException e) {
		return new ErrorResponse(e.getMessage());
	}

	@ExceptionHandler(ParameterNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse handleParameterNotValid(RuntimeException e) {
		return new ErrorResponse(e.getMessage());
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponse handleException(RuntimeException e) {
		return new ErrorResponse(String.format("Произошла непредвиденная ошибка!!! %s", e.getMessage()));
	}
}
