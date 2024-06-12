package com.asalavei.hangman;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Vocabulary {

    private static final Vocabulary INSTANCE = new Vocabulary();

    private final List<String> vocabularyList;
    private final Random random;
    private String word;

    private Vocabulary() {
        this.vocabularyList = loadVocabulary();
        this.random = new Random();
    }

    public static Vocabulary getInstance() {
        return INSTANCE;
    }

    public String getNextWord() {
        chooseNewWord();

        return word;
    }

    private void chooseNewWord() {
        word = vocabularyList.get(random.nextInt(vocabularyList.size()));
    }

    private List<String> loadVocabulary() {
        List<String> list = new ArrayList<>();
        try (InputStream inputStream = Vocabulary.class.getResourceAsStream("/vocabulary.txt");
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
}
