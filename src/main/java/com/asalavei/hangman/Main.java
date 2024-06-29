package com.asalavei.hangman;

import com.asalavei.hangman.vocabulary.VocabularyLanguage;
import com.asalavei.hangman.vocabulary.VocabularyLanguageSelector;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VocabularyLanguage vocabularyLanguage = VocabularyLanguageSelector.select(scanner);

        Game game = GameFactory.createGame(vocabularyLanguage, scanner);
        game.startGame();
    }
}