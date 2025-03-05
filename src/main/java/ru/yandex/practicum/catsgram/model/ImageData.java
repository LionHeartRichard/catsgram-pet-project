package ru.yandex.practicum.catsgram.model;

import lombok.Data;

@Data
public class ImageData {
	private final String name;
	private final byte[] data;
}
