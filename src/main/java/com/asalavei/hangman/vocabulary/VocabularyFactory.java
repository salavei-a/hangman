package com.asalavei.hangman.vocabulary;

public class VocabularyFactory {

    private VocabularyFactory() {
    }

    public static Vocabulary createVocabulary(VocabularyLanguage vocabularyLanguage) {
        return switch (vocabularyLanguage) {
            case RUSSIAN -> RussianVocabulary.getInstance(VocabularyLanguage.RUSSIAN);
            case ENGLISH -> EnglishVocabulary.getInstance(VocabularyLanguage.ENGLISH);
            default -> throw new IllegalArgumentException("Unsupported vocabulary language: " + vocabularyLanguage);
        };
    }
}