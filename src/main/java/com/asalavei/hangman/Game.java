package com.asalavei.hangman;

import com.asalavei.hangman.vocabulary.*;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Game {
    private final Scanner scanner;
    private final IHangman hangman;
    private final IVocabularyFactory vocabularyFactory;
    private Vocabulary vocabulary;
    private VocabularyLanguage vocabularyLanguage;
    private String word;
    private StringBuilder maskedWord;
    private Set<String> enteredLetters;
    private int attemptsLeft;

    public Game(IVocabularyFactory vocabularyFactory, IHangman hangman, VocabularyLanguage vocabularyLanguage, Scanner scanner) {
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
            if (word.equals(maskedWord.toString())) {
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
        maskedWord = new StringBuilder("_".repeat(word.length()));
        enteredLetters = new HashSet<>();
        attemptsLeft = 6;
        hangman.setCurrentStep(HangmanStep.STEP_START);
    }

    private void printCurrentWordState() {
        System.out.println("Word is: " + maskedWord);
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
            updateMaskedWord(letter);
            printCurrentWordState();
        } else {
            System.out.println("There is no such letter");
            printCurrentWordState();

            attemptsLeft--;
            hangman.updateHangmanState(attemptsLeft);
        }
    }

    private void updateMaskedWord(String letter) {
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter.charAt(0)) {
                maskedWord.setCharAt(i, word.charAt(i));
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