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

import javax.swing.*;
import java.awt.*;
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
    private double minFitness;

    private List<Character> bestGen;
    private Character bestIndividual;
    private double bestMaxFitness;
    private double bestAvgFitness;

    private int bestMaxFitnessGenNumber;
    private int bestAvgFitnessGenNumber;

    private Graphics graphics;
    private final int contentFlag;
    private final int structureFlag;
    private final int optFlag;

    public GeneticAlgorithm(Parser p){
        this.m = new ConfigurationManager(p);
        this.generationsMax = p.getGenerationsMax();
        this.fitnessOpt = p.getFitnessOpt();
        this.epsilon = p.getEpsilon();

        this.contentFlag = p.getContentFlag();
        this.structureFlag = p.getStructureFlag();
        this.optFlag = p.getOptFlag();

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

        Character worstIndividual = bestGen.stream().min((c1,c2)-> {
            if(c1.getFitness() > c2.getFitness())
                return 1;
            else if(c1.getFitness() < c2.getFitness())
                return -1;
            else
                return 0;
        }).get();
        minFitness = worstIndividual.getFitness();

        bestAvgFitness = averageFitness;
        bestMaxFitness = maxFitness;

        bestAvgFitnessGenNumber = 1;
        bestMaxFitnessGenNumber = 1;

        initGraphics();


        graphics.getFitnessAverageSeries().add(0,averageFitness);
        graphics.getBestFitnessSeries().add(0,maxFitness);
        graphics.getWorstFitnessSeries().add(0,minFitness);

        showIterativeMetrics(1);
    }

    private void initGraphics() {
        JFrame frame = new JFrame(Graphics.GRAPHICS_TITLE);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.graphics = new Graphics();
        frame.getContentPane().add(this.graphics, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    public void geneticAlgorithm() {

        if (m.getReplacementMethod().equals(ReplacementMethod.FIRST)) {
            geneticAlgorithmFirst();
        }
        else
            geneticAlgorithmOthers();

        showFinalMetrics();
    }

    private void geneticAlgorithmFirst() {

        LinkedList<Character> currentGeneration = (LinkedList) m.getInitialGeneration();

        CrossAlgorithm crossAlgorithm = CrossingMethod.getCrossingAlgorithm(m.getCrossMethod(), m.getRandomSeeded());

        MutationAlgorithm mutationAlgorithm = MutationMethod.getMutationAlgorithm(m.getMutationMethod(), m);

        double crossingProb = m.getCrossingProb();

        int generationCount = 2;
        double prevAverageFitness;
        double prevMaxFitness;

        RandomSeeded r = m.getRandomSeeded();

        do {
            prevAverageFitness = averageFitness;
            prevMaxFitness = maxFitness;

            LinkedList<Character> children = (LinkedList) Crossing.randomCross(currentGeneration, crossAlgorithm, crossingProb
                    , r);

            Mutation.mutate(children, mutationAlgorithm, m.getMutationProb(), r);

            currentGeneration = children;

            calculateMetrics(currentGeneration, generationCount);

            showIterativeMetrics(generationCount);

        } while(checkGeneration(prevAverageFitness, prevMaxFitness, generationCount++));
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

        CrossAlgorithm crossAlgorithm = CrossingMethod.getCrossingAlgorithm(m.getCrossMethod(), m.getRandomSeeded());

        double crossingProb = m.getCrossingProb();

        MutationAlgorithm mutationAlgorithm = MutationMethod.getMutationAlgorithm(m.getMutationMethod(), m);

        ReplacementAlgorithm replacementAlgorithm = ReplacementMethod.getReplacementAlgorithm(replacementMethod);

        LinkedList<Character> selectedParents = new LinkedList<>();

        int generationCount = 2;
        double prevAverageFitness;
        double prevMaxFitness;

        RandomSeeded r = m.getRandomSeeded();

        do {
            prevAverageFitness = averageFitness;
            prevMaxFitness = maxFitness;

            selectedParents.addAll(selectionAlgorithmA.select(currentGeneration));
            selectedParents.addAll(selectionAlgorithmB.select(currentGeneration));

            List<Character> children = Crossing.randomCross(selectedParents, crossAlgorithm, crossingProb, r);

            selectedParents.clear();

            Mutation.mutate(children, mutationAlgorithm, m.getMutationProb(), r);

            currentGeneration = replacementAlgorithm.newGeneration(children, currentGeneration,
                    selectionAlgorithmReplacementA,
                    selectionAlgorithmReplacementB);

            calculateMetrics(currentGeneration, generationCount);

            showIterativeMetrics(generationCount);

        } while (checkGeneration(prevAverageFitness, prevMaxFitness, generationCount++));

    }

    private void calculateMetrics(List<Character> currentGeneration, int generationCount) {
        averageFitness = currentGeneration.stream().collect(Collectors.averagingDouble(c -> c.getFitness()));

        this.graphics.getFitnessAverageSeries().add(generationCount, averageFitness);

        Character currentGenBestIndividual = currentGeneration.stream().max((c1, c2) -> {
            if (c1.getFitness() > c2.getFitness())
                return 1;
            else if (c1.getFitness() < c2.getFitness())
                return -1;
            else
                return 0;
        }).get();

        maxFitness = currentGenBestIndividual.getFitness();

        this.graphics.getBestFitnessSeries().add(generationCount, maxFitness);

        Character currentGenWorstIndividual = currentGeneration.stream().min((c1, c2) -> {
            if (c1.getFitness() > c2.getFitness())
                return 1;
            else if (c1.getFitness() < c2.getFitness())
                return -1;
            else
                return 0;
        }).get();

        minFitness = currentGenWorstIndividual.getFitness();
        this.graphics.getWorstFitnessSeries().add(generationCount, minFitness);

        if (maxFitness > bestMaxFitness) {
            bestIndividual = currentGenBestIndividual;
            bestMaxFitness = maxFitness;
            bestMaxFitnessGenNumber = generationCount;
        }

        if (averageFitness > bestAvgFitness) {
            bestGen = currentGeneration;
            bestAvgFitness = averageFitness;
            bestAvgFitnessGenNumber = generationCount;
        }
    }

    private boolean checkGeneration(double prevAverageFitness, double prevMaxFitness, int currentGenerationCount) {

        if (averageFitness + epsilon < prevAverageFitness && structureFlag == 1) {
            System.out.print("\n");
            System.out.print("[Structure drop detected (Average Fitness)]");
            return false;
        }

        if (maxFitness + epsilon < prevMaxFitness && contentFlag == 1) {
            System.out.print("\n");
            System.out.print("[Content drop detected (Max fitness)]");
            return false;
        }

        if (Math.abs(maxFitness - fitnessOpt) < epsilon && optFlag == 1) {
            System.out.print("\n");
            System.out.print("[Optimum reached]");
            return false;
        }

        if (currentGenerationCount == generationsMax) {
            System.out.print("\n");
            System.out.print("[Max iterations reached]");
            return false;
        }
        return true;
    }

    private void showIterativeMetrics(int generationCount) {
        System.out.println("Generation: " + generationCount);
        System.out.println("Max Fitness: " + maxFitness);
        System.out.println("Average Fitness: " + averageFitness);
    }

    private void showFinalMetrics() {
        System.out.println("\n");
        System.out.println("Best generation average is: " + bestAvgFitness + " which was found in generation number " + bestAvgFitnessGenNumber);
        System.out.println("Best individual performance is: " + bestMaxFitness + " which was found in generation number " + bestMaxFitnessGenNumber);
        System.out.print("\n");
        System.out.println("Best individual is: \n" + bestIndividual);
    }
}
