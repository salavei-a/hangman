package com.asalavei.hangman;

import com.asalavei.hangman.vocabulary.Vocabulary;
import com.asalavei.hangman.vocabulary.VocabularyFactory;
import com.asalavei.hangman.vocabulary.VocabularyLanguageSelector;

import java.util.Scanner;

public class NewGameController {
    private static final String PLAY = "Y";
    private static final String EXIT = "N";
    private static final String SELECT_VOCABULARY_LANGUAGE = "L";

    private Vocabulary vocabulary;

    public NewGameController(Vocabulary vocabulary) {
        this.vocabulary = vocabulary;
    }

    public Vocabulary getVocabulary() {
        return vocabulary;
    }

    public boolean isNewGame(Scanner scanner) {
        String answer;
        do {
            System.out.println("Want to play another game? [Y/N]");
            System.out.println("Select vocabulary language: [L]");

            answer = scanner.nextLine();
        } while (!answer.equalsIgnoreCase(PLAY) && !answer.equalsIgnoreCase(SELECT_VOCABULARY_LANGUAGE) && !answer.equalsIgnoreCase(EXIT));

        if (answer.equalsIgnoreCase(SELECT_VOCABULARY_LANGUAGE)) {
            vocabulary = VocabularyFactory.createVocabulary(VocabularyLanguageSelector.select(scanner));
            answer = PLAY;
        }

        return answer.equalsIgnoreCase(PLAY);
    }
}
