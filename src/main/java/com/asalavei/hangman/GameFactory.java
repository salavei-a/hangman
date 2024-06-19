package com.asalavei.hangman;

import com.asalavei.hangman.vocabulary.VocabularyLanguage;
import com.asalavei.hangman.vocabulary.VocabularyFactory;

public class GameFactory {

    public Game createGame(VocabularyFactory vocabularyFactory, VocabularyLanguage vocabularyLanguage) {
        return new Game(vocabularyFactory, new Hangman(), vocabularyLanguage);
    }
}
