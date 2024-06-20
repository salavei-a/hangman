package com.asalavei.hangman.vocabulary;

public enum VocabularyLanguage {
    RUSSIAN("[а-яё]", "Russian"),
    ENGLISH("[a-z]", "English");

    private final String regex;
    private final String name;

    VocabularyLanguage(String regex, String name) {
        this.regex = regex;
        this.name = name;
    }

    public String getRegex() {
        return regex;
    }

    public String getName() {
        return name;
    }

}