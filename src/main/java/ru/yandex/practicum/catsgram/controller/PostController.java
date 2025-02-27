package ru.yandex.practicum.catsgram.controller;

import java.time.Instant;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.yandex.practicum.catsgram.exception.ConditionsNotMetException;
import ru.yandex.practicum.catsgram.exception.NotFoundException;
import ru.yandex.practicum.catsgram.model.Post;

@RestController
@RequestMapping("/posts")
public class PostController {

	private final Map<Long, Post> posts = new HashMap<>();

	@GetMapping
	public Collection<Post> findAll() {
		return posts.values();
	}

	@PostMapping
	public Post create(@RequestBody Post post) {
		if (post == null || post.getDescription().isBlank()) {
			throw new ConditionsNotMetException("Описание не может быть пустым");
		}

		post.setId(getNextId());
		post.setPostDate(Instant.now());
		posts.put(post.getId(), post);
		return post;
	}

	@PutMapping
	public Post update(@RequestBody Post newPost) {
		if (newPost.getId() == null) {
			throw new ConditionsNotMetException("Id должен быть указан");
		}
		if (newPost == null || newPost.getDescription().isBlank()) {
			throw new ConditionsNotMetException("Описание не может быть пустым");
		}
		if (posts.containsKey(newPost.getId())) {
			Post post = posts.get(newPost.getId());
			post.setDescription(newPost.getDescription());
			return post;
		}
		throw new NotFoundException("Пост с id = " + newPost.getId() + " не найден");
	}

	// метод будет отрабатывать долго - лучше хранить в памяти последний
	// сгенерированный ид?!
	private Long getNextId() {
		Long currentMaxId = posts.keySet().stream().mapToLong(id -> id).max().orElse(0);
		return ++currentMaxId;
	}
}
