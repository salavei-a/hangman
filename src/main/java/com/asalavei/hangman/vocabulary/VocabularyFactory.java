package com.asalavei.hangman.vocabulary;

public class VocabularyFactory {
    private static final String RU_SIMPLE_VOCABULARY_PATH = "/vocabulary_russian.txt";
    private static final String EN_SIMPLE_VOCABULARY_PATH = "/vocabulary_english.txt";

    private VocabularyFactory() {
    }

    public static Vocabulary createVocabulary(VocabularyLanguage vocabularyLanguage) {
        return switch (vocabularyLanguage) {
            case RUSSIAN -> RussianVocabulary.getInstance(RU_SIMPLE_VOCABULARY_PATH, VocabularyLanguage.RUSSIAN);
            case ENGLISH -> EnglishVocabulary.getInstance(EN_SIMPLE_VOCABULARY_PATH, VocabularyLanguage.ENGLISH);
            default -> throw new IllegalArgumentException("Unsupported vocabulary language: " + vocabularyLanguage);
        };
    }
}