package com.asalavei.hangman.vocabulary;

import java.util.Scanner;

public class VocabularyLanguageSelector {

    private VocabularyLanguageSelector() {
    }

    private static final int RUSSIAN_VOCABULARY = 1;
    private static final int ENGLISH_VOCABULARY = 2;

    public static VocabularyLanguage select(Scanner scanner) {
        System.out.println("Select vocabulary language (1/2): \n1. Russian\n2. English");

        while (true) {
            String input = scanner.nextLine();

            if (!input.matches("\\d")) {
                System.out.println("Please enter a number!");
                continue;
            }

            Integer vocabularyLanguage = Integer.parseInt(input);

            if (vocabularyLanguage.equals(RUSSIAN_VOCABULARY)) {
                return VocabularyLanguage.RUSSIAN;
            } else if (vocabularyLanguage.equals(ENGLISH_VOCABULARY)) {
                return VocabularyLanguage.ENGLISH;
            } else {
                System.out.println("Please enter 1 or 2!");
            }
        }
    }

    public static Vocabulary change(VocabularyLanguage vocabularyLanguage, VocabularyFactory vocabularyFactory) {
        vocabularyLanguage = (vocabularyLanguage == VocabularyLanguage.RUSSIAN)
                ? VocabularyLanguage.ENGLISH
                : VocabularyLanguage.RUSSIAN;

        System.out.println("Selected " + vocabularyLanguage.getName() + " vocabulary language.");

        return vocabularyFactory.createVocabulary(vocabularyLanguage);
    }
}
