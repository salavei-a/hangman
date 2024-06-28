package com.asalavei.hangman;

public enum HangmanImage {
    IMAGE_START("_____\n |  |\n |\n |\n |\n_|_"),
    IMAGE_ONE("_____\n |  |\n |  0\n |\n |\n_|_"),
    IMAGE_TWO("_____\n |  |\n |  0\n |  |\n |\n_|_"),
    IMAGE_THREE("_____\n |  |\n |  0\n | \\|\n |\n_|_"),
    IMAGE_FOUR("_____\n |  |\n |  0\n | \\|\n | /\n_|_"),
    IMAGE_FIVE("_____\n |  |\n |  0\n | \\|/\n | /\n_|_"),
    IMAGE_SIX("_____\n |  |\n |  0\n | \\|/\n | / \\\n_|_");

    private final String image;

    HangmanImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return image;
    }
}