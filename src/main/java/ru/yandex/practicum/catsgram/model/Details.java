package ru.yandex.practicum.catsgram.model;

import java.util.Date;

import lombok.Data;
import lombok.NonNull;

@Data
public class Details {

	@NonNull
	private String email;
	@NonNull
	private String firstName;
	@NonNull
	private String lastName;
	private String information;
	private Date dayOfBirthday;
	private Gender gender = Gender.UNKNOWN;
}
