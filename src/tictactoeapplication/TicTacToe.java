/*
 * Copyright 2019 Christopher Coe
 * https://github.com/chrisdcoe
 */

package tictactoeapplication;

/* @author Chris */
public class TicTacToe {
    protected char[] board;
    protected char userMarker;
    protected char aiMarker;
    protected char winner;
    protected char currentMarker;
    
    // NOTE:
    // I reference the board array with standard values 0-8
    // But the player interacts with board spaces 1-9
    
    // Constructor
    public TicTacToe(char playerToken, char aiMarker) {
        this.userMarker = playerToken;
        this.aiMarker = aiMarker;
        this.winner = '-';
        this.board = setBoard();
        this.currentMarker = userMarker;
    }
    
    // Methods
    public static char[] setBoard() {
        char[] board = new char[9];
        for (int i = 0; i < board.length; i++) {
            board[i] = '-';
        }
        return board;
    }
    
    public boolean playTurn(int spot) {
        boolean isValid = withinRange(spot) && !isSpotTaken(spot);
        if (isValid) {
            board[spot-1] = currentMarker;
            // flip to AI's turn
            currentMarker = (currentMarker == userMarker) ? aiMarker : userMarker;
        }
        return isValid;
    }
    
    // Check if spot is in range
    public boolean withinRange(int number) {
        // number must be 1 to 9
        return number > 0 && number < board.length + 1;
    }
    
    // Check if spot is taken
    public boolean isSpotTaken(int number) {
        // A board space is '-' if empty, or has an 'X' or 'O' if taken
        return board[number-1] != '-';
    }
    
    // Print out the board
    public void printBoard() {
        // Board should look like this
        // | - | - | - 
        // ------------
        // | - | - | - 
        // ------------
        // | - | - | - 
    
        System.out.println();
        for (int i = 0; i < board.length; i++) {
            
            if (i % 3 == 0 && i != 0) {
                System.out.println();
                System.out.println("-------------");
            }
            System.out.print(" | " + board[i]);
        }
        System.out.println();
    }
    
    // Show player the board and indexes
    public static void printIndexBoard() {
        System.out.println();
        for (int i = 0; i < 9; i++) {
            
            if (i % 3 == 0 && i != 0) {
                System.out.println();
                System.out.println("------------");
            }
            System.out.print(" | " + (i + 1));
        }
        System.out.println();
    }
    
    public boolean isThereAWinner() {
        // We want to know if the symbols are all the same, but only if it's not the blank symbol
        // Check diagonal lines, middle row, and middle column
        boolean diagonalsAndMiddles = (rightDi() || leftDi() || middleRow() || secondCol()) && board[4] != '-';
        
        // Check top row and first column
        boolean topAndFirst = (topRow() || firstCol()) && board[0] != '-';
        
        // Check bottom row and third column
        boolean bottomAndThird = (bottomRow() || thirdCol()) && board[8] != '-';
        
        // Logic for if a winner is detected
        // Check the common space, the symbol there is the player who won
        if (diagonalsAndMiddles) {
            this.winner = board[4];
        } else if (topAndFirst) {
            this.winner = board[0];
        } else if (bottomAndThird) {
            this.winner = board[8];
        }
        // Returns true if a winner was found
        return diagonalsAndMiddles || topAndFirst || bottomAndThird;
    }
    
    // Helper functions
    // Check if a row/column/diagonal's symbols are all the same
    public boolean topRow() {
        return board[0] == board[1] && board[1] == board[2];
    }
    
    public boolean middleRow() {
        return board[3] == board[4] && board[4] == board[5];
    }
    
    public boolean bottomRow() {
        return board[6] == board[7] && board[7] == board[8];
    }
    
    public boolean firstCol() {
        return board[0] == board[3] && board[3] == board[6];
    }
    
    public boolean secondCol() {
        return board[1] == board[4] && board[4] == board[7];
    }
    
    public boolean thirdCol() {
        return board[2] == board[5] && board[5] == board[8];
    }
    
    public boolean leftDi() {
        return board[0] == board[4] && board[4] == board[8];
    }
    
    public boolean rightDi() {
        return board[2] == board[4] && board[4] == board[6];
    }
    
    public boolean isTheBoardFilled() {
        for (int i = 0; i < board.length; i++) {
            if (board[i] == '-') {
                return false;
            }
        }
        return true;
    }
    
    public String gameOver() {
        boolean didSomeoneWin = isThereAWinner();
        if (didSomeoneWin) {
            return "We have a winner: " + this.winner + "!";
        } else if (isTheBoardFilled()) {
            return "Draw: Game Over";
        } else {
            return "notOver";
        }
    }
}
