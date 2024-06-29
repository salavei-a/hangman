package com.asalavei.hangman;

import com.asalavei.hangman.vocabulary.*;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Game {
    private static final String COMMAND_PLAY_GAME = "Y";
    private static final String COMMAND_PLAY_DIF_VOCABULARY_LANG = "L";
    private static final String COMMAND_EXIT = "N";

    private final Scanner scanner;
    private final Hangman hangman;
    private final VocabularyFactory vocabularyFactory;
    private VocabularyLanguage vocabularyLanguage;
    private Vocabulary vocabulary;
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
        playGame();
        String input;

        do {
            System.out.println("Want to play another game? [Y/N]");
            System.out.println("Play with another vocabulary language: [L]");

            input = scanner.nextLine();

            if (input.equalsIgnoreCase(COMMAND_PLAY_GAME)) {
                playGame();

            } else if (input.equalsIgnoreCase(COMMAND_PLAY_DIF_VOCABULARY_LANG)) {
                vocabulary = VocabularyLanguageSelector.change(vocabularyLanguage, vocabularyFactory);
                playGame();
            } else if (input.equalsIgnoreCase(COMMAND_EXIT)) {
                exitGame();
                return;
            }

        } while (!input.equalsIgnoreCase(COMMAND_EXIT));
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