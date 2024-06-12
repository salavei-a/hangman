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

    public void updateHangmanState(int attempt) {
        switch (attempt) {
            case 5:
                setCurrentStep(HangmanStep.STEP_ONE);
                break;
            case 4:
                setCurrentStep(HangmanStep.STEP_TWO);
                break;
            case 3:
                setCurrentStep(HangmanStep.STEP_THREE);
                break;
            case 2:
                setCurrentStep(HangmanStep.STEP_FOUR);
                break;
            case 1:
                setCurrentStep(HangmanStep.STEP_FIVE);
                break;
            case 0:
                setCurrentStep(HangmanStep.STEP_SIX);
                break;
        }
    }
}
