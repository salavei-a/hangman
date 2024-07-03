package com.asalavei.hangman.vocabulary;

public enum VocabularyLanguage {
    RUSSIAN("[а-яё]", "Russian", "/vocabulary_russian.txt"),
    ENGLISH("[a-z]", "English", "/vocabulary_english.txt");

    private final String regex;
    private final String name;
    private final String path;

    VocabularyLanguage(String regex, String name, String path) {
        this.regex = regex;
        this.name = name;
        this.path = path;
    }

    public String getRegex() {
        return regex;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }
}