package ar.edu.itba.sia.utils;

import ar.edu.itba.sia.model.character.Character;
import ar.edu.itba.sia.model.equipment.Equipment;

import javax.management.AttributeNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Parser {

    private static final String FIRST_SELECTION_METHOD = "FIRST_SELECTION_METHOD", SECOND_SELECTION_METHOD = "SECOND_SELECTION_METHOD",
            CROSSING_METHOD = "CROSSING_METHOD", MUTATION_METHOD = "MUTATION_METHOD", FIRST_REPLACEMENT_METHOD = "FIRST_REPLACEMENT_METHOD",
            SECOND_REPLACEMENT_METHOD = "SECOND_REPLACEMENT_METHOD";

    private static final String SELECTION_PERCENT = "SELECTION_PERCENT", REPLACEMENT_PERCENT = "REPLACEMENT_PERCENT",
            MUTATION_PROB = "MUTATION_PROB", CROSSING_PROB = "CROSSING_PROB", POPULATION_CANT = "POPULATION_CANT", SELECTION_CANT = "SELECTION_CANT", TEMP = "TEMP",
            TOURNAMENT_CANT_COMPETITORS = "TOURNAMENT_CANT_COMPETITORS", TOURNAMENT_PROB = "TOURNAMENT_PROB",
            EXPONENTIAL_FACTOR = "EXPONENTIAL_FACTOR";

    private static final String ARMOR_FILE = "ARMOR_FILE", BOOTS_FILE = "BOOTS_FILE", GLOVES_FILE = "GLOVES_FILE",
            HELMET_FILE = "HELMET_FILE", WEAPON_FILE = "WEAPON_FILE";

    private static final String VALUE_SEPARATOR = " ";

    private PopulationGenerator populationGenerator;

    private CrossingMethod crossingMethod;
    private MutationMethod mutationMethod;
    private SelectionMethod firstSelectionMethod;
    private SelectionMethod secondSelectionMethod;
    private ReplacementMethod firstReplacementMethod;
    private ReplacementMethod secondReplacementMethod;
    private double selectionPercent;
    private double replacementPercent;
    private double mutationProb;
    private double crossingProb;
    private int populationCant;
    private int selectionCant;
    private double temp;
    private double exponentialFactor;
    private int tournamentCantCompetitors;
    private double tournamentProb;

    public Parser(final String filename) {
        try {
            this.parse(filename);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AttributeNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void parse(final String filename) throws IOException, AttributeNotFoundException {
        FileReader fileReader = new FileReader(filename);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line;

        String armorFile = null;
        String bootsFile = null;
        String glovesFile = null;
        String helmetFile = null;
        String weaponFile = null;

        while((line = bufferedReader.readLine()) != null) {
            String args[] = line.split(VALUE_SEPARATOR);

            switch (args[0]) {
                case FIRST_SELECTION_METHOD:
                    this.firstSelectionMethod = SelectionMethod.getSelectionMethod(args[1]);
                    break;
                case SECOND_SELECTION_METHOD:
                    this.secondSelectionMethod = SelectionMethod.getSelectionMethod(args[1]);
                    break;
                case CROSSING_METHOD:
                    this.crossingMethod = CrossingMethod.getCrossingMethod(args[1]);
                    break;
                case MUTATION_METHOD:
                    this.mutationMethod = MutationMethod.getMutationMethod(args[1]);
                    break;
                case FIRST_REPLACEMENT_METHOD:
                    this.firstReplacementMethod = ReplacementMethod.getReplacementMethod(args[1]);
                    break;
                case SECOND_REPLACEMENT_METHOD:
                    this.secondReplacementMethod = ReplacementMethod.getReplacementMethod(args[1]);
                    break;
                case SELECTION_PERCENT:
                    this.selectionPercent = Double.parseDouble(args[1]);
                    break;
                case REPLACEMENT_PERCENT:
                    this.replacementPercent = Double.parseDouble(args[1]);
                    break;
                case MUTATION_PROB:
                    this.mutationProb = Double.parseDouble(args[1]);
                    break;
                case CROSSING_PROB:
                    this.crossingProb = Double.parseDouble(args[1]);
                    break;
                case POPULATION_CANT:
                    this.populationCant = Integer.parseInt(args[1]);
                    break;
                case SELECTION_CANT:
                    this.selectionCant = Integer.parseInt(args[1]);
                    break;
                case TEMP:
                    this.temp = Double.parseDouble(args[1]);
                    break;
                case EXPONENTIAL_FACTOR:
                    this.exponentialFactor = Double.parseDouble(args[1]);
                    break;
                case TOURNAMENT_CANT_COMPETITORS:
                    this.tournamentCantCompetitors = Integer.parseInt(args[1]);
                    break;
                case TOURNAMENT_PROB:
                    this.tournamentProb = Double.parseDouble(args[1]);
                    break;
                case ARMOR_FILE:
                    armorFile = args[1];
                    break;
                case BOOTS_FILE:
                    bootsFile = args[1];
                    break;
                case GLOVES_FILE:
                    glovesFile = args[1];
                case HELMET_FILE:
                    helmetFile = args[1];
                case WEAPON_FILE:
                    weaponFile = args[1];
            }
        }
        populationGenerator = new PopulationGenerator(populationCant, armorFile, bootsFile, glovesFile,
                helmetFile, weaponFile);
    }

    public CrossingMethod getCrossingMethod() {
        return crossingMethod;
    }

    public MutationMethod getMutationMethod() {
        return mutationMethod;
    }

    public SelectionMethod getFirstSelectionMethod() {
        return firstSelectionMethod;
    }

    public SelectionMethod getSecondSelectionMethod() {
        return secondSelectionMethod;
    }

    public ReplacementMethod getFirstReplacementMethod() {
        return firstReplacementMethod;
    }

    public ReplacementMethod getSecondReplacementMethod() {
        return secondReplacementMethod;
    }

    public double getSelectionPercent() {
        return selectionPercent;
    }

    public double getReplacementPercent() {
        return replacementPercent;
    }

    public double getMutationProb() {
        return mutationProb;
    }

    public double getCrossingProb() {
        return crossingProb;
    }

    public int getPopulationCant() {
        return populationCant;
    }

    public int getSelectionCant() {
        return selectionCant;
    }

    public double getTemp() {
        return temp;
    }

    public double getExponentialFactor() { return exponentialFactor; }

    public int getTournamentCantCompetitors() {
        return tournamentCantCompetitors;
    }

    public double getTournamentProb() {
        return tournamentProb;
    }

    public List<Character> getInitialGeneration() { return populationGenerator.getInitialGeneration(); }

    public List<Equipment> getArmors() {
        return populationGenerator.getArmors();
    }

    public List<Equipment> getBoots() {
        return populationGenerator.getBoots();
    }

    public List<Equipment> getHelmets() {
        return populationGenerator.getHelmets();
    }

    public List<Equipment> getGloves() {
        return populationGenerator.getGloves();
    }

    public List<Equipment> getWeapons() {
        return populationGenerator.getWeapons();
    }
}
