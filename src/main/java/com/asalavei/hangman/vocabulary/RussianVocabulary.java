package com.asalavei.hangman.vocabulary;

public final class RussianVocabulary extends SimpleVocabulary {
    private static RussianVocabulary INSTANCE;

    private RussianVocabulary(String filePath, VocabularyLanguage language) {
        super(filePath, language);
    }

    static RussianVocabulary getInstance(String filePath, VocabularyLanguage language) {
        if (INSTANCE == null) {
            INSTANCE = new RussianVocabulary(filePath, language);
        }

        return INSTANCE;
    }
}
