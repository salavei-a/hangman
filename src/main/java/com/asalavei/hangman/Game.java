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
    private MaskedWord word;
    private Set<String> enteredLetters;

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
        HangmanRender hangmanRender = new DefaultHangmanRender();

        setupNewGame();
        word.printMask();

        while (isGameActive()) {
            if (word.matches()) {
                System.out.println("Congratulations! You won\n");
                return;
            }

            printMistakes();
            hangmanRender.print(hangman.getStep());
            System.out.println("Enter a letter: ");

            inputLetterChecker(scanner.nextLine());
        }

        hangmanRender.print(hangman.getStep());
        System.out.println("Unfortunately you lost, try again \nWord is " + word.getSecretWord() + "\n");
    }

    private void setupNewGame() {
        word = new MaskedWord(vocabulary.getNextWord());
        enteredLetters = new HashSet<>();
        hangman.refreshStep();
    }


    private void inputLetterChecker(String letter) {
        if (isCorrectLetter(letter)) {
            System.out.println("Please enter a lowercase " + vocabularyLanguage.getName() + " letter");
            return;
        }

        if (!enteredLetters.add(letter)) {
            word.printMask();
            System.out.println("The letter \"" + letter + "\" has already been entered, please enter another letter");
            return;
        }

        if (word.containsLetter(letter)) {
            word.updateMask(letter);
            word.printMask();
        } else {
            System.out.println("There is no such letter");
            word.printMask();
            hangman.increaseStep();
        }
    }

    private boolean isCorrectLetter(String letter) {
        return !letter.matches(vocabularyLanguage.getRegex());
    }

    private void printMistakes() {
        System.out.println("Number of mistakes: " + hangman.getStep());
    }

    private boolean isGameActive() {
        return hangman.getStep() != 6;
    }

    private void exitGame() {
        System.out.println("Exit the game");
    }
}