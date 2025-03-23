package ru.yandex.practicum.catsgram.dal;

import java.util.Collection;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@RequiredArgsConstructor
public class RepoFind<T> {

	private final JdbcTemplate jdbc;
	private final RowMapper<T> rowMapper;

	public Collection<T> findAll(String nameTable, Collection<T> collection) {
		log.trace("Operation FIND ALL");
		String query = String.format("SELECT * FROM %s", nameTable);
		collection = jdbc.query(query, rowMapper);
		log.trace("End findAll operation");
		return collection;
	}

	public Optional<T> byId(Long id, String nameTable) {
		log.trace("Operation FIND BY ID");
		String query = String.format("SELECT * FROM %s WHERE id=?", nameTable);
		T ans = jdbc.queryForObject(query, rowMapper, id);
		log.trace("End operation find by id");
		return Optional.ofNullable(ans);
	}

	public Optional<T> byUniqStringParam(String param, String nameTable, String nameParam) {
		log.trace("Operation FIND BY UNIQ PARAM");
		String query = String.format("SELECT * FROM %s WHERE %s=?", nameTable, nameParam);
		T ans = jdbc.queryForObject(query, rowMapper, param);
		log.trace("End operation find by uniq param");
		return Optional.ofNullable(ans);
	}

	public Collection<T> byStringParam(String param, String nameTable, String nameParam, Collection<T> collection) {
		log.trace("Operation FIND BY STRING PARAM");
		String query = String.format("SELECT * FROM %s WHERE %s=?", nameTable, nameParam);
		collection = jdbc.query(query, rowMapper, param);
		log.trace("End operation find by string param");
		return collection;
	}
}
