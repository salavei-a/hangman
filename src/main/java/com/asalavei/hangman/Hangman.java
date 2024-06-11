package com.asalavei.hangman;

public class Hangman {
    private HangmanStep currentStep;

    private Hangman() {
        this.currentStep = HangmanStep.STEP_START;
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

    public static Hangman createHangman() {
        Hangman hangman = new Hangman();

        return hangman;
    }
}
