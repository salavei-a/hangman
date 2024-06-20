package com.asalavei.hangman;

import com.asalavei.hangman.vocabulary.VocabularyLanguage;
import com.asalavei.hangman.vocabulary.Vocabulary;
import com.asalavei.hangman.vocabulary.VocabularyFactory;

import java.util.Scanner;

public class Game {

    private static final Scanner SCANNER = new Scanner(System.in);

    private final Hangman hangman;
    private final Vocabulary vocabulary;
    private final VocabularyLanguage vocabularyLanguage;
    private String word;
    private int attemptsLeft;
    private StringBuilder currentWord;
    private StringBuilder enteredLetters;
    private int guessedLettersCount;

    public Game(VocabularyFactory vocabularyFactory, Hangman hangman, VocabularyLanguage vocabularyLanguage) {
        this.vocabulary = vocabularyFactory.createVocabulary();
        this.hangman = hangman;
        this.vocabularyLanguage = vocabularyLanguage;
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

            processGuessedLetter(SCANNER.nextLine());
        }

        hangman.printHangman();
        System.out.println("Unfortunately you lost, try again \nWord is " + word + "\n");
    }

    private void setupNewGame() {
        word = vocabulary.getNextWord();
        currentWord = new StringBuilder("_".repeat(word.length()));
        enteredLetters = new StringBuilder();
        attemptsLeft = 6;
        guessedLettersCount = 0;
        hangman.setCurrentStep(HangmanStep.STEP_START);
    }

    private void printCurrentWordState() {
        System.out.println("Word is: " + currentWord);
    }

    private void processGuessedLetter(String letter) {
        if (!letter.matches(vocabularyLanguage.getRegex())) {
            System.out.println("Please enter a lowercase " + vocabularyLanguage.getName() + " letter");
            return;
        }

        if (enteredLetters.indexOf(letter) != -1) {
            printCurrentWordState();
            System.out.println("The letter \"" + letter + "\" has already been entered, please enter another letter");

            if (word.contains(letter)) {
                attemptsLeft--;
            }

            return;
        }

        if (word.contains(letter)) {
            updateCurrentWord(letter);
            printCurrentWordState();
        } else {
            System.out.println("There is no such letter");
            printCurrentWordState();

            attemptsLeft--;
            hangman.updateHangmanState(attemptsLeft);
        }

        enteredLetters.append(letter);
    }

    private void updateCurrentWord(String letter) {
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter.charAt(0)) {
                currentWord.setCharAt(i, word.charAt(i));
                guessedLettersCount++;
            }
        }
    }

    private boolean isGameActive() {
        return attemptsLeft > 0;
    }

    private void exitGame() {
        System.out.println("Exit the game");
        SCANNER.close();
        System.exit(0);
    }

}