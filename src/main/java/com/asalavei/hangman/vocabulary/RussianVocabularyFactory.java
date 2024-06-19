package com.asalavei.hangman.vocabulary;

public class RussianVocabularyFactory implements VocabularyFactory {

    @Override
    public Vocabulary createVocabulary() {
        return RussianVocabulary.getInstance();
    }

}