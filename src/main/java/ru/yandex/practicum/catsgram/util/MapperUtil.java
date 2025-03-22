package ru.yandex.practicum.catsgram.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.yandex.practicum.catsgram.dto.UserDto;
import ru.yandex.practicum.catsgram.model.User;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MapperUtil {
	public static UserDto mappToUserDto(User user) {
		UserDto dto = new UserDto();
		dto.setId(user.getId());
		dto.setLogin(user.getLogin());
		dto.setName(user.getName());
		dto.setEmail(user.getEmail());
		dto.setRegistrationDate(user.getRegistrationDate());
		return dto;
	}
}
