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
    private StringBuilder maskedWord;
    private Set<String> enteredLetters;
    private int mistakes;

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
        printMaskedWord();

        while (isGameActive()) {
            if (word.equals(maskedWord.toString())) {
                System.out.println("Congratulations! You won\n");
                return;
            }

            System.out.println("Number of mistakes: " + mistakes);
            hangman.print();
            System.out.println("Enter a letter: ");

            processGuessedLetter(scanner.nextLine());
        }

        hangman.print();
        System.out.println("Unfortunately you lost, try again \nWord is " + word + "\n");
    }

    private void setupNewGame() {
        word = vocabulary.getNextWord();
        maskedWord = new StringBuilder("_".repeat(word.length()));
        enteredLetters = new HashSet<>();
        mistakes = 0;
        hangman.updateImage(mistakes);
    }

    private void printMaskedWord() {
        System.out.println("Word is: " + maskedWord);
    }

    private void processGuessedLetter(String letter) {
        if (!letter.matches(vocabularyLanguage.getRegex())) {
            System.out.println("Please enter a lowercase " + vocabularyLanguage.getName() + " letter");
            return;
        }

        if (!enteredLetters.add(letter)) {
            printMaskedWord();
            System.out.println("The letter \"" + letter + "\" has already been entered, please enter another letter");

            if (word.contains(letter)) {
                mistakes++;
            }

            return;
        }

        if (word.contains(letter)) {
            updateMaskedWord(letter);
            printMaskedWord();
        } else {
            System.out.println("There is no such letter");
            printMaskedWord();

            mistakes++;
            hangman.updateImage(mistakes);
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
        return mistakes != 6;
    }

    private void exitGame() {
        System.out.println("Exit the game");
        scanner.close();
        System.exit(0);
    }
}