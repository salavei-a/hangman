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
        this.attempt = 6;
        this.currentWord = new StringBuilder("*".repeat(word.length()));
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

        while (isGameOver()) {
            hangman.printHangman();
            printAction();

            if (currentWord.indexOf("*") == -1) {
                System.out.println("Congratulations! You won. \nWord is " + getWord());
                startGame();
            }

            checkLetter(scanner.nextLine());
        }

        hangman.printHangman();
        System.out.println("Word is " + getWord());

        startGame();
    }

    private void exitGame() {
        System.out.println("Exit the game");
    }

    private void checkLetter(String letter) {

        if (word.contains(letter)) {
            printWord(letter);
        } else {
            attempt--;
            System.out.println("Такой буквы нет, осталось попыток: " + attempt);

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

            System.out.println(currentWord);
        }
    }

    private void printWord(String letter) {
        StringBuilder temproaryWord = currentWord;
        currentWord = new StringBuilder();

        for (int i = 0; i < word.length(); i++) {

            if (word.charAt(i) == letter.charAt(0)) {
                currentWord.append(word.charAt(i));
            } else {
                currentWord.append(temproaryWord.charAt(i));
            }
        }

        System.out.println(currentWord);
    }

    private void printAction() {
        System.out.println("Введите букву: ");
    }

    private boolean isGameOver() {
        return attempt > 0;
    }

}
