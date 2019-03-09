/*
 * Copyright 2019 Christopher Coe
 * https://github.com/chrisdcoe
 */

package tictactoeapplication;

import java.util.Scanner;

/* @author Chris */
public class TicTacToeApplication {

    public static void main(String[] args) {
        // Get input
        Scanner sc = new Scanner(System.in);
        
        // Allow for continuous games
        boolean doYouWantToPlay = true;
        while (doYouWantToPlay) { 
            System.out.println("Welcome to Tic Tac Toe.");
            System.out.println("Enter a symbol to represent you (such as X or O): ");
            char playerToken = sc.next().charAt(0);
            System.out.println("Enter a symbol to represent your opponent (such as X or O): ");
            char opponentToken = sc.next().charAt(0);
            TicTacToe game = new TicTacToe(playerToken, opponentToken);
            AI ai = new AI();
            
            // Set up the game
            System.out.println();
            System.out.println("Starting the game. \nTo play, enter a number, " 
                + "and your token will be put in its place.\nThe numbers go from "
                + "1-9, left to right.");
            TicTacToe.printIndexBoard();
            System.out.println();
            
            // Play game
            while (game.gameOver().equals("notOver")) {
                if (game.currentMarker == game.userMarker) {
                    // User's turn
                    System.out.println("It's your turn. Enter a spot for your token.");
                    int spot = sc.nextInt();
                    while(!game.playTurn(spot)) {
                        System.out.println("Try again. " + spot + " is invalid. " 
                            + "This spot is already taken or out of range.");
                        spot = sc.nextInt();
                    }
                    System.out.println("You picked " + spot + ".");
                } else {
                    // AI's turn
                    System.out.println("My turn.");
                    // AI picks spot
                    int aiSpot = ai.pickSpot(game);
                    game.playTurn(aiSpot);
                    System.out.println("I picked " + aiSpot + ".");
                }
                // Print out the current board
                System.out.println();
                game.printBoard();
            }
            System.out.println(game.gameOver());
            System.out.println();
            
            // Set up a new game, or not
            System.out.println("Do you want to play again? (Y/N)");
            char response = sc.next().charAt(0);
            doYouWantToPlay = (response == 'Y' || response == 'y');
            System.out.println();
            System.out.println();
            
        }
    }

}
