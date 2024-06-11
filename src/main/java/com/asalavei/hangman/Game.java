package com.asalavei.hangman;

import java.util.Scanner;

public class Game {

    private Hangman hangman;
    private String word;
    private Vocabulary vocabulary;
    private final Scanner scanner;

    private Game() {
        this.vocabulary = Vocabulary.getInstance();
        this.word = vocabulary.getWord();
        this.hangman = Hangman.createHangman();
        this.scanner = new Scanner(System.in);
    }

    public String getWord() {
        return word;
    }

    public static Game createGame() {
        Game game = new Game();

        return game;
    }

    public void startGame() {
        System.out.println("Press to start a [N]ew game or e[X]it:");

        String button = scanner.nextLine();

        if (button.equals("N") || button.equals("n")) {
            playGame();
        } else exitGame();
    }

    private void playGame() {
        System.out.println("Processing the game");

        hangman.printHangman();

        System.out.println(hangman.getCurrentStep());
        System.out.println("Word is " + getWord());
        System.out.println("Word is " + vocabulary.getNextWord());
    }

    private void exitGame() {
        System.out.println("Exit the game");
    }

    private boolean isGameOver() {
        return false;
    }

}
