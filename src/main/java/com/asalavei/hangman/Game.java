package com.asalavei.hangman;

public class Game {

    private Hangman hangman;
    private String word;

    public Game() {
        this.word = new Vocabulary().getRandomWord();
        this.hangman = new Hangman(HangmanStep.STEP_ONE);
    }

    public String getWord() {
        return word;
    }

    public void startGame() {
        // start the game

        hangman.printHangman();

        System.out.println(hangman.getCurrentStep());

        System.out.println("Word is " + getWord());


        // Play the game or quit (exit)
    }

    public void playGame() {
        // processing of game
    }

    public void quitGame() {
        // quit the game
    }

    public boolean isGameOver() {
        return false;
    }

}
