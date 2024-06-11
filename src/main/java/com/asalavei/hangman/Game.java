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

    public void startGame() {
        // start the game

        hangman.printHangman();

        System.out.println(hangman.getCurrentStep());

        System.out.println("Word is " + getWord());


        System.out.println("Word is " + vocabulary.getNextWord());



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

    public static Game createGame() {
        Game game = new Game();

        return game;
    }

}
