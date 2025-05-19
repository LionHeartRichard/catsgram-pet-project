package ru.yandex.practicum.catsgram.controller;

import java.util.Collection;

import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import ru.yandex.practicum.catsgram.model.Image;
import ru.yandex.practicum.catsgram.model.ImageData;
import ru.yandex.practicum.catsgram.service.ImageService;

@RestController
@RequiredArgsConstructor
public class ImageController {
	private final ImageService imageService;

	@GetMapping("/posts/{postId}/images")
	public Collection<Image> getImagesInfoByPostId(@PathVariable final Long postId) {
		return imageService.getImagesInfoByPostId(postId);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/posts/{postId}/images")
	public Collection<Image> saveImages(@PathVariable final Long postId,
			@RequestParam("image") final Collection<MultipartFile> multiparts) {
		return imageService.saveImages(postId, multiparts);
	}

	@GetMapping(value = "/images/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> downloadImage(@PathVariable final Long id) {
		ImageData imageData = imageService.downloadImage(id);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentDisposition(ContentDisposition.attachment().filename(imageData.getName()).build());

		return new ResponseEntity<>(imageData.getData(), headers, HttpStatus.OK);
	}
}
