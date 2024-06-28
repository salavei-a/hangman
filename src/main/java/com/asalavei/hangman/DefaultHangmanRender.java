package com.asalavei.hangman;

public class DefaultHangmanRender implements HangmanRender {
    @Override
    public void print(int step) {
        switch (step) {
            case 0 -> System.out.println(HangmanImage.IMAGE_START);
            case 1 -> System.out.println(HangmanImage.IMAGE_ONE);
            case 2 -> System.out.println(HangmanImage.IMAGE_TWO);
            case 3 -> System.out.println(HangmanImage.IMAGE_THREE);
            case 4 -> System.out.println(HangmanImage.IMAGE_FOUR);
            case 5 -> System.out.println(HangmanImage.IMAGE_FIVE);
            case 6 -> System.out.println(HangmanImage.IMAGE_SIX);
            default -> throw new IllegalArgumentException("Unknown step: " + step);
        }
    }
}
