package com.asalavei.hangman;

import com.asalavei.hangman.vocabulary.Vocabulary;

public class GameFactory {

    private GameFactory() {
    }

    public static Game createGame(Vocabulary vocabulary) {
        return new Game(vocabulary);
    }
}
