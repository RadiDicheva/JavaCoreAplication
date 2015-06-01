import java.util.ArrayList;
import java.util.Scanner;

public class SmallestSubstring {

	private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

	public static String smallestSubstringContainingTheAlphabet(String str) {
		if (str.length() < ALPHABET.length())
			throw new IllegalArgumentException("Your string isn't long enough!");

		String substr;
		ArrayList<String> substrings = new ArrayList<String>();

		for (int i = 0; i < str.length(); i++) {
			for (int j = 1; j <= str.length() - i; j++) {
				substr = str.substring(i, j + i);
				if (Character.isAlphabetic(substr.charAt(0))
						&& Character
								.isAlphabetic(substr.charAt(substr.length() - 1))
						&& substr.length() >= ALPHABET.length()
						&& containsTheAlphabet(substr)) {
					substrings.add(substr);
				}
			}
		}

		String result = substrings.get(0);

		for (int i = 1; i < substrings.size(); i++) {
			String string = substrings.get(i);
			if (string.length() < result.length()) {
				result = string;
			}
		}
		return result;
	}

	static boolean containsTheAlphabet(String str) {
		ArrayList<Character> letters = new ArrayList<Character>();

		for (int i = 0; i < ALPHABET.length(); i++) {
			letters.add(ALPHABET.charAt(i));
		}

		char[] strCharArray = str.toCharArray();

		for (Character c : strCharArray) {
			if (letters.contains(c)) {
				letters.remove(c);
			}
		}

		if (!letters.isEmpty())
			return false;
		else
			return true;
	}

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		System.out.printf("Input string: ");
		String inputString = scan.nextLine();
		inputString = inputString.toLowerCase();

		System.out
				.println("The smallest substring, containing the alphabet is: ["
						+ smallestSubstringContainingTheAlphabet(inputString)
						+ "]");

		scan.close();
	}
}
