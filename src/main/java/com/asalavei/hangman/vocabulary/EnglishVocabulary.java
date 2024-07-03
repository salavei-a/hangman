package com.asalavei.hangman.vocabulary;

public final class EnglishVocabulary extends SimpleVocabulary {
    private static EnglishVocabulary INSTANCE;

    private EnglishVocabulary(VocabularyLanguage language) {
        super(language);
    }

    static EnglishVocabulary getInstance(VocabularyLanguage language) {
        if (INSTANCE == null) {
            INSTANCE = new EnglishVocabulary(language);
        }

        return INSTANCE;
    }
}