package net.example;

import com.example.union.Either;
import com.example.union.Either.Left;
import com.example.union.Either.Right;

public class Main {

    public static Either<Double, String> safeDiv(double top, double bottom) {
        return bottom != 0.0
                ? new Left<>(top / bottom)
                : new Right<>("NaN");
    }

    public static void main(String[] args) {
        String message = safeDiv(10.0, 0.0).<String>switchBuilder()
                .onLeft(left -> String.format("%.02f", left.getItem()))
                .onRight(right -> String.format("ERROR: %s", right.getItem()))
                .apply();

        System.out.println(message);
    }
}