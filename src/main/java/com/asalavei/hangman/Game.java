package com.asalavei.hangman;

import java.util.Scanner;

public class Game {

    private final Scanner scanner;
    private final Hangman hangman;
    private final Vocabulary vocabulary;
    private String word;
    private int attempt;
    private StringBuilder currentWordState;
    private StringBuilder enteredLetters;

    private Game() {
        this.vocabulary = Vocabulary.getInstance();
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
        currentWordState = new StringBuilder("*".repeat(word.length()));
        attempt = 6;
        enteredLetters = new StringBuilder();
        String letter;
        hangman.setCurrentStep(HangmanStep.STEP_START);

        System.out.println("Word is " + currentWordState);

        while (isGameOver()) {

            if (currentWordState.indexOf("*") == -1) {
                System.out.println("Congratulations! You won\n");
                return;
            }

            System.out.println("Attempts left: " + attempt);
            hangman.printHangman();
            System.out.println("Enter a letter: ");

            letter = scanner.nextLine();
            checkLetter(letter);
            enteredLetters.append(letter);
        }

        hangman.printHangman();
        System.out.println("Unfortunately you lost, try again \nWord is " + getWord() + "\n");
    }

    private void exitGame() {
        System.out.println("Exit the game");
    }

    private void checkLetter(String letter) {

        if (!letter.matches("[а-яё]") || letter.isEmpty()) {
            System.out.println("Please enter a lowercase Russian letter");
            return;
        }

        if (enteredLetters.indexOf(letter) != -1) {
            printCurrentWordState();
            System.out.println("The letter \"" + letter + "\" has already been entered, please enter another letter");
            return;
        }

        if (word.contains(letter)) {
            printWord(letter);
        } else {
            System.out.println("There is no such letter");
            printCurrentWordState();

            attempt--;
            hangman.updateHangmanState(attempt);
        }
    }

    private void printWord(String letter) {
        StringBuilder previousWordState = new StringBuilder(currentWordState);
        currentWordState = new StringBuilder();

        for (int i = 0; i < word.length(); i++) {

            if (word.charAt(i) == letter.charAt(0)) {
                currentWordState.append(word.charAt(i));
            } else {
                currentWordState.append(previousWordState.charAt(i));
            }
        }

        printCurrentWordState();
    }

    private void printCurrentWordState() {
        System.out.println(currentWordState);
    }

    private boolean isGameOver() {
        return attempt > 0;
    }

}
