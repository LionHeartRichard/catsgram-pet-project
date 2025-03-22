package ru.yandex.practicum.catsgram.dto.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.yandex.practicum.catsgram.dto.impl.UserDto;
import ru.yandex.practicum.catsgram.model.User;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDtoMapper {
	public static UserDto mappToUserDto(User user) {
		UserDto dto = new UserDto();
		dto.setId(user.getId());
		dto.setLogin(user.getLogin());
		dto.setName(user.getName());
		dto.setEmail(user.getEmail());
		dto.setRegistrationDate(user.getRegistrationDate());
		return dto;
	}

	public static UserDto mappToUserDtoNoIdDate(User user) {
		UserDto dto = new UserDto();
		dto.setLogin(user.getLogin());
		dto.setName(user.getName());
		dto.setEmail(user.getEmail());
		return dto;
	}

	public static UserDto mappToUserDtoPublicInfo(User user) {
		UserDto dto = new UserDto();
		dto.setLogin(user.getLogin());
		dto.setName(user.getName());
		dto.setEmail(user.getEmail());
		return dto;
	}
}
