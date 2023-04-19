package com.chapter04;

public class Word {
	private String letters;
	private String[] words = { "a", "e", "i", "o", "u" };
	public Word(String letters) {
		this.letters = letters;
	}

	public boolean isVowel(int i) {
		for (String word : words) {
			if (letters.substring(i, i + 1).toLowerCase().equals(word)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isConsonant(int i) {
		for (String word : words) {
			if (letters.substring(i, i + 1).toLowerCase().equals(word)) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		Word word = new Word("Asdfghjkl");

		System.out.println(word.isVowel(0));
		System.out.println(word.isConsonant(0));
	}
}
