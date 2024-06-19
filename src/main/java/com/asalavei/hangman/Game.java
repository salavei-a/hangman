package com.asalavei.hangman;

import com.asalavei.hangman.vocabulary.Vocabulary;
import com.asalavei.hangman.vocabulary.VocabularyFactory;

import java.util.Scanner;

public class Game {

    private static final Scanner SCANNER = new Scanner(System.in);

    private final Hangman hangman;
    private final Vocabulary vocabulary;
    private String word;
    private int attempt;
    private StringBuilder currentWordState;
    private StringBuilder enteredLetters;

    public Game(VocabularyFactory vocabularyFactory) {
        this.vocabulary = vocabularyFactory.createVocabulary();
        this.hangman = Hangman.getInstance();
    }

    public void startGame() {
        String button;

        do {
            System.out.println("Press to start a [N]ew game or e[X]it:");
            button = SCANNER.nextLine();

            if (button.equalsIgnoreCase("N")) {
                playGame();

            } else if (button.equalsIgnoreCase("X")) {
                exitGame();
                return;
            }

        } while (!button.equalsIgnoreCase("X"));
    }

    private void playGame() {
        initializeGame();
        printCurrentWordState();

        while (isGameOver()) {

            if (currentWordState.indexOf("*") == -1) {
                System.out.println("Congratulations! You won\n");
                return;
            }

            System.out.println("Attempts left: " + attempt);
            hangman.printHangman();
            System.out.println("Enter a letter: ");

            checkLetter(SCANNER.nextLine());
        }

        hangman.printHangman();
        System.out.println("Unfortunately you lost, try again \nWord is " + word + "\n");
    }

    private void initializeGame() {
        word = vocabulary.getNextWord();
        currentWordState = new StringBuilder("*".repeat(word.length()));
        enteredLetters = new StringBuilder();
        attempt = 6;
        hangman.setCurrentStep(HangmanStep.STEP_START);
    }

    private void checkLetter(String letter) {

        if (!letter.matches("[а-яё]")) {
            System.out.println("Please enter a lowercase Russian letter");
            return;
        }

        if (enteredLetters.indexOf(letter) != -1) {
            printCurrentWordState();
            System.out.println("The letter \"" + letter + "\" has already been entered, please enter another letter");

            if (word.contains(letter)) {
                attempt--;
            }

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

        enteredLetters.append(letter);
    }

    private void printWord(String letter) {
        for (int i = 0; i < word.length(); i++) {

            if (word.charAt(i) == letter.charAt(0)) {
                currentWordState.setCharAt(i, word.charAt(i));
            }
        }

        printCurrentWordState();
    }

    private void printCurrentWordState() {
        System.out.println("Word is: " + currentWordState);
    }

    private boolean isGameOver() {
        return attempt > 0;
    }

    private void exitGame() {
        System.out.println("Exit the game");
        SCANNER.close();
        System.exit(0);
    }

}
