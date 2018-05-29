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
    private Character prevBestIndividual;
    private Character bestIndividual;
    private double bestMaxFitness;
    private double bestAvgFitness;

    private int bestMaxFitnessGenNumber;
    private int bestAvgFitnessGenNumber;

    private final double generationCheck;
    private final double generationInc;

    private double initialTime;
    private double timeMax;

    private double checkAvgFitness;
    private double checkMaxFitness;

    private Graphics graphics;
    private final int contentFlag;
    private final int structureFlag;
    private final int optFlag;
    private final int timeFlag;
    private final int iterationsFlag;
    private final int graphicFlag;


    public GeneticAlgorithm(Parser p){
        this.m = new ConfigurationManager(p);
        this.generationsMax = p.getGenerationsMax();
        this.fitnessOpt = p.getFitnessOpt();
        this.epsilon = p.getEpsilon();
        this.timeMax = p.getMaxTime();
        this.timeFlag = p.getTimeFlag();

        this.generationCheck = p.getGenerationCheck();
        this.generationInc = p.getGenerationInc();

        this.contentFlag = p.getContentFlag();
        this.structureFlag = p.getStructureFlag();
        this.optFlag = p.getOptFlag();
        this.iterationsFlag = p.getIterationsFlag();
        this.graphicFlag = p.getGraphicFlag();

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

        minFitness = bestGen.stream().mapToDouble(c -> c.getFitness()).
                min().getAsDouble();

        bestAvgFitness = averageFitness;
        bestMaxFitness = maxFitness;

        checkAvgFitness = averageFitness;
        checkMaxFitness = maxFitness;

        bestAvgFitnessGenNumber = 1;
        bestMaxFitnessGenNumber = 1;


        if (graphicFlag == 1) {
            initGraphics(m);

            graphics.getFitnessAverageSeries().add(0, averageFitness);
            graphics.getBestFitnessSeries().add(0, maxFitness);
            graphics.getWorstFitnessSeries().add(0, minFitness);
        }

        showIterativeMetrics(1);
    }

    private void initGraphics(ConfigurationManager m) {
        JFrame frame = new JFrame(Graphics.GRAPHICS_TITLE + " - " + m.getCharacterType().toString());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.graphics = new Graphics(m.getCharacterType().toString());
        frame.getContentPane().add(this.graphics, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    public List<Character> geneticAlgorithm() {

        if (m.getReplacementMethod().equals(ReplacementMethod.FIRST)) {
            geneticAlgorithmFirst();
        }
        else
            geneticAlgorithmOthers();

        showFinalMetrics();

        return bestGen;
    }

    private void geneticAlgorithmFirst() {

        LinkedList<Character> currentGeneration = (LinkedList) m.getInitialGeneration();

        CrossAlgorithm crossAlgorithm = CrossingMethod.getCrossingAlgorithm(m.getCrossMethod(), m.getRandomSeeded());

        MutationAlgorithm mutationAlgorithm = MutationMethod.getMutationAlgorithm(m.getMutationMethod(), m);

        double crossingProb = m.getCrossingProb();

        int generationCount = 2;

        RandomSeeded r = m.getRandomSeeded();

        initialTime = System.currentTimeMillis();

        do {
            LinkedList<Character> children = (LinkedList) Crossing.randomCross(currentGeneration, crossAlgorithm, crossingProb
                    , r);

            Mutation.mutate(children, mutationAlgorithm, m.getMutationProb(), r);

            currentGeneration = children;

            calculateMetrics(currentGeneration, generationCount);

            showIterativeMetrics(generationCount);

        } while(checkGeneration(generationCount++));
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

        RandomSeeded r = m.getRandomSeeded();

        initialTime = System.currentTimeMillis();

        do {
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

        } while (checkGeneration(generationCount++));

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

        minFitness = currentGeneration.stream().mapToDouble(c -> c.getFitness()).
                min().getAsDouble();


        if (graphicFlag == 1) {
            this.graphics.getFitnessAverageSeries().add(generationCount, averageFitness);
            this.graphics.getBestFitnessSeries().add(generationCount, maxFitness);
            this.graphics.getWorstFitnessSeries().add(generationCount, minFitness);
        }

        if (maxFitness > bestMaxFitness) {
            prevBestIndividual = bestIndividual.newSon(bestIndividual.getHeight(),
                    bestIndividual.getEquipment());
            bestIndividual = currentGenBestIndividual.newSon(currentGenBestIndividual.getHeight(),
                    currentGenBestIndividual.getEquipment());
            bestMaxFitness = maxFitness;
            bestMaxFitnessGenNumber = generationCount;
        }

        if (averageFitness > bestAvgFitness) {
            bestGen.clear();

            for (Character c : currentGeneration)
                bestGen.add(c.newSon(c.getHeight(), c.getEquipment()));

            bestAvgFitness = averageFitness;
            bestAvgFitnessGenNumber = generationCount;
        }
    }

    private boolean checkGeneration(int generationCount) {

        if (structureFlag == 1 && generationCount % generationCheck == 0) {

            if (averageFitness <= checkAvgFitness * (1 + generationInc)) {
                System.out.print("\n");
                System.out.print("[Structure drop detected (Average Fitness)]");
                return false;
            }
            else
                checkAvgFitness = averageFitness;
        }

        if (contentFlag == 1 && generationCount % generationCheck == 0) {

            if (maxFitness <= checkMaxFitness * (1 + generationInc)) {
                System.out.print("\n");
                System.out.print("[Content drop detected (Max fitness)]");
                return false;
            }
            else
                checkMaxFitness = maxFitness;
        }

        if (optFlag == 1 && Math.abs(maxFitness - fitnessOpt) < epsilon) {
            System.out.print("\n");
            System.out.print("[Optimum reached]");
            return false;
        }

        if (iterationsFlag == 1 && generationCount == generationsMax) {
            System.out.print("\n");
            System.out.print("[Max iterations reached]");
            return false;
        }

        double currentTime = ((System.currentTimeMillis() - initialTime)/1000.0);

        if(timeFlag == 1 && currentTime > timeMax) {
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

    public List<Character> getBestIndividuals() {
        List<Character> bestIndividuals = new LinkedList<>();
        bestIndividuals.add(bestIndividual);
        bestIndividuals.add(prevBestIndividual);
        return bestIndividuals;
    }
}
