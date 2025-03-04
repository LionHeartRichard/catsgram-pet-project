package ru.yandex.practicum.catsgram.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.service.PostService;

@RequiredArgsConstructor
@RestController
public class PostController {

	private final PostService postService;

	@PostMapping("/post")
	public Post create(@RequestBody final Post post) {
		return postService.create(post);
	}

	@GetMapping("/posts")
	public Collection<Post> findAll() {
		return postService.findAll();
	}

	@PutMapping("/post")
	public Post update(@RequestBody final Post newPost) {
		return postService.update(newPost);
	}

	@GetMapping("/post/{id}")
	public Post findPostById(@PathVariable Long id) {
		return postService.findPostById(id);
	}

}
