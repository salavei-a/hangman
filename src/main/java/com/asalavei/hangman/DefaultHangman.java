package com.asalavei.hangman;

public class DefaultHangman implements Hangman {
    private HangmanImage hangmanImage;

    @Override
    public void setHangmanImage(HangmanImage hangmanImage) {
        this.hangmanImage = hangmanImage;
    }

    @Override
    public void print() {
        System.out.println(hangmanImage.getImage());
    }

    @Override
    public void updateImage(int mistakes) {
        switch (mistakes) {
            case 1 -> setHangmanImage(HangmanImage.IMAGE_ONE);
            case 2 -> setHangmanImage(HangmanImage.IMAGE_TWO);
            case 3 -> setHangmanImage(HangmanImage.IMAGE_THREE);
            case 4 -> setHangmanImage(HangmanImage.IMAGE_FOUR);
            case 5 -> setHangmanImage(HangmanImage.IMAGE_FIVE);
            case 6 -> setHangmanImage(HangmanImage.IMAGE_SIX);
            default -> setHangmanImage(HangmanImage.IMAGE_START);
        }
    }
}
