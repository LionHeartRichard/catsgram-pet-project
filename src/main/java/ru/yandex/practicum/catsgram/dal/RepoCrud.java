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
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@RequiredArgsConstructor
public class RepoCrud<T> {

	private JdbcTemplate jdbc;

	public Optional<Long> create(String query, Object... params) {
		log.trace("Operation CREATE:");
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		jdbc.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			int[] idx = {1};
			for (Object p : params)
				ps.setObject(idx[0]++, p);
			return ps;
		}, keyHolder);
		Long id = keyHolder.getKeyAs(Long.class);
		log.trace("End create operation. Create object, id={}", id);
		return Optional.ofNullable(id);
	}

	public Optional<Integer> update(String query, Object... params) {
		log.trace("Operation UPDATE");
		Integer rowsUpdated = jdbc.update(query, params);
		rowsUpdated = rowsUpdated == 0 ? null : rowsUpdated;
		log.trace("End update operation");
		return Optional.ofNullable(rowsUpdated);
	}

	public Optional<Integer> delete(Long id, String nameTable) {
		log.trace("Operation DELETE");
		String query = String.format("DELETE FROM %s WHERE id=?", nameTable);
		Integer rowsDeleted = jdbc.update(query, id);
		rowsDeleted = rowsDeleted == 0 ? null : rowsDeleted;
		log.trace("End operation delete");
		return Optional.ofNullable(rowsDeleted);
	}
}
