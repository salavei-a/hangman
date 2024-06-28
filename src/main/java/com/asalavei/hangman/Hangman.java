package com.asalavei.hangman;

public class Hangman {
    private int step;

    public int getStep() {
        return step;
    }

    public void increaseStep() {
        step++;
    }

    public void refreshStep() {
        step = 0;
    }
}
