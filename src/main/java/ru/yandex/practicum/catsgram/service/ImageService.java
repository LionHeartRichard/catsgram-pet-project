package ru.yandex.practicum.catsgram.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import ru.yandex.practicum.catsgram.exception.NotFoundException;
import ru.yandex.practicum.catsgram.model.Image;
import ru.yandex.practicum.catsgram.model.ImageData;
import ru.yandex.practicum.catsgram.model.Post;

@Service
@RequiredArgsConstructor
public class ImageService {

	private static final String KEEP_DIR = "/home/kerrigan_kein/files_storage";
	private final Map<Long, Image> images = new HashMap<>();
	private final PostService postService;

	public Collection<Image> getImagesInfoByPostId(final Long postId) {
		return images.values().stream().filter(i -> i.getPostId().equals(postId)).toList();
	}

	public Collection<Image> saveImages(Long postId, Collection<MultipartFile> multiparts) {
		return multiparts.stream().map(f -> saveImage(postId, f)).toList();
	}

	private Image saveImage(Long postId, MultipartFile multipartFile) {
		Post post = postService.findPostById(postId);
		Path path = saveFile(multipartFile, post);
		Image image = Image.builder().id(nextId()).filePath(path.toString()).postId(postId)
				.originalFileName(multipartFile.getOriginalFilename()).build();
		images.put(image.getId(), image);
		return image;
	}

	@SneakyThrows
	private Path saveFile(MultipartFile multipartFile, Post post) {
		final String nameUniq = String.format("%d.%s", Instant.now().toEpochMilli(),
				StringUtils.getFilenameExtension(multipartFile.getOriginalFilename()));

		Path upload = Paths.get(KEEP_DIR, String.valueOf(post.getAuthorId()), post.getId().toString());
		Path path = upload.resolve(nameUniq);
		if (!Files.exists(upload))
			Files.createDirectories(upload);
		multipartFile.transferTo(path);
		return path;
	}

	public ImageData downloadImage(Long id) {
		if (!images.containsKey(id))
			throw new NotFoundException(String.format("Изображение с id: %d не найдено", id));
		Image image = images.get(id);
		byte[] data = loadFile(image);
		return new ImageData(image.getOriginalFileName(), data);
	}

	@SneakyThrows
	private byte[] loadFile(Image image) {
		Path path = Paths.get(image.getFilePath());
		if (Files.exists(path)) {
			return Files.readAllBytes(path);
		}
		throw new NotFoundException(
				String.format("Файл не найден. id: %d, name: %s", image.getId(), image.getOriginalFileName()));
	}

	private Long nextId() {
		Long id = images.keySet().stream().mapToLong(k -> k).max().orElse(0);
		return ++id;
	}
}
