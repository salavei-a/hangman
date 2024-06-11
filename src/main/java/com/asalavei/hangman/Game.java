package com.asalavei.hangman;

import java.util.Scanner;

public class Game {

    private final Scanner scanner;
    private Hangman hangman;
    private String word;
    private Vocabulary vocabulary;
    private int attempt;

    private Game() {
        this.vocabulary = Vocabulary.getInstance();
        this.word = vocabulary.getWord();
        this.hangman = Hangman.createHangman();
        this.scanner = new Scanner(System.in);
        this.attempt = 6;
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

        printAction();
        checkLetter(scanner.nextLine());

        System.out.println("Word is " + getWord());
    }

    private void exitGame() {
        System.out.println("Exit the game");
    }

    private void checkLetter(String letter) {

        if (word.contains(letter)) {
            printWord(letter);
        } else {
            System.out.println("Такой буквы нет, осталось попыток: " + attempt--);
            hangman.setCurrentStep(HangmanStep.STEP_ONE);
        }

    }

    private void printWord(String letter) {
        for (int i = 0; i < word.length(); i++) {

            if (word.charAt(i) == letter.charAt(0)) {
                System.out.print(word.charAt(i));
            } else {
                System.out.print("*");
            }
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
