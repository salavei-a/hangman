package com.asalavei.hangman;

public class Game {

    private Hangman hangman;
    private String word;
    private Vocabulary vocabulary;

    private Game() {
        this.vocabulary = Vocabulary.getInstance();
        this.word = vocabulary.getWord();
        this.hangman = Hangman.createHangman();
    }

    public String getWord() {
        return word;
    }

    public static Game createGame() {
        Game game = new Game();

        return game;
    }

    public void startGame() {
        // start the game

        hangman.printHangman();

        System.out.println(hangman.getCurrentStep());

        System.out.println("Word is " + getWord());


        System.out.println("Word is " + vocabulary.getNextWord());



        // Play the game or quit (exit)
    }

    private void playGame() {
        // processing of game
    }

    private void quitGame() {
        // quit the game
    }

    private boolean isGameOver() {
        return false;
    }

}
