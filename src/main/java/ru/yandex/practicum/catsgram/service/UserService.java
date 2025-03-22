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
import ru.yandex.practicum.catsgram.model.User;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

	private final UserDao userDao;

	private void checkConditionData(User user) {
		Optional<User> checkLogin = userDao.findByLogin(user.getLogin());
		if (checkLogin.isPresent()) {
			log.warn("User login already used: {} ", user.toString());
			throw new ConditionsNotMetException(String.format("User login: %s already used!!!", user.getLogin()));
		}
		Optional<User> checkEmail = userDao.findByEmail(user.getEmail());
		if (checkEmail.isPresent()) {
			log.warn("User email already used: {} ", user.toString());
			throw new ConditionsNotMetException(String.format("User email: %s already used!!!", user.getEmail()));
		}
	}

	public UserDto createUser(User user) {
		checkConditionData(user);
		if (user.getName() == null)
			user.setName(user.getLogin());
		log.trace("Beginning operation create user={}", user.toString());
		Long id = userDao.create(user).orElseThrow(() -> new InternalServerException("Failed keep user!!!"));
		user.setId(id);
		log.trace("User={} keep to database", user.toString());
		return UserDtoMapper.mappToUserDto(user);
	}

	public Collection<UserDto> findAll() {
		log.trace("Begining operation findAll");
		return userDao.read().stream().map(UserDtoMapper::mappToUserDto).toList();
	}

	public UserDto updateUser(User user) {
		// !!!!!!!!!!
		userDao.findById(user.getId()).orElseThrow(() -> new NotFoundException("User not found!!!"));
		checkConditionData(user);
		log.trace("Beginning operation update user:{}", user.toString());
		userDao.update(user);
		return UserDtoMapper.mappToUserDto(user);
	}

	public Optional<User> findById(final Long id) {
		return userRepository.findById(id);
	}

//	public UserDto createUser(NewUserRequest request) {
//        if (request.getEmail() == null || request.getEmail().isEmpty()) {
//            throw new ConditionsNotMetException("Имейл должен быть указан");
//        }
//
//        Optional<User> alreadyExistUser = userRepository.findByEmail(request.getEmail());
//        if (alreadyExistUser.isPresent()) {
//            throw new DuplicatedDataException("Данный имейл уже используется");
//        }
//
//        User user = UserMapper.mapToUser(request);
//
//        user = userRepository.save(user);
//
//        return UserMapper.mapToUserDto(user);
//    }
//
//    public UserDto getUserById(long userId) {
//        return userRepository.findById(userId)
//                .map(UserMapper::mapToUserDto)
//                .orElseThrow(() -> new NotFoundException("Пользователь не найден с ID: " + userId));
//    }
//
//    public List<UserDto> getUsers() {
//        return userRepository.findAll()
//                .stream()
//                .map(UserMapper::mapToUserDto)
//                .collect(Collectors.toList());
//    }
//
//    public UserDto updateUser(long userId, UpdateUserRequest request) {
//        User updatedUser = userRepository.findById(userId)
//                .map(user -> UserMapper.updateUserFields(user, request))
//                .orElseThrow(() -> new NotFoundException("Пользователь не найден"));
//        updatedUser = userRepository.update(updatedUser);
//        return UserMapper.mapToUserDto(updatedUser);
//    }

}
