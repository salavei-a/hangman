package com.asalavei.hangman;

import com.asalavei.hangman.vocabulary.*;

public class Main {
    public static void main(String[] args) {
        VocabularyFactory vocabularyFactory;
        VocabularyLanguage vocabularyLanguage;

        String selectedVocabularyLanguage = "russian";

        if ("russian".equals(selectedVocabularyLanguage)) {
            vocabularyFactory = new RussianVocabularyFactory();
            vocabularyLanguage = VocabularyLanguage.RUSSIAN;
        } else {
            vocabularyFactory = new EnglishVocabularyFactory();
            vocabularyLanguage = VocabularyLanguage.ENGLISH;
        }

        GameFactory gameFactory = new GameFactory();
        Game game = gameFactory.createGame(vocabularyFactory, vocabularyLanguage);
        game.startGame();
    }
}