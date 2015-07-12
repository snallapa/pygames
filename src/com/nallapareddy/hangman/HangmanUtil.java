package com.nallapareddy.hangman;

import java.util.Random;
import java.util.Scanner;


public class HangmanUtil {
	public static String HANGMAN_PICS[] = new String[7];

	public HangmanUtil(){
		 HANGMAN_PICS[0] =  "  +---+  \n  |   |  \n      |  \n      |  \n      |  \n      | \n=========";
		 HANGMAN_PICS[1] =  "  +---+  \n  |   |  \n  O   |  \n      |  \n      |  \n      | \n=========";
		 HANGMAN_PICS[2] =  "  +---+  \n  |   |  \n  O   |  \n  |   |  \n      |  \n      | \n=========";
		 HANGMAN_PICS[3] =  "  +---+  \n  |   |  \n  O   |  \n /|   |  \n      |  \n      | \n=========";
		 HANGMAN_PICS[4] =  "  +---+  \n  |   |  \n  O   |  \n /|\\  |  \n      |  \n      | \n=========";
		 HANGMAN_PICS[5] =  "  +---+  \n  |   |  \n  O   |  \n /|\\  |  \n /    |  \n      | \n=========";
		 HANGMAN_PICS[6] =  "  +---+  \n  |   |  \n  O   |  \n /|\\  |  \n / \\  |  \n      | \n=========";
	}
	
	public String getWord(String[] words){
		Random rand = new Random();
		int index = rand.nextInt(words.length);
		return words[index];
	}
	private int charIn(char ch, String word){
		for(int i =0;i<word.length();i++){
			if(word.charAt(i) == ch){
				return i;
			}
		}
		return -1;
	}
	
	public void displayBoard(String missedLetters, String correctLetters, String secretWord){
		System.out.println(HANGMAN_PICS[missedLetters.length()]);
		System.out.println();
		
		System.out.println("Missed Letters: " + missedLetters);
		char[] blank = new char[secretWord.length()];

		for(int i =0;i<secretWord.length();i++){
			int pos = charIn(secretWord.charAt(i), correctLetters);
			if(pos == -1){
				blank[i] = '_';
			}
			else{
				blank[i] = secretWord.charAt(i);
			}
		}

		System.out.println(blank);
		
	}
	public String getGuess(String alreadyGuessed){
		boolean TrueGuess = true;
		Scanner scan = new Scanner(System.in);
		System.out.println("Guess a Letter");
		String guess = scan.nextLine();
		guess=guess.toLowerCase();
		while(TrueGuess == true){
			if(guess.length() != 1){
				System.out.println("Please enter a single letter");
			}
			char[] alreadyArray = new char[alreadyGuessed.length()];
			alreadyGuessed.getChars(0, alreadyGuessed.length(), alreadyArray, 0);
			if(guess.length() !=1){
				guess = " ";
			}
			char guessChar = guess.charAt(0);
			for(int i =0;i<alreadyGuessed.length();i++){
				if(guessChar == alreadyArray[i]){
					System.out.println("You have already guessed that letter");
				}
			}
			if(Character.isLetter(guessChar)!= true && guess!=" "){
				System.out.println("Please choose a letter");
			}
			else{
				TrueGuess=false;
			}
		}
		return guess;
		
	}
	public boolean playAgain(){
		System.out.println("Do you Want to play again");
		Scanner scan = new Scanner(System.in);
		String answer = scan.nextLine();
		if(answer.startsWith("y") == true){
			return true;
		}
		return false;
	}
	
	
}
