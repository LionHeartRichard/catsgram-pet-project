package ru.yandex.practicum.catsgram.dal.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ru.yandex.practicum.catsgram.model.Image;

public class ImageRowMapper implements RowMapper<Image>{

	@Override
	public Image mapRow(ResultSet rs, int rowNum) throws SQLException {
		Image image = Image.
				builder().
				id(rs.getLong("id")).
				build();
		return image;
	}

}
