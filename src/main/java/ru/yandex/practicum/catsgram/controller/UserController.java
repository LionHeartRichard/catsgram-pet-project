package ru.yandex.practicum.catsgram.controller;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ru.yandex.practicum.catsgram.model.User;
import ru.yandex.practicum.catsgram.service.UserService;

@RequiredArgsConstructor
@RestController
public class UserController {

	private final UserService userService;

	@PostMapping("/user")
	public User create(@RequestBody final User user) {
		return userService.create(user);
	}

	@GetMapping("/users")
	public Collection<User> findAll() {
		return userService.findAll();
	}

	@PutMapping("/user")
	public User update(@RequestBody final User newUser) {
		return userService.update(newUser);
	}

	@GetMapping("/user/{id}")
	public User findUserById(@PathVariable Long id) {
		return userService.findUserById(id);
	}

	@GetMapping("/user/email/{email}")
	public User findUserBuEmail(@PathVariable String email) {
		return userService.findUserByEmail(email);
	}
}
