package com.asalavei.hangman;

public class MaskedWord {
    private final String secretWord;
    private StringBuilder mask;

    public MaskedWord(String secretWord) {
        this.secretWord = secretWord;
        this.mask = new StringBuilder("_".repeat(secretWord.length()));
    }

    public String getSecretWord() {
        return secretWord;
    }

    public void updateMask(String letter) {
        for (int i = 0; i < secretWord.length(); i++) {
            if (secretWord.charAt(i) == letter.charAt(0)) {
                mask.setCharAt(i, secretWord.charAt(i));
            }
        }
    }

    public void printMask() {
        System.out.println("Word is: " + mask);
    }

    public boolean containsLetter(String letter) {
        return secretWord.contains(letter);
    }

    public boolean matches() {
        return secretWord.equals(mask.toString());
    }
}
