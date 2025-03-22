package ru.yandex.practicum.catsgram.util;

public final class PatternUtil {
	public static final String ONE_DIGIT = "(?=.*[0-9])";
	public static final String LOWER_CASE = "(?=.*[a-z])";
	public static final String UPPER_CASE = "(?=.*[A-Z])";
	public static final String SPECIAL_CHAR = "(?=.*[@#$%^&+=])";
	public static final String NO_SPACE = "(?=\\S+$)";

	private static final String MIN_LEN = "8";
	private static final String MAX_LEN = "20";

	public static final String MIN_AND_MAX = ".{" + MIN_LEN + "," + MAX_LEN + "}";

	public static final String PASSWORD = ONE_DIGIT + LOWER_CASE + UPPER_CASE + SPECIAL_CHAR + NO_SPACE
			+ MIN_AND_MAX;
}
