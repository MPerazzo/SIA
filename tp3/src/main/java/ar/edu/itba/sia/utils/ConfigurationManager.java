package ar.edu.itba.sia.utils;

import ar.edu.itba.sia.model.character.Character;

import java.util.List;

public class ConfigurationManager {

    private final Parser parser;

    public ConfigurationManager(final Parser parser) {
        this.parser = parser;
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

    public SelectionMethod getFirstSelectionMethod() {
        return parser.getFirstSelectionMethod();
    }

    public SelectionMethod getSecondSelectionMethod() {
        return parser.getSecondSelectionMethod();
    }

    public CrossingMethod getCrossMethod() { return parser.getCrossingMethod(); }

    public MutationMethod getMutationMethod() { return parser.getMutationMethod(); }

    public ReplacementMethod getFirstReplacementMethod() { return  parser.getFirstReplacementMethod(); }

    public ReplacementMethod getSecondReplacementMethod() { return parser.getSecondReplacementMethod(); }

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
        return parser.getMutationProb();
    }
}
