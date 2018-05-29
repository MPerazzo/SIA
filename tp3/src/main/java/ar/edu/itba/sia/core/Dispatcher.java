package ar.edu.itba.sia.core;

import ar.edu.itba.sia.utils.Parser;
import java.util.concurrent.ExecutionException;

public class Dispatcher {

    public Dispatcher(Parser p) {
        int raidFlag = p.getRaidFlag();

        if (raidFlag == 1)
            try {
                new Raid(p);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        else
            new GeneticAlgorithm(p).geneticAlgorithm();
    }
}
