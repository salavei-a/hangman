package com.asalavei.hangman;

import com.asalavei.hangman.vocabulary.*;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Game {

    private final Scanner scanner;
    private final Hangman hangman;
    private final VocabularyFactory vocabularyFactory;
    private Vocabulary vocabulary;
    private VocabularyLanguage vocabularyLanguage;
    private String word;
    private int attemptsLeft;
    private StringBuilder guessedWordState;
    private Set<String> enteredLetters;
    private int guessedLettersCount;

    public Game(VocabularyFactory vocabularyFactory, Hangman hangman, VocabularyLanguage vocabularyLanguage, Scanner scanner) {
        this.vocabularyFactory = vocabularyFactory;
        this.vocabulary = vocabularyFactory.createVocabulary(vocabularyLanguage);
        this.hangman = hangman;
        this.vocabularyLanguage = vocabularyLanguage;
        this.scanner = scanner;
    }

    public void startGame() {
        String button;

        do {
            System.out.println("Press to start a [N]ew game, [C]hange vocabulary language or e[X]it:");
            button = scanner.nextLine();

            if (button.equalsIgnoreCase("N")) {
                playGame();

            } else if (button.equalsIgnoreCase("C")) {
                changeVocabularyLanguage();
            } else if (button.equalsIgnoreCase("X")) {
                exitGame();
                return;
            }

        } while (!button.equalsIgnoreCase("X"));
    }

    private void changeVocabularyLanguage() {
        vocabularyLanguage = (vocabularyLanguage == VocabularyLanguage.RUSSIAN)
                ? VocabularyLanguage.ENGLISH
                : VocabularyLanguage.RUSSIAN;
        vocabulary = vocabularyFactory.createVocabulary(vocabularyLanguage);

        System.out.println("Selected " + vocabularyLanguage.getName() + " vocabulary language.");
    }

    private void playGame() {
        setupNewGame();
        printCurrentWordState();

        while (isGameActive()) {
            if (guessedLettersCount == word.length()) {
                System.out.println("Congratulations! You won\n");
                return;
            }

            System.out.println("Attempts left: " + attemptsLeft);
            hangman.printHangman();
            System.out.println("Enter a letter: ");

            processGuessedLetter(scanner.nextLine());
        }

        hangman.printHangman();
        System.out.println("Unfortunately you lost, try again \nWord is " + word + "\n");
    }

    private void setupNewGame() {
        word = vocabulary.getNextWord();
        guessedWordState = new StringBuilder("_".repeat(word.length()));
        enteredLetters = new HashSet<>();
        attemptsLeft = 6;
        guessedLettersCount = 0;
        hangman.setCurrentStep(HangmanStep.STEP_START);
    }

    private void printCurrentWordState() {
        System.out.println("Word is: " + guessedWordState);
    }

    private void processGuessedLetter(String letter) {
        if (!letter.matches(vocabularyLanguage.getRegex())) {
            System.out.println("Please enter a lowercase " + vocabularyLanguage.getName() + " letter");
            return;
        }

        if (!enteredLetters.add(letter)) {
            printCurrentWordState();
            System.out.println("The letter \"" + letter + "\" has already been entered, please enter another letter");

            if (word.contains(letter)) {
                attemptsLeft--;
            }

            return;
        }

        if (word.contains(letter)) {
            updateGuessedWordState(letter);
            printCurrentWordState();
        } else {
            System.out.println("There is no such letter");
            printCurrentWordState();

            attemptsLeft--;
            hangman.updateHangmanState(attemptsLeft);
        }
    }

    private void updateGuessedWordState(String letter) {
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter.charAt(0)) {
                guessedWordState.setCharAt(i, word.charAt(i));
                guessedLettersCount++;
            }
        }
    }

    private boolean isGameActive() {
        return attemptsLeft > 0;
    }

    private void exitGame() {
        System.out.println("Exit the game");
        scanner.close();
        System.exit(0);
    }

}