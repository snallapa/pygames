package com.nallapareddy.hangman;
import java.util.Arrays;

public class Hangman {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] words = new String[] { "ant", "baboon", "badger", "bat",
				"bear", "beaver", "camel", "cat", "clam", "cobra", "cougar",
				"coyote", "crow", "deer", "dog", "donkey", "duck", "eagle",
				"ferret", "fox", "frog", "goat", "goose", "hawk", "lion",
				"lizard", "llama", "mole", "monkey" };

		HangmanUtil hang = new HangmanUtil();
		System.out.println("H A N G M A N");
		String missedLetters = "";
		String correctLetters = "";
		String secretWord = hang.getWord(words);
		boolean gameIsDone = false;
		char[] secretWordArray = new char[secretWord.length()];
		secretWord.getChars(0, secretWord.length(), secretWordArray, 0);

		while (true) {
			hang.displayBoard(missedLetters, correctLetters, secretWord);
			String guess = hang.getGuess(missedLetters + correctLetters);
			if (secretWord.contains(guess) == true) {
				correctLetters = correctLetters + guess;

				char[] correctLettersArray = new char[correctLetters.length()];
				correctLetters.getChars(0, correctLetters.length(),
						correctLettersArray, 0);
				Arrays.sort(secretWordArray);
				Arrays.sort(correctLettersArray);
				String newSecret  = new String(secretWordArray);
				String newCorrect = new String(correctLettersArray);
				boolean foundAllLetters = true;
				if(newCorrect.contains(newSecret) == false){
					foundAllLetters=false;
				}
				if (foundAllLetters == true) {
					System.out.println("Yes! The secret word is " + secretWord
							+ "! You have won!");
					gameIsDone = true;
				}
			} else {
				missedLetters = missedLetters + guess;
				if (missedLetters.length() == HangmanUtil.HANGMAN_PICS.length - 1) {
					hang.displayBoard(missedLetters, correctLetters, secretWord);
					System.out.println("You have run out of guesses!\nAfter "
							+ missedLetters.length() + " missed guesses and "
							+ correctLetters.length()
							+ " correct guesses, the word was " + secretWord);
					gameIsDone = true;
				}
			}

			if (gameIsDone == true) {
				boolean play = hang.playAgain();
				if (play == true) {
					missedLetters = "";
					correctLetters = "";
					gameIsDone = false;
					secretWord = hang.getWord(words);
				} else {
					break;
				}
			}

		}

	}

}