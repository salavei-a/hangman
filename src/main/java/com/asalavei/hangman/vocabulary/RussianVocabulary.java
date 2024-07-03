package com.asalavei.hangman.vocabulary;

public final class RussianVocabulary extends SimpleVocabulary {
    private static final RussianVocabulary INSTANCE = new RussianVocabulary(VocabularyLanguage.RUSSIAN);

    private RussianVocabulary(VocabularyLanguage language) {
        super(language);
    }

    static RussianVocabulary getInstance() {
        return INSTANCE;
    }
}
