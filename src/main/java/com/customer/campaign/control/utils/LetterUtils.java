package com.customer.campaign.control.utils;

public class LetterUtils {

	public static boolean isConsonant(char letter) {
		return !isVowel(letter);
	}

	public static Character isPreviousConsonant(String value, char letter) {
		char previousLetter = value.charAt(value.indexOf(letter) - 1);

		if (isConsonant(previousLetter)) {
			return previousLetter;
		} else {
			return null;
		}
	}

	public static boolean isVowel(char letter) {
		String lower = "";

		lower += letter;

		return (lower.toLowerCase().equals("a") || lower.toLowerCase().equals("e") || lower.toLowerCase().equals("i")
				|| lower.toLowerCase().equals("o") || lower.toLowerCase().equals("u"));
	}

	public static boolean isPreviousVowel(String value, char letter) {
		char previousLetter = value.charAt(value.indexOf(letter) - 1);

		return isVowel(previousLetter);
	}

}
