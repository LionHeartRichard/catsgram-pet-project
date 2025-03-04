package ru.yandex.practicum.catsgram.service;

import java.time.Instant;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.yandex.practicum.catsgram.exception.ConditionsNotMetException;
import ru.yandex.practicum.catsgram.exception.NotFoundException;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.model.User;

@Service
public class PostService {

	private final UserService userService;
	private final Map<Long, Post> posts = new HashMap<>();

	@Autowired
	public PostService(UserService userService) {
		this.userService = userService;
	}

	public Collection<Post> findAll() {
		return posts.values();
	}

	public Post create(Post post) {

		if (post.getDescription() == null || post.getDescription().isBlank()) {
			throw new ConditionsNotMetException("Описание не может быть пустым");
		}
		Optional<User> user = userService.findUserById(post.getAuthorId());
		if (user.isEmpty())
			throw new ConditionsNotMetException(String.format("Автор с id = %d не найден", post.getAuthorId()));
		post.setId(nextId());
		post.setPostDate(Instant.now());
		posts.put(post.getId(), post);
		return post;
	}

	public Post update(Post newPost) {
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

	private Long nextId() {
		Long id = posts.keySet().stream().mapToLong(k -> k).max().orElse(0);
		return ++id;
	}
}
