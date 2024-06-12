package com.asalavei.hangman;

import java.util.Scanner;

public class Game {

    private final Scanner scanner;
    private final Hangman hangman;
    private final Vocabulary vocabulary;
    private String word;
    private int attempt;
    private StringBuilder currentWord;

    private Game() {
        this.vocabulary = Vocabulary.getInstance();
        this.word = vocabulary.getWord();
        this.hangman = Hangman.getInstance();
        this.scanner = new Scanner(System.in);
    }

    public String getWord() {
        return word;
    }

    public static Game createGame() {
        return new Game();
    }

    public void startGame() {
        String button;

        do {
            System.out.println("Press to start a [N]ew game or e[X]it:");
            button = scanner.nextLine();

            if (button.equalsIgnoreCase("N")) {
                playGame();

            } else if (button.equalsIgnoreCase("X")) {
                exitGame();
                return;
            }

        } while (!button.equalsIgnoreCase("X"));
    }

    private void playGame() {
        word = vocabulary.getNextWord();
        currentWord = new StringBuilder("*".repeat(word.length()));
        attempt = 6;
        hangman.setCurrentStep(HangmanStep.STEP_START);

        System.out.println("Word is " + currentWord);

        while (isGameOver()) {

            if (currentWord.indexOf("*") == -1) {
                System.out.println("Congratulations! You won. \nWord is " + getWord() + "\n");
                return;
            }

            System.out.println("Attempts left: " + attempt);
            hangman.printHangman();
            System.out.println("Enter a letter: ");
            checkLetter(scanner.nextLine());
        }

        hangman.printHangman();
        System.out.println("Unfortunately you lost, try again. \nWord is " + getWord() + "\n");
    }

    private void exitGame() {
        System.out.println("Exit the game");
    }

    private void checkLetter(String letter) {

        if (letter == null || letter.isEmpty()) {
            System.out.println("You have not entered any letters, please enter a letter");
            return;
        }

        if (!letter.matches("[а-яА-ЯёЁ]")) {
            System.out.println("Please enter a Russian letter");
            return;
        }

        if (word.contains(letter)) {
            printWord(letter);
        } else {
            attempt--;
            System.out.println("There is no such letter.");

            hangman.updateHangmanState(attempt);

            System.out.println(currentWord);
        }
    }

    private void printWord(String letter) {
        StringBuilder temporaryWord = new StringBuilder(currentWord);
        currentWord = new StringBuilder();

        for (int i = 0; i < word.length(); i++) {

            if (word.charAt(i) == letter.charAt(0)) {
                currentWord.append(word.charAt(i));
            } else {
                currentWord.append(temporaryWord.charAt(i));
            }
        }

        System.out.println(currentWord);
    }

    private boolean isGameOver() {
        return attempt > 0;
    }

}
