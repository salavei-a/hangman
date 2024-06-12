package com.asalavei.hangman;

import java.util.Scanner;

public class Game {

    private final Scanner scanner;
    private Hangman hangman;
    private String word;
    private Vocabulary vocabulary;
    private int attempt;
    private StringBuilder currentWord;

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
            System.out.println("There is no such letter. Attempts left: " + attempt);

            updateHangmanState();

            System.out.println(currentWord);
        }
    }

    private void updateHangmanState() {
        switch (attempt) {
            case 5:
                hangman.setCurrentStep(HangmanStep.STEP_ONE);
                break;
            case 4:
                hangman.setCurrentStep(HangmanStep.STEP_TWO);
                break;
            case 3:
                hangman.setCurrentStep(HangmanStep.STEP_THREE);
                break;
            case 2:
                hangman.setCurrentStep(HangmanStep.STEP_FOUR);
                break;
            case 1:
                hangman.setCurrentStep(HangmanStep.STEP_FIVE);
                break;
            case 0:
                hangman.setCurrentStep(HangmanStep.STEP_SIX);
                break;
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
        System.out.println("Attempts left: " + attempt);
    }

    private boolean isGameOver() {
        return attempt > 0;
    }

}
