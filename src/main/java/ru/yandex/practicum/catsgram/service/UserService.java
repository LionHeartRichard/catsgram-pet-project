package ru.yandex.practicum.catsgram.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.catsgram.dal.impl.UserDao;
import ru.yandex.practicum.catsgram.dto.impl.UserDto;
import ru.yandex.practicum.catsgram.dto.mapper.UserDtoMapper;
import ru.yandex.practicum.catsgram.exception.ConditionsNotMetException;
import ru.yandex.practicum.catsgram.exception.InternalServerException;
import ru.yandex.practicum.catsgram.exception.NotFoundException;
import ru.yandex.practicum.catsgram.exception.ParameterNotValidException;
import ru.yandex.practicum.catsgram.model.User;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

	private final UserDao userDao;

	private void checkConditionLogin(final String login) {
		Optional<User> userOpt = userDao.findByLogin(login);
		if (userOpt.isPresent()) {
			log.warn("Login already used, user={} ", userOpt.get().toString());
			throw new ConditionsNotMetException(String.format("Login: %s already used!!!", login));
		}
	}

	private void checkConditionEmail(final String email) {
		Optional<User> userOpt = userDao.findByEmail(email);
		if (userOpt.isPresent()) {
			log.warn("User email already used, user={} ", userOpt.get().toString());
			throw new ConditionsNotMetException(String.format("Email: %s already used!!!", email));
		}
	}

	public UserDto createUser(User user) {
		checkConditionLogin(user.getLogin());
		checkConditionEmail(user.getEmail());
		if (user.getName() == null)
			user.setName(user.getLogin());
		log.trace("Beginning operation create user: {}", user.toString());
		Long id = userDao.create(user).orElseThrow(() -> new InternalServerException("Failed keep user!!!"));
		user.setId(id);
		log.trace("User={} keep to database", user.toString());
		return UserDtoMapper.mappToUserDto(user);
	}

	public Collection<UserDto> findAll() {
		log.trace("Begining operation findAll");
		return userDao.read().stream().map(UserDtoMapper::mappToUserDto).toList();
	}

	public UserDto updateUser(Long id, Optional<UserDto> dtoOpt) {
		final UserDto dto = dtoOpt.orElseThrow(() -> new ParameterNotValidException("user", "null"));
		log.trace("Beginning operation update user, id={} userDto: {}", id, dto.toString());
		User user = userDao.findUserById(id).orElseThrow(() -> new NotFoundException("User not found!!!"));
		log.trace("Old user: {}", user.toString());
		user = helperUpdate(user, dto);
		userDao.update(user);
		log.trace("User - UPDATE: ", user.toString());
		return UserDtoMapper.mappToUserDto(user);
	}

	private User helperUpdate(User user, final UserDto dto) {
		if (dto.getLogin() != null) {
			final String login = dto.getLogin();
			checkConditionLogin(login);
			user.setLogin(login);
		}
		if (dto.getEmail() != null) {
			final String email = dto.getEmail();
			checkConditionEmail(email);
			user.setEmail(email);
		}
		if (dto.getName() != null)
			user.setName(dto.getName());
		return user;
	}

	public UserDto findUserById(final Long id) {
		log.trace("Beginnig - findUserById, id=: {}", id);
		final User user = userDao.findUserById(id).orElseThrow(() -> new NotFoundException("User not found!!!"));
		return UserDtoMapper.mappToUserDto(user);
	}
}
