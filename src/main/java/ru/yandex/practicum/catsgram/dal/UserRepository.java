package ru.yandex.practicum.catsgram.dal;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import ru.yandex.practicum.catsgram.model.User;

@Repository
public class UserRepository extends BaseRepository<User> {

	private static final String FIND_ALL_QUERY = "SELECT * FROM users";
	private static final String FIND_BY_EMAIL_QUERY = "SELECT * FROM users WHERE email = ?";
	private static final String FIND_BY_ID_QUERY = "SELECT * FROM users WHERE id = ?";
	private static final String INSERT_QUERY = "INSERT INTO users(username, email, password, registration_date, login)"
			+ "VALUES (?, ?, ?, ?, ?) RETURNING id";
	private static final String UPDATE_QUERY = "UPDATE users SET username = ?, email = ?, password = ? WHERE id = ?";

	public UserRepository(JdbcTemplate jdbc, RowMapper<User> mapper) {
		super(jdbc, mapper);
	}

	public List<User> findAll() {
		return findMany(FIND_ALL_QUERY);
	}

	public Optional<User> findByEmail(String email) {
		return findOne(FIND_BY_EMAIL_QUERY, email);
	}

	public Optional<User> findById(Long id) {
		return findOne(FIND_BY_ID_QUERY, id);
	}

	public User save(User user) {
		Long id = insert(INSERT_QUERY, user.getName(), user.getEmail(), user.getPassword(),
				Timestamp.from(user.getRegistrationDate()), user.getLogin());
		user.setId(id);
		return user;
	}

	public User update(User user) {
		update(UPDATE_QUERY, user.getName(), user.getEmail(), user.getPassword(), user.getId());
		return user;
	}

}
