package ru.yandex.practicum.catsgram.dal;

import java.util.Collection;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RepoFind<T> {

	private final JdbcTemplate jdbc;
	private final RowMapper<T> rowMapper;

	public Optional<T> byId(Long id, String nameTable) {
		String query = String.format("SELECT * FROM %s WHERE id=?", nameTable);
		T ans = jdbc.queryForObject(query, rowMapper, id);
		return Optional.ofNullable(ans);
	}

	public Optional<T> byUniqStringParam(String param, String nameTable, String nameParam) {
		String query = String.format("SELECT * FROM %s WHERE %s=?", nameTable, nameParam);
		T ans = jdbc.queryForObject(query, rowMapper, param);
		return Optional.ofNullable(ans);
	}

	public Collection<T> byStringParam(String param, String nameTable, String nameParam, Collection<T> collection) {
		String query = String.format("SELECT * FROM %s WHERE %s=?", nameTable, nameParam);
		collection = jdbc.query(query, rowMapper, param);
		return collection;
	}
}
