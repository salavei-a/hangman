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
    private Vocabulary vocabulary;
    private MaskedWord word;
    private Set<String> enteredLetters;

    public Game(Vocabulary vocabulary, Hangman hangman, Scanner scanner) {
        this.vocabulary = vocabulary;
        this.hangman = hangman;
        this.scanner = scanner;
    }

    public void start() {
        play();
        String input;

        do {
            System.out.println("Want to play another game? [Y/N]");
            System.out.println("Play with another vocabulary language: [L]");

            input = scanner.nextLine();

            if (input.equalsIgnoreCase(COMMAND_PLAY_GAME)) {
                play();

            } else if (input.equalsIgnoreCase(COMMAND_PLAY_DIF_VOCABULARY_LANG)) {
                vocabulary = VocabularyLanguageSelector.change(vocabulary);
                play();
            } else if (input.equalsIgnoreCase(COMMAND_EXIT)) {
                exit();
                return;
            }

        } while (!input.equalsIgnoreCase(COMMAND_EXIT));
    }

    private void play() {
        HangmanRender hangmanRender = new DefaultHangmanRender();

        setup();
        printMask();

        while (isGameActive()) {
            if (word.matches()) {
                System.out.println("Congratulations! You won\n");
                return;
            }

            printMistakes();
            hangmanRender.print(hangman.getStep());

            String letter = inputLetterChecker();

            if (word.containsLetter(letter)) {
                word.updateMask(letter);
                printMask();
            } else {
                System.out.println("There is no such letter");
                printMask();
                hangman.increaseStep();
            }
        }

        hangmanRender.print(hangman.getStep());
        System.out.println("Unfortunately you lost, try again \nWord is " + word.getSecretWord() + "\n");
    }

    private void setup() {
        word = new MaskedWord(vocabulary.getNextWord());
        enteredLetters = new HashSet<>();
        hangman.refreshStep();
    }

    private String inputLetterChecker() {
        while (true) {
            System.out.println("Enter a letter: ");
            String letter = scanner.nextLine();

            if (isCorrectLetter(letter)) {
                System.out.println("Please enter a lowercase " + vocabulary.getLanguage().getName() + " letter");
                continue;
            }

            if (!enteredLetters.add(letter)) {
                System.out.println("The letter \"" + letter + "\" has already been entered, please enter another letter");
                continue;
            }

            return letter;
        }
    }

    public void printMask() {
        System.out.println("Word is: " + word.getMask());
    }

    private boolean isCorrectLetter(String letter) {
        return !letter.matches(vocabulary.getLanguage().getRegex());
    }

    private void printMistakes() {
        System.out.println("Number of mistakes: " + hangman.getStep());
    }

    private boolean isGameActive() {
        return hangman.getStep() != 6;
    }

    private void exit() {
        System.out.println("Exit the game");
    }
}