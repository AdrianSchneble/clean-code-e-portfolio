package dhbw.softwareengineering.good;

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
		ArrayList<List<Character>> list = new ArrayList<>();
		if (useDigits) {
			list.add(listFromIntArray(digits));
		}
		if (useLowerCaseLetters) {
			list.add(listFromCharArray(lowerCaseLetters));
		}
		if (useUpperCaseLetters) {
			list.add(listFromCharArray(upperCaseLetters));
		}
		if (useSpecialChars) {
			list.add(listFromCharArray(specialChars));
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
			result.append(selectRandomChar(validChars));
		}
		return result.toString();
	}

	private Character selectRandomChar(List<List<Character>> list) {
		int type = (int) Math.round((Math.random() * (list.size() - 1)));
		List<Character> c = list.get(type);
		Character randomChar = c.get((int) Math.round((Math.random() * (c.size() - 1))));
		return randomChar;
	}
}
