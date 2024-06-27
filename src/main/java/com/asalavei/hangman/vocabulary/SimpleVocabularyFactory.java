package com.asalavei.hangman.vocabulary;

public class SimpleVocabularyFactory implements VocabularyFactory {
    @Override
    public Vocabulary createVocabulary(VocabularyLanguage vocabularyLanguage) {
        return switch (vocabularyLanguage) {
            case RUSSIAN -> RussianVocabulary.getInstance();
            case ENGLISH -> EnglishVocabulary.getInstance();
            default -> throw new IllegalArgumentException("Unsupported vocabulary language: " + vocabularyLanguage);
        };
    }
}