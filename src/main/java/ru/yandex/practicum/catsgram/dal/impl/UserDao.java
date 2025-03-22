package ru.yandex.practicum.catsgram.dal.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.catsgram.model.User;
import ru.yandex.practicum.catsgram.dal.RepoCrud;
import ru.yandex.practicum.catsgram.dal.RepoFind;
import ru.yandex.practicum.catsgram.dal.Dao;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserDao implements Dao<User> {

	private final RepoFind<User> repoFind;
	private final RepoCrud<User> repoCrud;

	private final static String TABLE_NAME = "users";
	private static final String INSERT_QUERY = "INSERT INTO users(name, email, password, registration_date, login)"
			+ "VALUES (?, ?, ?, ?) returning id";
	private static final String UPDATE_QUERY = "UPDATE users SET name = ?, email = ?, password = ?, login = ? WHERE id = ?";

	@Override
	public Optional<Long> create(User user) {
		log.trace("Begin work: create, user={}", user.toString());
		return repoCrud.create(INSERT_QUERY, user.getName(), user.getEmail(), user.getPassword(),
				user.getRegistrationDate(), user.getLogin());

	}

	@Override
	public Collection<User> read() {
		log.trace("Begin work: read, table={}", TABLE_NAME);
		return repoCrud.read(TABLE_NAME, new ArrayList<User>());
	}

	@Override
	public Optional<Integer> delete(User user) {
		log.trace("Begin work: delete, user={}", user.toString());
		return repoCrud.delete(user.getId(), TABLE_NAME);
	}

	@Override
	public Optional<Integer> update(User user) {
		log.trace("Begin work: upade, user={}", user);
		return repoCrud.update(UPDATE_QUERY, user.getName(), user.getEmail(), user.getPassword(), user.getLogin(),
				user.getId());
	}

	@Override
	public Optional<User> findById(Long id) {
		log.trace("Begin work: fingById, table={}, userId={}", TABLE_NAME, id);
		return repoFind.byId(id, TABLE_NAME);
	}

	public Optional<User> findByEmail(String email) {
		log.trace("Begin work: findByEmail, email={}", email);
		return repoFind.byUniqStringParam(email, TABLE_NAME, "email");
	}

	public Optional<User> findByLogin(String login) {
		log.trace("Begin work: findByLofin={}", login);
		return repoFind.byUniqStringParam(login, TABLE_NAME, "login");
	}

}
