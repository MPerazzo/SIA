package ar.edu.itba.sia;


import ar.edu.itba.sia.core.GeneticAlgorithm;
import ar.edu.itba.sia.core.Raid;
import ar.edu.itba.sia.core.RandomSeeded;
import ar.edu.itba.sia.model.character.Character;
import ar.edu.itba.sia.model.character.warrior.Warrior3;
import ar.edu.itba.sia.utils.Parser;

import javax.management.AttributeNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;

public class Main
{

    public static void main( String[] args )  {
//        GeneticAlgorithm g = new GeneticAlgorithm(new Parser("configFile.txt"));
//        g.geneticAlgorithm();

        try {
            Raid raid = new Raid("fulldata/pecheras.xls", "fulldata/botas.xls",
                    "fulldata/guantes.xls", "fulldata/cascos.xls",
                    "fulldata/armas.xls");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (AttributeNotFoundException e) {
            e.printStackTrace();
        }
    }
}
