package com.nallapareddy.tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToeUtil {
	public void drawBoard(String[] board) {
		System.out.println("   |   |");
		System.out
				.println(" " + board[7] + " | " + board[8] + " | " + board[9]);
		System.out.println("   |   |");
		System.out.println("-----------");
		System.out.println("   |   |");
		System.out
				.println(" " + board[4] + " | " + board[5] + " | " + board[6]);
		System.out.println("   |   |");
		System.out.println("-----------");
		System.out.println("   |   |");
		System.out
				.println(" " + board[1] + " | " + board[2] + " | " + board[3]);
		System.out.println("   |   |");
	}

	public String inputPlayerLetter() {
		String letter = " ";
		Scanner scan = new Scanner(System.in);
		while(letter.equals("X") == false && letter.equals("O") == false) {
			System.out.println("Which letter do you want to be? (X or O)");
			letter = scan.nextLine().toUpperCase();
		}
		return letter;
	}

	public String whoGoesFirst() {
		Random rand = new Random();
		if (rand.nextInt(2) == 0) {
			return "computer";
		}
		return "player";
	}

	public boolean playAgain() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Do you want to play again?(yes or no)");
		String ans = scan.nextLine();
		if (ans.startsWith("y") == true) {
			return true;
		}
		return false;
	}

	public void makeMove(String[] board, String letter, int move) {
		board[move] = letter;
	}

	public boolean isWinner(String[] bo, String le) {
		if ((bo[7] == le && bo[8] == le && bo[9] == le)
				|| (bo[4] == le && bo[5] == le && bo[6] == le)
				|| (bo[1] == le && bo[2] == le && bo[3] == le)
				|| (bo[7] == le && bo[4] == le && bo[1] == le)
				|| (bo[8] == le && bo[5] == le && bo[2] == le)
				|| (bo[9] == le && bo[6] == le && bo[3] == le)
				|| (bo[7] == le && bo[5] == le && bo[3] == le)
				|| (bo[9] == le && bo[5] == le && bo[1] == le)) {
			return true;
		}
		return false;

	}

	public String[] getBoardCopy(String[] board) {
		String[] dupeBoard = new String[10];
		for (int i = 0; i < board.length; i++) {
			dupeBoard[i] = board[i];
		}
		return dupeBoard;
	}

	public boolean isSpaceFree(String[] board, int move) {
		if (board[move] == " ") {
			return true;
		}
		return false;
	}

	public int getPlayerMove(String[] board) {
		int move = -1;
		Scanner scan = new Scanner(System.in);
		while (move<0 == true || move>9==true) {
			while(move == -1 || isSpaceFree(board,move)== false){
				System.out.println("What is your move?(1-9)");
				if(scan.hasNextInt()){
					move = scan.nextInt();
				}
			}
		}
		return move;
	}

	public int chooseRandomMoveFromList(String[] board, int[] movesList) {
		List possibleMoves = new ArrayList();
		for (int i = 0; i < movesList.length; i++) {
			if (isSpaceFree(board, i) == true) {
				possibleMoves.add(i);
			}
		}
		if (possibleMoves.size() != 0) {
			Random rand = new Random();
			int num = rand.nextInt(possibleMoves.size());
			return (Integer) possibleMoves.get(num);
		}

		return 0;
	}

	public int getComputerMove(String[] board, String computerLetter) {
		String playerLetter = "";
		if (computerLetter == "X") {
			playerLetter = "O";
		} else {
			playerLetter = "X";
		}

		for (int i = 1; i < 10; i++) {
			String[] copy = new String[10];
			copy = getBoardCopy(board);
			if (isSpaceFree(copy, i) == true) {
				makeMove(copy, computerLetter, i);
				if (isWinner(copy, computerLetter) == true) {
					return i;
				}

				for (int j = 1; j < 10; j++) {
					String[] copy2 = new String[10];
					copy2 = getBoardCopy(board);
					if (isSpaceFree(copy2, j) == true) {
						makeMove(copy, playerLetter, i);
						if (isWinner(copy, playerLetter) == true) {
							return j;
						}
					}

				}


			}

		}
		int[] movesList = new int[]{1,3,7,9};
		int move = chooseRandomMoveFromList(board,movesList);
		if(move != 0){
			return move;
		}
		if(isSpaceFree(board, 5) == true){
			return 5;
		}
		int[] movesList2 = new int[]{2,4,6,8};
		return chooseRandomMoveFromList(board, movesList2);
	}
	public boolean isBoardFull(String[] board){
		for(int i =1;i<10;i++){
			if(isSpaceFree(board,i) == true){
				return false;
			}
		}
		return true;
	}
	
	
}
