package com.asalavei.hangman;

public class Hangman {

    private HangmanStep currentStep;

    public Hangman(HangmanStep currentStep) {
        this.currentStep = currentStep;
    }

    public void printHangman() {
        System.out.println(currentStep.getStep());
    }
}
