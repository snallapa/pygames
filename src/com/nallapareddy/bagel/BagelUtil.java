package com.nallapareddy.bagel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BagelUtil {

	public int getSecretNum(int digits) {
		// List<Integer> number = new ArrayList<Integer>();
		// StringBuffer sb = new StringBuffer();
		// for(int i = 0;i<10;i++){
		// number.add(i);
		// }
		// int secretWord = 0;
		// Collections.shuffle(number);
		// for(int i = 0;i<digits;i++){
		// secretWord = Integer.parseInt((sb.append(number.get(i)).toString()));
		// }
		int secretWord = 0;
		Random rand = new Random();
		int[] secretArray = new int[4];
		int secretNum = 0;
		for (int i = 0; i < secretArray.length; i++) {
			secretArray[i] = rand.nextInt(8) + 1;
		}
		for (int i = 0; i < secretArray.length; i++) {
			secretNum += (int) (secretArray[i] * Math.pow(10, Bagel.NUMDIGITS
					- i - 1));
		}
		return secretNum;
	}

	public String getClues(int secretNum, int guess) {
		if (guess == secretNum) {
			return "";
		}
		String guessStr = Integer.toString(guess);
		int[] guessArray = new int[guessStr.length()];
		for (int i = 0; i < guessStr.length(); i++) {
			guessArray[i] = guessStr.charAt(i) - '0';
		}
		String secretStr = Integer.toString(secretNum);
		int[] secretNumArray = new int[secretStr.length()];
		for (int i = 0; i < secretStr.length(); i++) {
			secretNumArray[i] = secretStr.charAt(i) - '0';
		}
		List<String> clue = new ArrayList<String>();
		int index = -1;
		
		for (int i = 0; i < String.valueOf(guess).length(); i++) {
			if (guessArray[i] == secretNumArray[i]) {
				clue.add("Fermi ");
				index = i;
			}
			for (int j = 0; j < Bagel.NUMDIGITS; j++) {
				if (j == index) {
					continue;
				}
				if (guessArray[i] == secretNumArray[j]) {
					clue.add("Pico ");
				}
			}
		}
		if (clue.isEmpty()) {
			return "Bagels";
		}
		String[] clueArray = new String[clue.size()];
		clue.toArray(clueArray);
		Arrays.sort(clueArray);
		String clueStr = "";
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < clue.size(); i++) {
			clueStr = sb.append(clueArray[i]).toString();
		}

		return clueStr;
	}

	public boolean isOnlyDigits(int num) {
		String numStr = Integer.toString(num);
		int[] numArray = new int[numStr.length()];
		for (int i = 0; i < numStr.length(); i++) {
			numArray[i] = numStr.charAt(i) - '0';
		}
		for (int i = 0; i < String.valueOf(num).length(); i++) {
			if (numArray[i] < 0 || numArray[i] > 9) {
				return false;
			}
		}
		return true;
	}

	public boolean playAgain() {
		System.out
				.println("Do you want to play again? (Type in yes for the next person to play)");
		Scanner scan = new Scanner(System.in);
		String ans = scan.nextLine();
		if (ans.startsWith("y")) {
			return true;
		}
		return false;
	}

}
