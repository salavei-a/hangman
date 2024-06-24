package com.asalavei.hangman;

import com.asalavei.hangman.vocabulary.VocabularyFactory;
import com.asalavei.hangman.vocabulary.VocabularyLanguage;

import java.util.Scanner;

public class GameFactory {
    public static Game createGame(VocabularyLanguage vocabularyLanguage, Scanner scanner) {
        return new Game(new VocabularyFactory(), new Hangman(), vocabularyLanguage, scanner);
    }
}
