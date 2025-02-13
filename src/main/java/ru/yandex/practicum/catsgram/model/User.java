package ru.yandex.practicum.catsgram.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class User {
	@NonNull
	private Details details;
	private PurchasesInformation purchasesInformation;
}
