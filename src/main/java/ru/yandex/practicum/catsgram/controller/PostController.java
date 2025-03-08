package ru.yandex.practicum.catsgram.controller;

import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ru.yandex.practicum.catsgram.exception.ParameterNotValidException;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.service.PostService;
import ru.yandex.practicum.catsgram.util.Direction;

@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController {

	private final PostService postService;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Post create(@RequestBody final Post post) {
		return postService.create(post);
	}

	@GetMapping
	public Collection<Post> findAll(@RequestParam(defaultValue = Direction.AT_FIRST) String order,
			@RequestParam(defaultValue = "0") Integer from, @RequestParam(defaultValue = "10") Integer size) {
		if (!order.equals(Direction.AT_FIRST) && !order.equals(Direction.FROM_THE_END))
			throw new ParameterNotValidException("sort", "должен содержать корректное значение!");
		if (size <= 0)
			throw new ParameterNotValidException("size", "должен быть больше нуля!");
		if (from < 0)
			throw new ParameterNotValidException("from", "не может быть меньше нуля");
		return postService.findAll(order, from, size);
	}

	@PutMapping
	public Post update(@RequestBody final Post newPost) {
		return postService.update(newPost);
	}

	@GetMapping("/{id}")
	public Post findPostById(@PathVariable Long id) {
		return postService.findPostById(id);
	}

}
