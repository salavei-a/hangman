package com.asalavei.hangman;

import com.asalavei.hangman.vocabulary.*;

public class Main {
    public static void main(String[] args) {
        VocabularyFactory vocabularyFactory;
        Language vocabularyLanguage;

        String selectedVocabularyLanguage = "russian";

        if ("russian".equals(selectedVocabularyLanguage)) {
            vocabularyFactory = new RussianVocabularyFactory();
            vocabularyLanguage = Language.RUSSIAN;
        } else {
            vocabularyFactory = new EnglishVocabularyFactory();
            vocabularyLanguage = Language.ENGLISH;
        }

        Game game = new Game(vocabularyFactory, vocabularyLanguage);
        game.startGame();
    }
}