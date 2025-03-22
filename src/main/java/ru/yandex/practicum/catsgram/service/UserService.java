package ru.yandex.practicum.catsgram.service;

import java.time.Instant;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.yandex.practicum.catsgram.dal.impl.UserRepositories;
import ru.yandex.practicum.catsgram.dto.UserDto;
import ru.yandex.practicum.catsgram.exception.ConditionsNotMetException;
import ru.yandex.practicum.catsgram.exception.DuplicatedDataException;
import ru.yandex.practicum.catsgram.exception.NotFoundException;
import ru.yandex.practicum.catsgram.model.User;
import ru.yandex.practicum.catsgram.util.MapperUtil;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepositories userRepository;

	public Collection<UserDto> findAll() {
		return userRepository.findAll().stream().map(MapperUtil::mappToUserDto).toList();
	}

	public Optional<User> findById(final Long id) {
		return userRepository.findById(id);
	}

}
