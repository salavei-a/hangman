package com.asalavei.hangman;

import com.asalavei.hangman.vocabulary.SimpleVocabularyFactory;
import com.asalavei.hangman.vocabulary.VocabularyLanguage;

import java.util.Scanner;

public class GameFactory {

    private GameFactory() {
    }

    public static Game createGame(VocabularyLanguage vocabularyLanguage, Scanner scanner) {
        return new Game(new SimpleVocabularyFactory(), new Hangman(), vocabularyLanguage, scanner);
    }
}
