package ar.edu.itba.sia;


import ar.edu.itba.sia.core.GeneticAlgorithm;
import ar.edu.itba.sia.core.RandomSeeded;
import ar.edu.itba.sia.model.character.Character;
import ar.edu.itba.sia.model.character.warrior.Warrior3;
import ar.edu.itba.sia.utils.Parser;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Main
{

    public static void main( String[] args )  {
        GeneticAlgorithm g = new GeneticAlgorithm(new Parser("configFile.txt"));
        g.geneticAlgorithm();


    }

}
