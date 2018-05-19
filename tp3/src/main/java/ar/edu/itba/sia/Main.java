package ar.edu.itba.sia;

import ar.edu.itba.sia.utils.Parser;

public class Main
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Parser parser = new Parser("configFile.txt");
        System.out.println(parser.getFirstSelectionMethod());
        System.out.println(parser.getSecondSelectionMethod());
        System.out.println(parser.getCrossingMethod());
        System.out.println(parser.getMutationMethod());
        System.out.println(parser.getFirstReplacementMethod());
        System.out.println(parser.getSecondReplacementMethod());
        System.out.println(parser.getSelectionPercent());
        System.out.println(parser.getReplacementPercent());
        System.out.println(parser.getMutationProb());
        System.out.println(parser.getCrossingProb());
        System.out.println(parser.getPopulation());
        System.out.println(parser.getSelectionCant());
        System.out.println(parser.getTemp());
        System.out.println(parser.getTournamentCantCompetitors());
        System.out.println(parser.isTournamentProb());
        System.out.println(parser.getTournamentProb());

    }
}
