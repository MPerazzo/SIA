package ar.edu.itba.sia.core;

import ar.edu.itba.sia.interfaces.CrossAlgorithm;
import ar.edu.itba.sia.interfaces.MutationAlgorithm;
import ar.edu.itba.sia.interfaces.ReplacementAlgorithm;
import ar.edu.itba.sia.interfaces.SelectionAlgorithm;
import ar.edu.itba.sia.model.character.Character;
import ar.edu.itba.sia.utils.*;
import ar.edu.itba.sia.utils.enums.CrossingMethod;
import ar.edu.itba.sia.utils.enums.MutationMethod;
import ar.edu.itba.sia.utils.enums.ReplacementMethod;
import ar.edu.itba.sia.utils.enums.SelectionMethod;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class GeneticAlgorithm {

    private final ConfigurationManager m;

    private final int generationsMax;
    private final double fitnessOpt;
    private final double epsilon;
    private double averageFitness;
    private double maxFitness;

    private List<Character> bestGen;
    private Character bestIndividual;
    private double bestMaxFitness;
    private double bestAvgFitness;

    public GeneticAlgorithm(Parser p){
        this.m = new ConfigurationManager(p);
        this.generationsMax = p.getGenerationsMax();
        this.fitnessOpt = p.getFitnessOpt();
        this.epsilon = p.getEpsilon();

        bestGen = p.getInitialGeneration();

        averageFitness = bestGen.stream().collect(Collectors.averagingDouble(c -> c.getFitness()));

        bestIndividual = bestGen.stream().max((c1, c2) -> {
            if (c1.getFitness() > c2.getFitness())
                return 1;
            else if (c1.getFitness() < c2.getFitness())
                return -1;
            else
                return 0;
        }).get();

        maxFitness = bestIndividual.getFitness();

        bestAvgFitness = averageFitness;
        bestMaxFitness = maxFitness;
    }

    public void geneticAlgorithm() {
        if (m.getReplacementMethod().equals(ReplacementMethod.FIRST)) {
            geneticAlgorithmFirst();
        }
        else
            geneticAlgorithmOthers();

        System.out.println("\n");
        System.out.println("Best generation average is: " + bestAvgFitness);
        System.out.println("Best individual performance is: " + bestMaxFitness);
        System.out.print("\n");
        System.out.println("Best individual is: \n" + bestIndividual);
    }

    private void geneticAlgorithmFirst() {

        LinkedList<Character> currentGeneration = (LinkedList) m.getInitialGeneration();

        CrossAlgorithm crossAlgorithm = CrossingMethod.getCrossingAlgorithm(m.getCrossMethod());

        MutationAlgorithm mutationAlgorithm = MutationMethod.getMutationAlgorithm(m.getMutationMethod(), m);

        double crossingProb = m.getCrossingProb();

        int generationCount = 1;

        do {
            LinkedList<Character> children = (LinkedList) Crossing.randomCross(currentGeneration, crossAlgorithm, crossingProb);

            Mutation.mutate(children, mutationAlgorithm, m.getMutationProb());

            currentGeneration = children;

            calculateMetrics(currentGeneration, generationCount);

        } while(checkGenerationA(generationCount++));
    }

    private void geneticAlgorithmOthers() {

        int population = m.getPopulationCant();
        List<Character> currentGeneration = m.getInitialGeneration();

        double selectionPercent = m.getSelectionPercent();
        double replacementPercent = m.getReplacementPercent();
        int selectionCant = m.getSelectionCant();
        int selectionCantA = (int) Math.floor(selectionPercent * selectionCant);
        int selectionCantB = selectionCant - selectionCantA;

        ReplacementMethod replacementMethod = m.getReplacementMethod();

        int replacementCant = 0;
        switch (replacementMethod) {
            case SECOND:
                replacementCant = population - selectionCant;
                break;
            case THIRD:
                replacementCant = population;
                break;
        }

        int replacementCantA = (int) Math.floor(replacementPercent * replacementCant);
        int replacementCantB = replacementCant - replacementCantA;

        SelectionAlgorithm selectionAlgorithmA = SelectionMethod.
                getSelectionAlgorithm(m.getSelectionMethodA(), selectionCantA, m);
        SelectionAlgorithm selectionAlgorithmB = SelectionMethod.
                getSelectionAlgorithm(m.getSelectionMethodB(), selectionCantB, m);

        SelectionAlgorithm selectionAlgorithmReplacementA = SelectionMethod.
                getSelectionAlgorithm(m.getReplacementSelectionMethodA(), replacementCantA, m);
        SelectionAlgorithm selectionAlgorithmReplacementB = SelectionMethod.
                getSelectionAlgorithm(m.getReplacementSelectionMethodB(), replacementCantB, m);

        CrossAlgorithm crossAlgorithm = CrossingMethod.getCrossingAlgorithm(m.getCrossMethod());

        double crossingProb = m.getCrossingProb();

        MutationAlgorithm mutationAlgorithm = MutationMethod.getMutationAlgorithm(m.getMutationMethod(), m);

        ReplacementAlgorithm replacementAlgorithm = ReplacementMethod.getReplacementAlgorithm(replacementMethod);

        LinkedList<Character> selectedParents = new LinkedList<>();

        int generationCount = 1;
        double prevAverageFitness;
        double prevMaxFitness;

        do {
            prevAverageFitness = averageFitness;
            prevMaxFitness = maxFitness;

            selectedParents.addAll(selectionAlgorithmA.select(currentGeneration));
            selectedParents.addAll(selectionAlgorithmB.select(currentGeneration));

            List<Character> children = Crossing.randomCross(selectedParents, crossAlgorithm, crossingProb);

            selectedParents.clear();

            Mutation.mutate(children, mutationAlgorithm, m.getMutationProb());

            List<Character> newGen = replacementAlgorithm.newGeneration(children, currentGeneration,
                    selectionAlgorithmReplacementA,
                    selectionAlgorithmReplacementB);

            currentGeneration = newGen;

            calculateMetrics(currentGeneration, generationCount);

        } while (checkGenerationB(prevAverageFitness, prevMaxFitness, generationCount++));

    }

    private void calculateMetrics(List<Character> currentGeneration, int generationCount) {
        averageFitness = currentGeneration.stream().collect(Collectors.averagingDouble(c -> c.getFitness()));

        Character currentGenBestIndividual = currentGeneration.stream().max((c1, c2) -> {
            if (c1.getFitness() > c2.getFitness())
                return 1;
            else if (c1.getFitness() < c2.getFitness())
                return -1;
            else
                return 0;
        }).get();

        maxFitness = currentGenBestIndividual.getFitness();

        System.out.println("Generation: " + generationCount);
        System.out.println("Max Fitness: " + maxFitness);
        System.out.println("Average Fitness: " + averageFitness);

        if (maxFitness > bestMaxFitness) {
            bestIndividual = currentGenBestIndividual;
            bestMaxFitness = maxFitness;
        }

        if (averageFitness > bestAvgFitness) {
            bestGen = currentGeneration;
            bestAvgFitness = averageFitness;
        }
    }

    private boolean checkGenerationA(int currentGenerationCount) {

        if (Math.abs(maxFitness - fitnessOpt) < epsilon) {
            System.out.print("\n");
            System.out.print("Optimum reached");
            return false;
        }

        if (currentGenerationCount == generationsMax) {
            System.out.print("\n");
            System.out.print("Max iterations reached");
            return false;
        }
        return true;
    }

    private boolean checkGenerationB(double prevAverageFitness, double prevMaxFitness, int currentGenerationCount) {

        if (averageFitness < prevAverageFitness) {
            System.out.print("\n");
            System.out.print("Structure failure");
            return false;
        }

        if (maxFitness < prevMaxFitness) {
            System.out.print("\n");
            System.out.print("Content failure");
            return false;
        }

        if (Math.abs(maxFitness - fitnessOpt) < epsilon) {
            System.out.print("\n");
            System.out.print("Optimum reached");
            return false;
        }

        if (currentGenerationCount == generationsMax) {
            System.out.print("\n");
            System.out.print("Max iterations reached");
            return false;
        }
        return true;
    }
}
