package com.asalavei.hangman;

import java.util.Scanner;

public class Game {

    private final Scanner scanner;
    private Hangman hangman;
    private String word;
    private Vocabulary vocabulary;

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
        vocabulary.getNextWord();
        hangman.printHangman();


        checkLetter(word);
        printAction();


        System.out.println("Word is " + getWord());
    }

    private void exitGame() {
        System.out.println("Exit the game");
    }

    private void checkLetter(String word) {
        for (int i = 0; i < word.length(); i++) {
            System.out.print("*");
        }

        System.out.println();
    }

    private void printAction() {
        System.out.println("Введите букву: ");
    }

    private boolean isGameOver() {
        return false;
    }

}
