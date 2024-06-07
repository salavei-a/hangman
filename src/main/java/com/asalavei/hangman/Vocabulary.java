package com.asalavei.hangman;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Vocabulary {

    private List<String> vocabularyList;
    private Random random;
    private String word;


    public Vocabulary() {
        this(new Random());
    }

    public Vocabulary(Random random) {
        this.vocabularyList = loadVocabulary();
        this.random = random;
        this.word = getRandomWord();
    }

    public String getWord() {
        return word;
    }

    public List<String> loadVocabulary() {
        vocabularyList = new ArrayList<>();
        try (InputStream inputStream = Vocabulary.class.getResourceAsStream("/vocabulary.txt");
             Scanner scanner = new Scanner(inputStream)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                vocabularyList.add(line);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error reading vocabulary file.", e);
        }

        return vocabularyList;
    }

    public String getRandomWord() {
        int index = random.nextInt(vocabularyList.size());
        return vocabularyList.get(index);
    }
}
