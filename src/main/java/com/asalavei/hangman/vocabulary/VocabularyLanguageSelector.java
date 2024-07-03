package com.asalavei.hangman.vocabulary;

import java.util.Scanner;

public class VocabularyLanguageSelector {

    private VocabularyLanguageSelector() {
    }

    public static VocabularyLanguage select(Scanner scanner) {
        System.out.println("Select vocabulary language:");

        printAllVocabularyLanguages();

        while (true) {
            String input = scanner.nextLine();

            if (isNumber(input)) {
                System.out.println("Please enter a number!");
                continue;
            }

            int inputVocabularyLanguage = Integer.parseInt(input);

            if (isCorrectVocabularyLanguage(inputVocabularyLanguage)) {
                VocabularyLanguage vocabularyLanguage = VocabularyLanguage.values()[inputVocabularyLanguage - 1];
                System.out.println("Selected " + vocabularyLanguage.getName() + " vocabulary language.");

                return vocabularyLanguage;
            } else {
                System.out.println("Please enter the correct language number!");
            }
        }
    }

    private static boolean isCorrectVocabularyLanguage(int inputVocabularyLanguage) {
        return inputVocabularyLanguage > 0 && inputVocabularyLanguage <= VocabularyLanguage.values().length;
    }

    private static boolean isNumber(String input) {
        return !input.matches("\\d");
    }

    private static void printAllVocabularyLanguages() {
        for (VocabularyLanguage language : VocabularyLanguage.values()) {
            System.out.println(language.ordinal() + 1 + ". " + language.getName());
        }
    }
}
