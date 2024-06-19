package com.asalavei.hangman.vocabulary;

public enum Language {
    RUSSIAN("[а-яё]", "Russian"), ENGLISH("[a-z]", "English");

    private final String regex;
    private final String name;

    Language(String regex, String name) {
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