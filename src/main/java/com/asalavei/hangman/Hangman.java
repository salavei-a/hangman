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
