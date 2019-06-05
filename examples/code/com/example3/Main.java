package com.example3;

public class Main {
    public static Either<Double, String> division(double top, double bottom) {
        return bottom != 0.0
                ? new Either.Left<>(top / bottom)
                : new Either.Right<>("Not a Number");
    }

    public static void main(String[] args) {
        String message = division(10.0, 20.0).<String>createSwitch()
                .onLeft(number -> String.format("%.02f", number.getItem()))
                .onRight(Either.Right::getItem)
                .apply();

        System.out.println(message);
    }
}