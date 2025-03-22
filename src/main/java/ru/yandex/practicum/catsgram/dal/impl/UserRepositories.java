package ru.yandex.practicum.catsgram.dal.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.catsgram.model.User;
import ru.yandex.practicum.catsgram.dal.RepoFind;
import ru.yandex.practicum.catsgram.dal.Repositories;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserRepositories implements Repositories<User> {

	private final RepoFind<User> repoFind;
	private final static String TABLE_NAME = "users";

	@Override
	public Optional<User> add(User t) {
		return Optional.empty();
	}

	@Override
	public void remove(User t) {
		// TODO Auto-generated method stub

	}

	@Override
	public Optional<User> update(User t) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<User> findById(Long id) {
		log.trace("begin work: fingById, table={}, userId={}", TABLE_NAME, id);
		return repoFind.byId(id, TABLE_NAME);
	}

	@Override
	public Collection<User> findAll() {
		log.trace("begin work: findAll, table={}", TABLE_NAME);
		return repoFind.all(TABLE_NAME, new ArrayList<User>());
	}

}
