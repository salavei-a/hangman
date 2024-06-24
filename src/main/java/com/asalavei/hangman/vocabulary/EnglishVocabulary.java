package com.asalavei.hangman.vocabulary;

public class EnglishVocabulary extends Vocabulary {
    private static final EnglishVocabulary INSTANCE = new EnglishVocabulary();

    private EnglishVocabulary() {
        super("/vocabulary_english.txt");
    }

    protected static EnglishVocabulary getInstance() {
        return INSTANCE;
    }
}