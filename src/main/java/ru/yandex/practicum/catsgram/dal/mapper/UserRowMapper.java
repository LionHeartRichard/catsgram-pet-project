package ru.yandex.practicum.catsgram.dal.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import ru.yandex.practicum.catsgram.model.User;

@Component
public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		Timestamp registrationDate = rs.getTimestamp("registration_date");
		User user = new User(rs.getLong("id"),
				rs.getString("login"),
				rs.getString("name"),
				rs.getString("email"),
				rs.getString("password"),
				registrationDate.toInstant());
		return user;
	}

}
