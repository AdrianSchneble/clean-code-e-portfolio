package dhbw.softwareengineering.eportfolio.cleancode.passwordgenerator;

import java.util.ArrayList;
import java.util.List;

public class PasswordGenerator {

	private static int digits[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	private static char lowerCaseLetters[] = new char[29];
	private static char upperCaseLetters[] = new char[29];
	private static char specialChars[] = new char[29];
	private List<List<Character>> validChars = new ArrayList<>();

	static {
		initLetterLists();
		initSpecialCharsList();
	}

	private static void initLetterLists() {
		String alphabet = "abcdefghijklmnopqrstuvwxyzäöü";
		lowerCaseLetters = alphabet.toLowerCase().toCharArray();
		upperCaseLetters = alphabet.toUpperCase().toCharArray();
	}

	private static void initSpecialCharsList() {
		String specials = "!\"§$" + '%' + "&/()={[]}+*~'#-_.:,;@<>|";
		System.out.println(specials.length());
		specialChars = specials.toCharArray();
	}

	public PasswordGenerator(boolean useDigits, boolean useLowerCaseLetters, boolean useUpperCaseLetters,
			boolean useSpecialChars) {
		if (useDigits) {
			validChars.add(listFromIntArray(digits));
		}
		if (useLowerCaseLetters) {
			validChars.add(listFromCharArray(lowerCaseLetters));
		}
		if (useUpperCaseLetters) {
			validChars.add(listFromCharArray(upperCaseLetters));
		}
		if (useSpecialChars) {
			validChars.add(listFromCharArray(specialChars));
		}
	}

	private List<Character> listFromIntArray(int[] array) {
		List<Character> list = new ArrayList<>();
		for (int i : array) {
			// (char)i returns the ASCII char with value i, not the char 'i'
			list.add((char) (i + '0'));
		}
		return list;
	}

	private List<Character> listFromCharArray(char[] array) {
		List<Character> list = new ArrayList<>();
		for (char c : array) {
			list.add(c);
		}
		return list;
	}

	public String generate(int length) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < length; i++) {
			result.append(selectRandomChar());
		}
		return result.toString();
	}

	private Character selectRandomChar() {
		int type = (int) Math.round((Math.random() * (validChars.size() - 1)));
		List<Character> c = validChars.get(type);
		Character randomChar = c.get((int) Math.round((Math.random() * (c.size() - 1))));
		return randomChar;
	}
}
