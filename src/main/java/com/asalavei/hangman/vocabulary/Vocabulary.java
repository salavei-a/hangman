package com.asalavei.hangman.vocabulary;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public abstract class Vocabulary {
    private static final Random RANDOM = new Random();
    private static final int RUSSIAN_VOCABULARY = 1;
    private static final int ENGLISH_VOCABULARY = 2;

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
        printSelectVocabularyLanguage();

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

    private static void printSelectVocabularyLanguage() {
        System.out.println("Select vocabulary language (1/2): \n1. Russian\n2. English");
    }
}