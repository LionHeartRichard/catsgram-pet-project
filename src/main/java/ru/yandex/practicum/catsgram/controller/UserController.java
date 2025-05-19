package ru.yandex.practicum.catsgram.controller;

import java.util.List;

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
import ru.yandex.practicum.catsgram.dto.NewUserRequest;
import ru.yandex.practicum.catsgram.dto.UpdateUserRequest;
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

	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public List<User> findAll() {
		log.trace("query client: GET /users, FIND ALL");
		return userService.getUsers();
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public User createUser(@Valid @RequestBody NewUserRequest newUserRequest) {
		log.trace("query clients: POST /users, CREATE USER");
		return userService.createUser(newUserRequest);
	}

	@ResponseStatus(HttpStatus.OK)
	@PutMapping("/{user_id}")
	public User updateUser(@PathVariable("user_id") Long userId, @RequestBody UpdateUserRequest updateUserRequest) {
		log.trace("query clients: PUT/user_id, UPDATE USER");
		validator.validIndex(userId);
		return userService.updateUser(userId, updateUserRequest);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{user_id}")
	public User findUserById(@PathVariable("user_id") Long userId) {
		log.trace("query clients: GET/user_id, FIND USER BY ID");
		validator.validIndex(userId);
		return userService.getUserById(userId);
	}

}
