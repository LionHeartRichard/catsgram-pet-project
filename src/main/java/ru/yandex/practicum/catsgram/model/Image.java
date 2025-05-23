package ru.yandex.practicum.catsgram.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder(toBuilder = true)
@EqualsAndHashCode(of = "id")
public class Image {
	private Long id;
	private Long postId;
	private String originalFileName;
	private String filePath;
}
