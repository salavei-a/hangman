package com.asalavei.hangman;

import com.asalavei.hangman.vocabulary.SimpleVocabularyFactory;
import com.asalavei.hangman.vocabulary.VocabularyLanguage;

import java.util.Scanner;

public class GameFactory {
    public static Game createGame(VocabularyLanguage vocabularyLanguage, Scanner scanner) {
        return new Game(new SimpleVocabularyFactory(), new DefaultHangman(), vocabularyLanguage, scanner);
    }
}
