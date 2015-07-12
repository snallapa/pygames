package com.nallapareddy.tictactoe;

public class TicTacToe {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		while (true) {
			String[] board = new String[] { " ", " ", " ", " ", " ", " ", " ",
					" ", " ", " ", };
			TicTacToeUtil util = new TicTacToeUtil();
			String playerLetter = util.inputPlayerLetter();
			String computerLetter = "";
			if (playerLetter.equals("X") == true) {
				computerLetter = "O";
			} else {
				computerLetter = "X";
			}
			computerLetter.toString();
			String turn = util.whoGoesFirst();
			System.out.println("The " + turn + " will go first");
			boolean gameIsPlaying = true;
			while (gameIsPlaying) {
				if (turn == "player") {
					util.drawBoard(board);
					int move = util.getPlayerMove(board);
					util.makeMove(board, playerLetter, move);
					if (util.isWinner(board, playerLetter)) {
						util.drawBoard(board);
						System.out.println("You won");
						gameIsPlaying = false;
					} else {
						if (util.isBoardFull(board)) {
							util.drawBoard(board);
							System.out.println("The game is a tie");
							break;
						} else {
							turn = "computer";
						}
					}

				}
				else{
					int move = util.getComputerMove(board, computerLetter);
					util.makeMove(board, computerLetter, move);
					
					if(util.isWinner(board, computerLetter)){
						util.drawBoard(board);
						System.out.println("The computer wins");
						gameIsPlaying  = false;
					}
					else{
						if(util.isBoardFull(board)){
							util.drawBoard(board);
							System.out.println("The game is a tie");
							break;
						}
						else{
							turn = "player";
						}
					}
				}
			}
			boolean ans = util.playAgain();
			if(ans == false){
				break;
			}
		}

	}

}
