package com.asalavei.hangman.vocabulary;

import java.util.Scanner;

public class VocabularyLanguageSelector {

    private VocabularyLanguageSelector() {
    }

    public static VocabularyLanguage select(Scanner scanner) {
        System.out.println("Select vocabulary language:");

        for (VocabularyLanguage language : VocabularyLanguage.values()) {
            System.out.println(language.ordinal() + 1 + ". " + language.getName());
        }

        while (true) {
            String input = scanner.nextLine();

            if (!input.matches("\\d")) {
                System.out.println("Please enter a number!");
                continue;
            }

            Integer vocabularyLanguage = Integer.parseInt(input);

            if (vocabularyLanguage > 0 && vocabularyLanguage <= VocabularyLanguage.values().length) {
                return VocabularyLanguage.values()[vocabularyLanguage - 1];
            } else {
                System.out.println("Please enter the correct language number!");
            }
        }
    }

    public static Vocabulary change(Vocabulary vocabulary) {
        VocabularyLanguage vocabularyLanguage = vocabulary.getLanguage();

        vocabularyLanguage = (vocabularyLanguage == VocabularyLanguage.RUSSIAN)
                ? VocabularyLanguage.ENGLISH
                : VocabularyLanguage.RUSSIAN;

        System.out.println("Selected " + vocabularyLanguage.getName() + " vocabulary language.");

        return VocabularyFactory.createVocabulary(vocabularyLanguage);
    }
}
