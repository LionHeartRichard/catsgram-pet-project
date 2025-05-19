package ru.yandex.practicum.catsgram.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
// TODO import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.catsgram.dal.UserRepository;
import ru.yandex.practicum.catsgram.dto.NewUserRequest;
import ru.yandex.practicum.catsgram.dto.UpdateUserRequest;
import ru.yandex.practicum.catsgram.dto.mapper.UserMapper;
import ru.yandex.practicum.catsgram.exception.ConditionsNotMetException;
import ru.yandex.practicum.catsgram.exception.DuplicatedDataException;
import ru.yandex.practicum.catsgram.exception.NotFoundException;
import ru.yandex.practicum.catsgram.model.User;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	public User createUser(NewUserRequest request) {
		if (request.getEmail() == null || request.getEmail().isEmpty()) {
			throw new ConditionsNotMetException("Имейл должен быть указан");
		}

		Optional<User> alreadyExistUser = userRepository.findByEmail(request.getEmail());
		if (alreadyExistUser.isPresent()) {
			throw new DuplicatedDataException("Данный имейл уже используется");
		}

		User user = UserMapper.mapToUser(request);

		user = userRepository.save(user);

		return user;
	}

	public User getUserById(Long userId) {
		return userRepository.findById(userId)
				.orElseThrow(() -> new NotFoundException("Пользователь не найден с ID: " + userId));
	}

	public List<User> getUsers() {
		return userRepository.findAll().stream().collect(Collectors.toList());
	}

	public User updateUser(Long userId, UpdateUserRequest request) {
		User updatedUser = userRepository.findById(userId).map(user -> UserMapper.updateUserFields(user, request))
				.orElseThrow(() -> new NotFoundException("Пользователь не найден"));
		updatedUser = userRepository.update(updatedUser);
		return updatedUser;
	}
}
