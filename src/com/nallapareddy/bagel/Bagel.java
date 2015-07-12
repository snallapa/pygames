package com.nallapareddy.bagel;

import java.util.Random;
import java.util.Scanner;

public class Bagel {

	final static int NUMDIGITS = 4;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		while (true) {
			intro();
			boolean gameWon = false;
			int secretNum = getSecretNum(NUMDIGITS);
			System.out.println(secretNum);
			int numGuesses = 0;
			while (gameWon == false) {
				System.out.print("Your guess? ");
				Scanner scan = new Scanner(System.in);
				int guess = scan.nextInt();
				numGuesses++;

				if (guess == secretNum) {
					System.out.println("You got it right in " + numGuesses
							+ " guesses");
					gameWon = true;
				} else {
					System.out.println(getClues(secretNum, guess));
				}
			}
			if(playAgain() == false){
				break;
			}
			/*
			 * while (true) { int secretNum = util.getSecretNum(NUMDIGITS);
			 * System.out.println(secretNum); System.out
			 * .println("I have thought up a number. You have 10 guesses to get it."
			 * ); int numGuesses = 1; int i = 1; boolean won = false;
			 * 
			 * while (numGuesses <= MAXGUESS) { int guess = 0; while
			 * (String.valueOf(guess).length() != NUMDIGITS ||
			 * util.isOnlyDigits(guess) == false) {
			 * System.out.print("Your guess? "); Scanner scan = new
			 * Scanner(System.in); if (scan.hasNextInt()) { guess =
			 * scan.nextInt(); if (String.valueOf(guess).length() == NUMDIGITS)
			 * { i = i + 1; } else {
			 * System.out.println("Put in a four digit number!!"); } } else {
			 * System.out.println("Put in a four digit number!!"); } } String
			 * clue = util.getClues(secretNum, guess); System.out.println(clue);
			 * numGuesses = numGuesses + 1;
			 * 
			 * if (guess == secretNum) { won = true; break; } if (numGuesses >
			 * MAXGUESS) { System.out
			 * .println("You ran out of guesses. The answer was " + secretNum);
			 * } } if (won) { System.out.println("You won in " + (numGuesses -
			 * 1) + " guesses"); System.out.println(); } if (util.playAgain() ==
			 * false) { break; } }
			 */
		}
	}

	public static int getSecretNum(int digits) {
		Random rand = new Random();
		return rand.nextInt(9000) + 1000;
	}

	public static int[] digitArray(int num) {
		int[] digits = new int[NUMDIGITS];
		for (int i = 0; i < NUMDIGITS; i++) {
			digits[i] = num % 10;
			num = num / 10;
		}
		return digits;
	}

	public static void intro() {
		System.out.println("I am thinking of a " + NUMDIGITS
				+ "-digit numer. Try to guess what it is.");
		System.out.println("When i say:    That means:");
		System.out
				.println("   Pica       One digit is correct but in the wrong position");
		System.out
				.println("   Fermi      One digit is correct and in the right position");
		System.out.println("   Bagels     None of the digits are correct.");
	}

	public static String getClues(int secretNum, int guess) {
		int[] secretNumArray = digitArray(secretNum);
		int[] guessArray = digitArray(guess);
		int[] tempSecretArray = new int[NUMDIGITS];
		for (int i = 0; i < NUMDIGITS; i++) {
			tempSecretArray[i] = secretNumArray[i];
		}
		
		int[] tempGuessArray = new int[NUMDIGITS];
		for (int i = 0; i < NUMDIGITS; i++) {
			tempGuessArray[i] = guessArray[i];
		}
		
		String clueFermi = "";
		String cluePico = "";
		int index = -1;
		for (int i = 0; i < NUMDIGITS; i++) {
			if (tempSecretArray[i] == guessArray[i]) {
				clueFermi = clueFermi + "Fermi ";
				tempSecretArray[i] = -1;
			}
		}
		for (int i = 0; i < NUMDIGITS; i++) {
			for (int j = 0; j < NUMDIGITS; j++) {
				if (tempSecretArray[j] == tempGuessArray[i]) {
					cluePico = cluePico + "Pica ";
					tempSecretArray[j] = -1;
					tempGuessArray[i] = -2;
				}
			}
		}
		if (cluePico.isEmpty() && clueFermi.isEmpty()) {
			return "Bagels";
		}
		return clueFermi + cluePico;
	}

	public static boolean playAgain() {
		System.out.print("Do you want to play again ");
		Scanner scan = new Scanner(System.in);
		String answer = scan.next();
		if (answer.toLowerCase().startsWith("y")) {
			System.out.println();
			return true;
		} else {
			return false;
		}
		
		
	}

}
