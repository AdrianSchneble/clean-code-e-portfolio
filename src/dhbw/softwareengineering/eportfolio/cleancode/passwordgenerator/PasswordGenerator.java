package dhbw.softwareengineering.eportfolio.cleancode.passwordgenerator;

import java.util.ArrayList;
import java.util.List;

public class PasswordGenerator {

	private static char digits[] = new char[10];
	private static char lowerCaseLetters[] = new char[29];
	private static char upperCaseLetters[] = new char[29];
	private static char specialChars[] = new char[29];
	private List<List<Character>> validCharLists = new ArrayList<>();

	static {
		initDigitList();
		initLetterLists();
		initSpecialCharsList();
	}

	private static void initDigitList() {
		String digitString = "0123456789";
		digits = digitString.toCharArray();
	}

	private static void initLetterLists() {
		String alphabet = "abcdefghijklmnopqrstuvwxyzäöü";
		lowerCaseLetters = alphabet.toLowerCase().toCharArray();
		upperCaseLetters = alphabet.toUpperCase().toCharArray();
	}

	private static void initSpecialCharsList() {
		String specials = "!\"§$%&/()={[]}+*~'#-_.:,;@<>|";
		specialChars = specials.toCharArray();
	}

	public PasswordGenerator(boolean useDigits, boolean useLowerCaseLetters, boolean useUpperCaseLetters,
			boolean useSpecialChars) {
		if (useDigits) {
			validCharLists.add(listFromCharArray(digits));
		}
		if (useLowerCaseLetters) {
			validCharLists.add(listFromCharArray(lowerCaseLetters));
		}
		if (useUpperCaseLetters) {
			validCharLists.add(listFromCharArray(upperCaseLetters));
		}
		if (useSpecialChars) {
			validCharLists.add(listFromCharArray(specialChars));
		}
	}

	private List<Character> listFromCharArray(char[] array) {
		List<Character> list = new ArrayList<>();
		for (char c : array) {
			list.add(c);
		}
		return list;
	}

	public String generatePassword(int length) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < length; i++) {
			result.append(selectRandomChar());
		}
		return result.toString();
	}

	private Character selectRandomChar() {
		int randomCharacterList = (int) Math.round((Math.random() * (validCharLists.size() - 1)));
		List<Character> c = validCharLists.get(randomCharacterList);
		Character randomChar = c.get((int) Math.round((Math.random() * (c.size() - 1))));
		return randomChar;
	}
}
