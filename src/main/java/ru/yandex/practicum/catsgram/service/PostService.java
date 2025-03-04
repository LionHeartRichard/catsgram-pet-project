package ru.yandex.practicum.catsgram.service;

import java.time.Instant;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.yandex.practicum.catsgram.exception.ConditionsNotMetException;
import ru.yandex.practicum.catsgram.exception.NotFoundException;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.model.User;

@RequiredArgsConstructor
@Service
public class PostService {

	private final UserService userService;
	private final Map<Long, Post> posts = new HashMap<>();

	public Collection<Post> findAll() {
		return posts.values();
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
		throw new NotFoundException(String.format("Пост с id = %d не найден!", newPost.getId()));
	}

	public Post findPostById(final Long id) {
		Post post = posts.get(id);
		if (post == null)
			throw new NotFoundException(String.format("Пост id = %d не найден", id));
		return post;
	}

	private Long nextId() {
		Long id = posts.keySet().stream().mapToLong(k -> k).max().orElse(0);
		return ++id;
	}
}
