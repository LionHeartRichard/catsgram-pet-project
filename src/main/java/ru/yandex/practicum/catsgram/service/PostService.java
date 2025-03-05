package ru.yandex.practicum.catsgram.service;

import java.time.Instant;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.yandex.practicum.catsgram.exception.ConditionsNotMetException;
import ru.yandex.practicum.catsgram.exception.NotFoundException;
import ru.yandex.practicum.catsgram.model.Post;

@RequiredArgsConstructor
@Service
public class PostService {

	private final UserService userService;
	private final Map<Long, Post> posts = new LinkedHashMap<>();

	public Collection<Post> findAll(final String order, final Integer from, final Integer size) {
		if (order.equals("desc"))
			return posts.entrySet().stream().sorted((a, b) -> Long.compare(b.getKey(), a.getKey())).skip(from)
					.limit(size).map(e -> e.getValue()).toList();
		return posts.entrySet().stream().skip(from).limit(size).map(e -> e.getValue()).toList();
	}

	public Post create(final Post post) {

		if (post.getDescription() == null || post.getDescription().isBlank()) {
			throw new ConditionsNotMetException("Описание не может быть пустым");
		}
		userService.findUserById(post.getAuthorId());
		post.setId(nextId());
		post.setPostDate(Instant.now());
		posts.put(post.getId(), post);
		return post;
	}

	public Post update(final Post newPost) {
		if (newPost.getId() == null) {
			throw new ConditionsNotMetException("Id должен быть указан");
		}
		if (newPost.getDescription() == null || newPost.getDescription().isBlank()) {
			throw new ConditionsNotMetException("Описание не может быть пустым");
		}
		if (posts.containsKey(newPost.getId())) {
			Post post = posts.get(newPost.getId());
			post.setDescription(newPost.getDescription());
			posts.put(post.getId(), post);
			return post;
		}
		throw new NotFoundException(String.format("Пост с id: %d не найден!", newPost.getId()));
	}

	public Post findPostById(final Long id) {
		Post post = posts.get(id);
		if (post == null)
			throw new NotFoundException(String.format("Пост id: %d не найден", id));
		return post;
	}

	private Long nextId() {
		Long id = posts.keySet().stream().mapToLong(k -> k).max().orElse(0);
		return ++id;
	}
}
