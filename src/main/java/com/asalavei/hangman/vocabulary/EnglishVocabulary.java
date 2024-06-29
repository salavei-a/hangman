package com.asalavei.hangman.vocabulary;

public final class EnglishVocabulary extends SimpleVocabulary {
    private static EnglishVocabulary INSTANCE;

    private EnglishVocabulary(String filePath, VocabularyLanguage language) {
        super(filePath, language);
    }

    static EnglishVocabulary getInstance(String filePath, VocabularyLanguage language) {
        if (INSTANCE == null) {
            INSTANCE = new EnglishVocabulary(filePath, language);
        }

        return INSTANCE;
    }
}