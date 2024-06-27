package com.asalavei.hangman;

public interface Hangman {
    void setHangmanImage(HangmanImage currentImage);

    void print();

    void updateImage(int mistakes);
}
