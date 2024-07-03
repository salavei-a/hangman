package com.asalavei.hangman.vocabulary;

public final class RussianVocabulary extends SimpleVocabulary {
    private static RussianVocabulary INSTANCE;

    private RussianVocabulary(VocabularyLanguage language) {
        super(language);
    }

    static RussianVocabulary getInstance(VocabularyLanguage language) {
        if (INSTANCE == null) {
            INSTANCE = new RussianVocabulary(language);
        }

        return INSTANCE;
    }
}
