package com.asalavei.hangman.vocabulary;

public class EnglishVocabularyFactory implements VocabularyFactory {

    @Override
    public Vocabulary createVocabulary() {
        return EnglishVocabulary.getInstance();
    }

}