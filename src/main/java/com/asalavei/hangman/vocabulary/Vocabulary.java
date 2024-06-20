package com.asalavei.hangman.vocabulary;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public abstract class Vocabulary {

    private static final Random RANDOM = new Random();
    private final List<String> vocabularyList;

    protected Vocabulary(String filePath) {
        this.vocabularyList = loadVocabulary(filePath);
    }

    public String getNextWord() {
        return vocabularyList.get(RANDOM.nextInt(vocabularyList.size()));
    }

    private List<String> loadVocabulary(String filePath) {
        List<String> list = new ArrayList<>();

        try (InputStream inputStream = this.getClass().getResourceAsStream(filePath);
             Scanner scanner = new Scanner(inputStream)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                list.add(line);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error reading vocabulary file.", e);
        }

        return list;
    }

    public static VocabularyLanguage selectVocabularyLanguage(Scanner scanner) {
        VocabularyLanguage vocabularyLanguage = null;

        printSelectVocabularyLanguage();

        do {
            String button = scanner.nextLine();

            if (button.equalsIgnoreCase("R")) {
                vocabularyLanguage = VocabularyLanguage.RUSSIAN;
            } else if (button.equalsIgnoreCase("E")) {
                vocabularyLanguage = VocabularyLanguage.ENGLISH;
            } else
                printSelectVocabularyLanguage();

        } while (vocabularyLanguage == null);

        System.out.println("Selected " + vocabularyLanguage.getName() + " vocabulary language.");

        return vocabularyLanguage;
    }

    private static void printSelectVocabularyLanguage() {
        System.out.println("Select vocabulary language. Press for [R]ussian or [E]nglish language:");
    }

}