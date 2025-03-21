package ru.yandex.practicum.catsgram.dal;

import java.util.Collection;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

import ru.yandex.practicum.catsgram.model.User;
import ru.yandex.practicum.catsgram.dal.mapper.UserRowMapper;

@Repository
@RequiredArgsConstructor
public class UserRepository {
	private final JdbcTemplate jdbc;
	private final UserRowMapper mapperUser;

	public Collection<User> findAll() {
		String query = "SELECT * FROM users";
		return jdbc.query(query, mapperUser);
	}
}
