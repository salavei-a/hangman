package com.asalavei.hangman.vocabulary;

public class RussianVocabulary extends Vocabulary {

    private static final RussianVocabulary INSTANCE = new RussianVocabulary();

    private RussianVocabulary() {
        super("/vocabulary_russian.txt");
    }

    protected static RussianVocabulary getInstance() {
        return INSTANCE;
    }

}
