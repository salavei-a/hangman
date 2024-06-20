package com.asalavei.hangman.vocabulary;

public class UniversalVocabularyFactory implements VocabularyFactory{

    @Override
    public Vocabulary createVocabulary(VocabularyLanguage vocabularyLanguage) {
        return switch (vocabularyLanguage) {
            case RUSSIAN -> RussianVocabulary.getInstance();
            case ENGLISH -> EnglishVocabulary.getInstance();
        };
    }

}