package com.asalavei.hangman;

public class Hangman {

    private static final Hangman INSTANCE = new Hangman();
    private HangmanStep currentStep;

    private Hangman() {
    }

    public void setCurrentStep(HangmanStep currentStep) {
        this.currentStep = currentStep;
    }

    public void printHangman() {
        System.out.println(currentStep.getStep());
    }

    public static Hangman getInstance() {
        return INSTANCE;
    }
}
