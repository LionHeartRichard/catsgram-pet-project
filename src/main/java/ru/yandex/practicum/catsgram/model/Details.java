package ru.yandex.practicum.catsgram.model;

import java.util.Date;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder(toBuilder = true)
public class Details {

	@NonNull
	private String email;
	@NonNull
	private String firstName;
	@NonNull
	private String lastName;
	private String information;
	private Date dayOfBirthday;
	@Builder.Default
	private Gender gender = Gender.UNKNOWN;
}
