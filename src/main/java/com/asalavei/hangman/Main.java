package com.asalavei.hangman;

import com.asalavei.hangman.vocabulary.RussianVocabularyFactory;

public class Main {
    public static void main(String[] args) {

        Game game = new Game(new RussianVocabularyFactory());
        game.startGame();
    }
}