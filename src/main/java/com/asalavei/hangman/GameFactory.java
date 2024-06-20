package com.asalavei.hangman;

import com.asalavei.hangman.vocabulary.EnglishVocabularyFactory;
import com.asalavei.hangman.vocabulary.RussianVocabularyFactory;
import com.asalavei.hangman.vocabulary.VocabularyLanguage;
import com.asalavei.hangman.vocabulary.VocabularyFactory;

public class GameFactory {

    public Game createGame(VocabularyLanguage vocabularyLanguage) {
        VocabularyFactory vocabularyFactory = switch (vocabularyLanguage) {
            case RUSSIAN -> new RussianVocabularyFactory();
            case ENGLISH -> new EnglishVocabularyFactory();
        };

        return new Game(vocabularyFactory, new Hangman(), vocabularyLanguage);
    }
}
