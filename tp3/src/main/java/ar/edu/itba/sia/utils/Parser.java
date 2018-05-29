package ar.edu.itba.sia.utils;

import ar.edu.itba.sia.core.RandomSeeded;
import ar.edu.itba.sia.model.character.Character;
import ar.edu.itba.sia.model.equipment.*;
import ar.edu.itba.sia.utils.enums.*;

import javax.management.AttributeNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Parser {

    private static final String SELECTION_METHOD_A = "SELECTION_METHOD_A", SELECTION_METHOD_B = "SELECTION_METHOD_B",
            CROSSING_METHOD = "CROSSING_METHOD", MUTATION_METHOD = "MUTATION_METHOD", MUTATION_TYPE = "MUTATION_TYPE", REPLACEMENT_METHOD = "REPLACEMENT_METHOD",
            REPLACEMENT_SELECTION_METHOD_A = "REPLACEMENT_SELECTION_METHOD_A", REPLACEMENT_SELECTION_METHOD_B = "REPLACEMENT_SELECTION_METHOD_B";

    private static final String SELECTION_PERCENT = "SELECTION_PERCENT", REPLACEMENT_PERCENT = "REPLACEMENT_PERCENT",
            MUTATION_PROB = "MUTATION_PROB", CROSSING_PROB = "CROSSING_PROB", POPULATION_CANT = "POPULATION_CANT", SELECTION_CANT = "SELECTION_CANT", TEMP = "TEMP",
            TOURNAMENT_CANT_COMPETITORS = "TOURNAMENT_CANT_COMPETITORS", TOURNAMENT_PROB = "TOURNAMENT_PROB",
            EXPONENTIAL_FACTOR = "EXPONENTIAL_FACTOR", CHARACTER_TYPE = "CHARACTER_TYPE";

    private static final String RANDOM = "RANDOM", GENERATIONS = "GENERATIONS", FITNESS_OPT = "FITNESS_OPT", EPSILON = "EPSILON", GENERATION_CHECK = "GENERATION_CHECK",
            GENERATION_INC = "GENERATION_INC", CONTENT_FLAG = "CONTENT_FLAG", STRUCTURE_FLAG = "STRUCTURE_FLAG", OPT_FLAG = "OPT_FLAG", ITERATIONS_FLAG = "ITERATIONS_FLAG",
            TIME = "TIME", TIME_FLAG = "TIME_FLAG", GRAPHIC_FLAG = "GRAPHIC_FLAG";

    private static final String ARMOR_FILE = "ARMOR_FILE", BOOTS_FILE = "BOOTS_FILE", GLOVES_FILE = "GLOVES_FILE",
            HELMET_FILE = "HELMET_FILE", WEAPON_FILE = "WEAPON_FILE";

    private static final String VALUE_SEPARATOR = " ";

    private PopulationGenerator populationGenerator;

    private SelectionMethod selectionMethodA;
    private SelectionMethod selectionMethodB;
    private CrossingMethod crossingMethod;
    private MutationMethod mutationMethod;
    private MutationType mutationType;
    private ReplacementMethod replacementMethod;
    private SelectionMethod replacementSelectionMethodA;
    private SelectionMethod replacementSelectionMethodB;
    private CharacterType characterType;
    private RandomSeeded randomSeeded;
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
    private int generationsMax;
    private double fitnessOpt;
    private double epsilon;
    private int generationCheck;
    private double generationInc;
    private int contentFlag;
    private int structureFlag;
    private int optFlag;
    private int iterationsFlag;
    private int timeFlag;
    private int graphicFlag;
    private double maxTime;


    public Parser(final String filename) {
        try {
            this.parse(filename);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AttributeNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Parser(String fileName, List<Armor> armors, List<Boots> boots, List<Gloves> gloves, List<Helmet> helmets,
                  List<Weapon> weapons) throws IOException, AttributeNotFoundException {
        parseRaid(fileName, armors, boots, gloves, helmets, weapons);

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
                case SELECTION_METHOD_A:
                    this.selectionMethodA = SelectionMethod.getSelectionMethod(args[1]);
                    break;
                case SELECTION_METHOD_B:
                    this.selectionMethodB = SelectionMethod.getSelectionMethod(args[1]);
                    break;
                case CROSSING_METHOD:
                    this.crossingMethod = CrossingMethod.getCrossingMethod(args[1]);
                    break;
                case MUTATION_METHOD:
                    this.mutationMethod = MutationMethod.getMutationMethod(args[1]);
                    break;
                case MUTATION_TYPE:
                    this.mutationType = MutationType.getMutationType(args[1]);
                    break;
                case REPLACEMENT_METHOD:
                    this.replacementMethod = ReplacementMethod.getReplacementMethod(args[1]);
                    break;
                case REPLACEMENT_SELECTION_METHOD_A:
                    this.replacementSelectionMethodA = SelectionMethod.getSelectionMethod(args[1]);
                    break;
                case REPLACEMENT_SELECTION_METHOD_B:
                    this.replacementSelectionMethodB = SelectionMethod.getSelectionMethod(args[1]);
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
                case CHARACTER_TYPE:
                    this.characterType = CharacterType.getCharacterType(args[1]);
                    break;
                case GENERATION_CHECK:
                    this.generationCheck = Integer.parseInt(args[1]);
                    break;
                case GENERATION_INC:
                    this.generationInc = Double.parseDouble(args[1])/100;
                    break;
                case RANDOM:
                    this.randomSeeded = new RandomSeeded(Long.parseLong(args[1]));
                    break;
                case CONTENT_FLAG:
                    this.contentFlag = Integer.parseInt(args[1]);
                    break;
                case STRUCTURE_FLAG:
                    this.structureFlag = Integer.parseInt(args[1]);
                    break;
                case ITERATIONS_FLAG:
                    this.iterationsFlag = Integer.parseInt(args[1]);
                    break;
                case GRAPHIC_FLAG:
                    this.graphicFlag = Integer.parseInt(args[1]);
                    break;
                case OPT_FLAG:
                    this.optFlag = Integer.parseInt(args[1]);
                    break;
                case GENERATIONS:
                    this.generationsMax = Integer.parseInt(args[1]);
                    break;
                case FITNESS_OPT:
                    this.fitnessOpt = Double.parseDouble(args[1]);
                    break;
                case EPSILON:
                    this.epsilon = Double.parseDouble(args[1]);
                    break;
                case ARMOR_FILE:
                    armorFile = args[1];
                    break;
                case BOOTS_FILE:
                    bootsFile = args[1];
                    break;
                case GLOVES_FILE:
                    glovesFile = args[1];
                    break;
                case HELMET_FILE:
                    helmetFile = args[1];
                    break;
                case WEAPON_FILE:
                    weaponFile = args[1];
                    break;
                case TIME:
                    this.maxTime = Double.parseDouble(args[1]);
                    break;
                case TIME_FLAG:
                    this.timeFlag = Integer.parseInt(args[1]);
            }
        }
        populationGenerator = new PopulationGenerator(populationCant, characterType, armorFile, bootsFile, glovesFile,
                helmetFile, weaponFile, randomSeeded);
    }

    private void parseRaid(final String filename, List<Armor> armors, List<Boots> boots, List<Gloves> gloves, List<Helmet> helmets,
                           List<Weapon> weapons) throws IOException, AttributeNotFoundException {
        FileReader fileReader = new FileReader(filename);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line;

        while((line = bufferedReader.readLine()) != null) {
            String args[] = line.split(VALUE_SEPARATOR);

            switch (args[0]) {
                case SELECTION_METHOD_A:
                    this.selectionMethodA = SelectionMethod.getSelectionMethod(args[1]);
                    break;
                case SELECTION_METHOD_B:
                    this.selectionMethodB = SelectionMethod.getSelectionMethod(args[1]);
                    break;
                case CROSSING_METHOD:
                    this.crossingMethod = CrossingMethod.getCrossingMethod(args[1]);
                    break;
                case MUTATION_METHOD:
                    this.mutationMethod = MutationMethod.getMutationMethod(args[1]);
                    break;
                case MUTATION_TYPE:
                    this.mutationType = MutationType.getMutationType(args[1]);
                    break;
                case REPLACEMENT_METHOD:
                    this.replacementMethod = ReplacementMethod.getReplacementMethod(args[1]);
                    break;
                case REPLACEMENT_SELECTION_METHOD_A:
                    this.replacementSelectionMethodA = SelectionMethod.getSelectionMethod(args[1]);
                    break;
                case REPLACEMENT_SELECTION_METHOD_B:
                    this.replacementSelectionMethodB = SelectionMethod.getSelectionMethod(args[1]);
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
                case CHARACTER_TYPE:
                    this.characterType = CharacterType.getCharacterType(args[1]);
                    break;
                case GENERATION_CHECK:
                    this.generationCheck = Integer.parseInt(args[1]);
                    break;
                case GENERATION_INC:
                    this.generationInc = Double.parseDouble(args[1])/100;
                    break;
                case RANDOM:
                    this.randomSeeded = new RandomSeeded(Long.parseLong(args[1]));
                    break;
                case CONTENT_FLAG:
                    this.contentFlag = Integer.parseInt(args[1]);
                    break;
                case STRUCTURE_FLAG:
                    this.structureFlag = Integer.parseInt(args[1]);
                    break;
                case OPT_FLAG:
                    this.optFlag = Integer.parseInt(args[1]);
                    break;
                case ITERATIONS_FLAG:
                    this.iterationsFlag = Integer.parseInt(args[1]);
                    break;
                case GRAPHIC_FLAG:
                    this.graphicFlag = Integer.parseInt(args[1]);
                    break;
                case GENERATIONS:
                    this.generationsMax = Integer.parseInt(args[1]);
                    break;
                case FITNESS_OPT:
                    this.fitnessOpt = Double.parseDouble(args[1]);
                    break;
                case EPSILON:
                    this.epsilon = Double.parseDouble(args[1]);
                    break;
                case TIME:
                    this.maxTime = Double.parseDouble(args[1]);
                    break;
                case TIME_FLAG:
                    this.timeFlag = Integer.parseInt(args[1]);
            }
        }
        populationGenerator = new PopulationGenerator(populationCant, characterType, armors, boots, gloves, helmets,
                weapons, randomSeeded);
    }


    public CrossingMethod getCrossingMethod() {
        return crossingMethod;
    }

    public MutationMethod getMutationMethod() {
        return mutationMethod;
    }

    public MutationType getMutationType() { return mutationType; }

    public SelectionMethod getSelectionMethodA() {
        return selectionMethodA;
    }

    public SelectionMethod getSelectionMethodB() {
        return selectionMethodB;
    }

    public ReplacementMethod getReplacementMethod() {
        return replacementMethod;
    }

    public SelectionMethod getReplacementSelectionMethodA() { return replacementSelectionMethodA; }

    public SelectionMethod getReplacementSelectionMethodB() { return replacementSelectionMethodB; }

    public CharacterType getCharacterType() { return this.characterType; }

    public RandomSeeded getRandomSeeded() { return randomSeeded; }

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

    public int getGenerationsMax() { return generationsMax; }

    public double getFitnessOpt() { return fitnessOpt; }

    public double getEpsilon() { return epsilon; }

    public int getGenerationCheck() { return generationCheck; }

    public double getGenerationInc() { return generationInc; }

    public int getContentFlag() { return contentFlag; }

    public int getStructureFlag() { return structureFlag; }

    public int getIterationsFlag() { return iterationsFlag; }

    public int getOptFlag() { return optFlag; }

    public int getGraphicFlag() { return graphicFlag; }

    public double getMaxTime() {
        return maxTime;
    }

    public int getTimeFlag() {
        return timeFlag;
    }

    public List<Character> getInitialGeneration() { return populationGenerator.getInitialGeneration(); }

    public List<Armor> getArmors() {
        return populationGenerator.getArmors();
    }

    public List<Boots> getBoots() {
        return populationGenerator.getBoots();
    }

    public List<Helmet> getHelmets() {
        return populationGenerator.getHelmets();
    }

    public List<Gloves> getGloves() {
        return populationGenerator.getGloves();
    }

    public List<Weapon> getWeapons() {
        return populationGenerator.getWeapons();
    }
}
