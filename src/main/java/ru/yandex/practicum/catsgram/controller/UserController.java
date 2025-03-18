package ru.yandex.practicum.catsgram.controller;

import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.catsgram.exception.ParameterNotValidException;
import ru.yandex.practicum.catsgram.model.User;
import ru.yandex.practicum.catsgram.service.UserService;
import ru.yandex.practicum.catsgram.validation.LocalValidator;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

	private final UserService userService;
	private final LocalValidator validator;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public User create(@Valid @RequestBody final User user) {
		log.trace("query client: POST /users, user ={}", user.toString());
		return userService.create(user);
	}

	@GetMapping
	public Collection<User> findAll() {
		log.trace("query client: GET /users, FIND ALL");
		return userService.findAll();
	}

	@PutMapping
	public User update(@Valid @RequestBody final User user) {
		log.trace("query client: PUT /users, user = ", user.toString());
		return userService.update(user);
	}

	@GetMapping("/{id}")
	public User findUserById(@PathVariable final Long id) {
		log.trace("query client: GET /users/id, id = {}", id);
		validator.validIndex(id).orElseThrow(() -> new ParameterNotValidException("id", "not positive value"));
		return userService.findUserById(id);
	}

	@GetMapping("/{email}")
	public User findUserByEmail(@PathVariable final String email) {
		log.trace("query client: GET /users/email, email = {}", email);
		return userService.findUserByEmail(email);
	}
}
