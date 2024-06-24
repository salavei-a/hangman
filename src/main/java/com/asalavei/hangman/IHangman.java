package com.asalavei.hangman;

public interface IHangman {
    void setCurrentStep(HangmanStep currentStep);

    void printHangman();

    void updateHangmanState(int attempt);
}
