package ar.edu.itba.sia;

import ar.edu.itba.sia.model.character.Character;
import ar.edu.itba.sia.utils.Parser;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import ar.edu.itba.sia.utils.PopulationGenerator;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

public class Main
{
    public static void main( String[] args ) throws IOException {
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


        PopulationGenerator populationGenerator = new PopulationGenerator(parser.getPopulation(),"testdata/pecheras.xls","testdata/botas.xls","testdata/guantes.xls", "testdata/cascos.xls", "testdata/armas.xls");

    }
}
