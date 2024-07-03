package com.asalavei.hangman.vocabulary;

public final class EnglishVocabulary extends SimpleVocabulary {
    private static final EnglishVocabulary INSTANCE = new EnglishVocabulary(VocabularyLanguage.ENGLISH);

    private EnglishVocabulary(VocabularyLanguage language) {
        super(language);
    }

    static EnglishVocabulary getInstance() {
        return INSTANCE;
    }
}