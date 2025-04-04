package ru.yandex.practicum.catsgram;

import java.util.Map;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

@SpringBootApplication
public class CatsgramApplication {
	public static void main(String[] args) {
		SpringApplication.run(CatsgramApplication.class, args);
		final Gson gson = new Gson();
		try (final Scanner scanner = new Scanner(System.in)) {
			System.out.print("Введите JSON => ");
			final String input = scanner.nextLine();

			gson.fromJson(input, Map.class);
			System.out.println("Был введён корректный JSON");
		} catch (JsonSyntaxException exception) {
			System.out.println("Был введён некорректный JSON");
		}
	}
}
