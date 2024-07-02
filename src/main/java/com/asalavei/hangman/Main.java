package com.asalavei.hangman;

import com.asalavei.hangman.vocabulary.Vocabulary;
import com.asalavei.hangman.vocabulary.VocabularyFactory;
import com.asalavei.hangman.vocabulary.VocabularyLanguageSelector;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Vocabulary vocabulary = VocabularyFactory.createVocabulary(VocabularyLanguageSelector.select(scanner));

        boolean isPlayAgain;

        do {
            Game game = GameFactory.createGame(vocabulary);
            game.runLoop(scanner);

            NewRoundController newRoundController = new NewRoundController(vocabulary);
            isPlayAgain = newRoundController.isNewGame(scanner);
            vocabulary = newRoundController.getVocabulary();
        } while (isPlayAgain);

        scanner.close();
    }
}