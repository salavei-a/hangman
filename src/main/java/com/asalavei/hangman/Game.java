package com.asalavei.hangman;

import com.asalavei.hangman.hangman.DefaultHangmanRender;
import com.asalavei.hangman.hangman.Hangman;
import com.asalavei.hangman.hangman.HangmanRender;
import com.asalavei.hangman.vocabulary.*;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Game {
    private final Vocabulary vocabulary;
    private final MaskedWord word;

    public Game(Vocabulary vocabulary) {
        this.vocabulary = vocabulary;
        this.word = new MaskedWord(vocabulary.getNextWord());
    }

    void runLoop(Scanner scanner) {
        Hangman hangman = new Hangman();
        HangmanRender hangmanRender = new DefaultHangmanRender();
        Set<String> enteredLetters = new HashSet<>();

        printMask();

        while (isGameActive(hangman)) {
            if (word.matches()) {
                System.out.println("Congratulations! You won\n");
                return;
            }

            printMistakes(hangman);
            hangmanRender.print(hangman.getStep());

            String letter = inputLetterChecker(enteredLetters, scanner);

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

    private String inputLetterChecker(Set<String> enteredLetters, Scanner scanner) {
        while (true) {
            System.out.println("Enter a letter: ");
            String letter = scanner.nextLine();

            if (!isCorrectLetter(letter)) {
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

    private void printMask() {
        System.out.println("Word is: " + word.getMask());
    }

    private boolean isCorrectLetter(String letter) {
        return letter.matches(vocabulary.getLanguage().getRegex());
    }

    private void printMistakes(Hangman hangman) {
        System.out.println("Number of mistakes: " + hangman.getStep());
    }

    private boolean isGameActive(Hangman hangman) {
        return hangman.getStep() != 6;
    }
}