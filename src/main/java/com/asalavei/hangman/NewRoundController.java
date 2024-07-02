package com.asalavei.hangman;

import com.asalavei.hangman.vocabulary.Vocabulary;
import com.asalavei.hangman.vocabulary.VocabularyLanguageSelector;

import java.util.Scanner;

public class NewRoundController {
    private static final String PLAY = "Y";
    private static final String EXIT = "N";
    private static final String PLAY_WITH_DIF_VOCABULARY = "L";

    private Vocabulary vocabulary;

    public NewRoundController(Vocabulary vocabulary) {
        this.vocabulary = vocabulary;
    }

    public Vocabulary getVocabulary() {
        return vocabulary;
    }

    protected boolean isNewGame(Scanner scanner) {
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

        return answer.equalsIgnoreCase(PLAY);
    }
}
