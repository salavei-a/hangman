package com.asalavei.hangman;

public class Hangman implements IHangman {
    private HangmanStep currentStep;

    @Override
    public void setCurrentStep(HangmanStep currentStep) {
        this.currentStep = currentStep;
    }

    @Override
    public void printHangman() {
        System.out.println(currentStep.getStep());
    }

    @Override
    public void updateHangmanState(int attempt) {
        switch (attempt) {
            case 5 -> setCurrentStep(HangmanStep.STEP_ONE);
            case 4 -> setCurrentStep(HangmanStep.STEP_TWO);
            case 3 -> setCurrentStep(HangmanStep.STEP_THREE);
            case 2 -> setCurrentStep(HangmanStep.STEP_FOUR);
            case 1 -> setCurrentStep(HangmanStep.STEP_FIVE);
            case 0 -> setCurrentStep(HangmanStep.STEP_SIX);
        }
    }
}
