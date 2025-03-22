package ru.yandex.practicum.catsgram.dal;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Collection;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RepoCrud<T> {

	private JdbcTemplate jdbc;
	private RowMapper<T> rowMapper;

	public Optional<Long> create(String query, Object... params) {
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		jdbc.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			int[] idx = {1};
			for (Object p : params)
				ps.setObject(idx[0]++, p);
			return ps;
		}, keyHolder);

		Long id = keyHolder.getKeyAs(Long.class);
		return Optional.ofNullable(id);
	}

	public Collection<T> read(String nameTable, Collection<T> collection) {
		String query = String.format("SELECT * FROM %s", nameTable);
		collection = jdbc.query(query, rowMapper);
		return collection;
	}

	public Optional<Integer> update(String query, Object... params) {
		Integer rowsUpdated = jdbc.update(query, params);
		rowsUpdated = rowsUpdated == 0 ? null : rowsUpdated;
		return Optional.ofNullable(rowsUpdated);
	}

	public Optional<Integer> delete(Long id, String nameTable) {
		String query = String.format("DELETE FROM %s WHERE id=?", nameTable);
		Integer rowsDeleted = jdbc.update(query, id);
		rowsDeleted = rowsDeleted == 0 ? null : rowsDeleted;
		return Optional.ofNullable(rowsDeleted);
	}
}
