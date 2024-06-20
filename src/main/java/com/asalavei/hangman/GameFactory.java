package com.asalavei.hangman;

import com.asalavei.hangman.vocabulary.UniversalVocabularyFactory;
import com.asalavei.hangman.vocabulary.VocabularyLanguage;

import java.util.Scanner;

public class GameFactory {

    public Game createGame(VocabularyLanguage vocabularyLanguage, Scanner scanner) {
        return new Game(new UniversalVocabularyFactory(), new Hangman(), vocabularyLanguage, scanner);
    }

}
