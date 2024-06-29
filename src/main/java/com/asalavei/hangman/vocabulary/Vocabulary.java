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
        this.vocabularyList = load(filePath);
    }

    public String getNextWord() {
        return vocabularyList.get(RANDOM.nextInt(vocabularyList.size()));
    }

    private List<String> load(String filePath) {
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
}