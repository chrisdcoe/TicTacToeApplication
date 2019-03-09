/*
 * Copyright 2019 Christopher Coe
 * https://github.com/chrisdcoe
 */

package tictactoeapplication;

import java.util.ArrayList;
import java.util.Random;

/* @author Chris */
class AI {
    public int pickSpot(TicTacToe game) {
        // Simple AI looks at all available board spots and picks one at random
        ArrayList<Integer> choices = new ArrayList();
        for (int i=0; i < 9; i++) {
            // If the slot is not taken, add it as a choice
            if (game.board[i] == '-') {
                choices.add(i+1); // Check index 0-8, but add spots 1-9
            }
        }
        Random rand = new Random();
        int choice = choices.get(Math.abs(rand.nextInt() % choices.size()));
        return choice;
    }
}
