package com.asalavei.hangman.vocabulary;

import java.util.EnumMap;
import java.util.Map;

public class VocabularyFactory {
    private static final Map<VocabularyLanguage, Vocabulary> registry = new EnumMap<>(VocabularyLanguage.class);

    static {
        registry.put(VocabularyLanguage.RUSSIAN, RussianVocabulary.getInstance());
        registry.put(VocabularyLanguage.ENGLISH, EnglishVocabulary.getInstance());
    }

    private VocabularyFactory() {
    }

    public static Vocabulary createVocabulary(VocabularyLanguage vocabularyLanguage) {
        return registry.get(vocabularyLanguage);
    }
}