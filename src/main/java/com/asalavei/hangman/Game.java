package com.asalavei.hangman;

public class Game {

    private Game() {
    }

    public static void startGame() {
        // start the game
        Hangman hangman = new Hangman(HangmanStep.STEP_SIX);

        hangman.printHangman();


        // Play the game or quit (exit)
    }

    public static void playGame() {
        // processing of game
    }

    public static void quitGame() {
        // quit the game
    }

    public static boolean isOver() {
        return false;
    }

}
