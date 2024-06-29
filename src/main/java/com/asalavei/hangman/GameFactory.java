package com.asalavei.hangman;

import com.asalavei.hangman.vocabulary.Vocabulary;
import com.asalavei.hangman.vocabulary.VocabularyFactory;
import com.asalavei.hangman.vocabulary.VocabularyLanguage;

import java.util.Scanner;

public class GameFactory {

    private GameFactory() {
    }

    public static Game createGame(VocabularyLanguage vocabularyLanguage, Scanner scanner) {
        Vocabulary vocabulary = VocabularyFactory.createVocabulary(vocabularyLanguage);

        return new Game(vocabulary, new Hangman(), scanner);
    }
}
