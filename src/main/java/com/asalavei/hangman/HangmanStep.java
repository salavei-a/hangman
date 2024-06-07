package com.asalavei.hangman;

public enum HangmanStep {
    STEP_START("_____\n |  |\n |\n |\n |\n_|_"),
    STEP_ONE("_____\n |  |\n |  0\n |\n |\n_|_"),
    STEP_TWO("_____\n |  |\n |  0\n |  |\n |\n_|_"),
    STEP_THREE("_____\n |  |\n |  0\n | \\|\n |\n_|_"),
    STEP_FOUR("_____\n |  |\n |  0\n | \\|\n | /\n_|_"),
    STEP_FIVE("_____\n |  |\n |  0\n | \\|/\n | /\n_|_"),
    STEP_SIX("_____\n |  |\n |  0\n | \\|/\n | / \\\n_|_");

    private String step;

    HangmanStep(String step) {
        this.step = step;
    }

    public String getStep() {
        return step;
    }
}