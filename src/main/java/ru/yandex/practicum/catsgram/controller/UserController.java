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

import lombok.RequiredArgsConstructor;
import ru.yandex.practicum.catsgram.model.User;
import ru.yandex.practicum.catsgram.service.UserService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

	private final UserService userService;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public User create(@RequestBody final User user) {
		return userService.create(user);
	}

	@GetMapping
	public Collection<User> findAll() {
		return userService.findAll();
	}

	@PutMapping
	public User update(@RequestBody final User newUser) {
		return userService.update(newUser);
	}

	@GetMapping("/{id}")
	public User findUserById(@PathVariable final Long id) {
		return userService.findUserById(id);
	}

	@GetMapping("/{email}")
	public User findUserByEmail(@PathVariable final String email) {
		return userService.findUserByEmail(email);
	}
}
