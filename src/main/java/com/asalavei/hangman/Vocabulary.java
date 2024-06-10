package com.asalavei.hangman;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Vocabulary {

    private final List<String> vocabularyList;
    private final Random random;
    private String word;

    private static Vocabulary INSTANCE = new Vocabulary();

    private Vocabulary() {
        this.vocabularyList = loadVocabulary();
        this.random = new Random();
        chooseNewWord();
    }

    public String getWord() {
        return word;
    }

    public static Vocabulary getInstance() {
        return INSTANCE;
    }

    public String getNextWord() {
        chooseNewWord();

        return this.word;
    }

    private void chooseNewWord() {
        this.word = vocabularyList.get(random.nextInt(vocabularyList.size()));
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
