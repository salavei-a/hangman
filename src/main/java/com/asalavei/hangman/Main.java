package com.asalavei.hangman;

import com.asalavei.hangman.vocabulary.VocabularyLanguage;

public class Main {
    public static void main(String[] args) {
        Game game = new GameFactory().createGame(VocabularyLanguage.RUSSIAN);
        game.startGame();
    }
}