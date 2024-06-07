package com.asalavei.hangman;

public class Hangman {

    private HangmanStep currentStep;

    public Hangman(HangmanStep currentStep) {
        this.currentStep = currentStep;
    }

    public HangmanStep getCurrentStep() {
        return currentStep;
    }

    public void setCurrentStep(HangmanStep currentStep) {
        this.currentStep = currentStep;
    }

    public void printHangman() {
        System.out.println(currentStep.getStep());
    }
}
