package com.asalavei.hangman.vocabulary;

public class VocabularyFactory implements IVocabularyFactory {
    @Override
    public Vocabulary createVocabulary(VocabularyLanguage vocabularyLanguage) {
        return switch (vocabularyLanguage) {
            case RUSSIAN -> RussianVocabulary.getInstance();
            case ENGLISH -> EnglishVocabulary.getInstance();
        };
    }
}