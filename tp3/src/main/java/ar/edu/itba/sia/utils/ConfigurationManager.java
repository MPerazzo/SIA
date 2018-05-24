package ar.edu.itba.sia.utils;

import ar.edu.itba.sia.interfaces.MutationProbCalculator;
import ar.edu.itba.sia.model.MutationNoUniformProb;
import ar.edu.itba.sia.model.MutationUniformProb;
import ar.edu.itba.sia.model.character.Character;
import ar.edu.itba.sia.model.equipment.*;
import ar.edu.itba.sia.utils.enums.*;

import java.util.List;

public class ConfigurationManager {

    public static final double HEIGHT_START = 1.3;
    public static final double HEIGHT_END = 2.0;

    private final Parser parser;

    private final MutationProbCalculator mutationProbCalculator;

    public ConfigurationManager(final Parser parser) {

        this.parser = parser;

        if (parser.getMutationType().equals(MutationType.UNIFORM))
            mutationProbCalculator = new MutationUniformProb(parser.getMutationProb());
        else
            mutationProbCalculator = new MutationNoUniformProb(parser.getMutationProb());
    }

    public int getSelectionCant() {
        return parser.getSelectionCant();
    }

    public double getTemp() {
        return parser.getTemp();
    }

    public double getSelectionPercent() {
        return parser.getSelectionPercent();
    }

    public double getReplacementPercent() {
        return parser.getReplacementPercent();
    }

    public List<Character> getInitialGeneration() {
        return parser.getInitialGeneration();
    }

    public int getPopulationCant() {
        return parser.getPopulationCant();
    }

    public SelectionMethod getSelectionMethodA() {
        return parser.getSelectionMethodA();
    }

    public SelectionMethod getSelectionMethodB() {
        return parser.getSelectionMethodB();
    }

    public CrossingMethod getCrossMethod() { return parser.getCrossingMethod(); }

    public MutationMethod getMutationMethod() { return parser.getMutationMethod(); }

    public ReplacementMethod getReplacementMethod() { return  parser.getReplacementMethod(); }

    public SelectionMethod getReplacementSelectionMethodA() { return parser.getReplacementSelectionMethodA(); }

    public SelectionMethod getReplacementSelectionMethodB() { return parser.getReplacementSelectionMethodB(); }

    public double getExponentialFactor() {
        return parser.getExponentialFactor();
    }

    public int getTournamentCantCompetitors() {
        return parser.getTournamentCantCompetitors();
    }

    public double getTournamentProb() {
        return parser.getTournamentProb();
    }

    public double getCrossingProb() {
        return parser.getCrossingProb();
    }

    public double getMutationProb() {
        return mutationProbCalculator.getProb();
    }

    public List<Armor> getArmors() {
        return parser.getArmors();
    }

    public List<Boots> getBoots() {
        return parser.getBoots();
    }

    public List<Helmet> getHelmets() {
        return parser.getHelmets();
    }

    public List<Gloves> getGloves() {
        return parser.getGloves();
    }

    public List<Weapon> getWeapons() {
        return parser.getWeapons();
    }
}
