package ru.yandex.practicum.catsgram.model;

import java.util.Date;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class PurchasesInformation {
	Date lastPurchase;
	long purchaseCounts = 0;
}
