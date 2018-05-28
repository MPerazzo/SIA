package ar.edu.itba.sia.core;

import java.util.Random;

public class RandomSeeded {

    private final Random random;

    public RandomSeeded(long seed) {
        this.random = new Random(seed);
    }

    public int nextInt(int from, int to) {
        return random.nextInt(to - from) + from;
    }

    public double nextDouble() {
        return random.nextDouble();
    }

    public double nextDouble(double from, double to) {
        return from + (to - from) * random.nextDouble();
    }

    public Random getRandom() {
        return random;
    }
}
