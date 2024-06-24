package com.asalavei.hangman;

import com.asalavei.hangman.vocabulary.Vocabulary;
import com.asalavei.hangman.vocabulary.VocabularyLanguage;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VocabularyLanguage vocabularyLanguage = Vocabulary.selectVocabularyLanguage(scanner);

        Game game = GameFactory.createGame(vocabularyLanguage, scanner);
        game.startGame();
    }
}