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
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.service.PostService;

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
	public Collection<Post> findAll(@RequestParam(defaultValue = "abc") String order,
			@RequestParam(defaultValue = "0") Integer from, @RequestParam(defaultValue = "10") Integer size) {
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
