package ru.yandex.practicum.catsgram.model;

import java.util.Date;

import lombok.Data;

@Data
public class PurchasesInformation {
	private Date lastPurchase;
	private long purchaseCounts = 0;
}
