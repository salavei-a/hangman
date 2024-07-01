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

            String answer;
            do {
                System.out.println("Want to play another game? [Y/N]");
                System.out.println("Play with another vocabulary language: [L]");

                answer = scanner.nextLine();
            } while (!answer.equalsIgnoreCase(PLAY) && !answer.equalsIgnoreCase(PLAY_WITH_DIF_VOCABULARY) && !answer.equalsIgnoreCase(EXIT));

            if (answer.equalsIgnoreCase(PLAY_WITH_DIF_VOCABULARY)) {
                vocabulary = VocabularyLanguageSelector.change(vocabulary);
                answer = PLAY;
            }

            isPlayAgain = answer.equalsIgnoreCase(PLAY);

        } while (isPlayAgain);

        scanner.close();
    }

    private static final String PLAY = "Y";
    private static final String EXIT = "N";
    private static final String PLAY_WITH_DIF_VOCABULARY = "L";
}