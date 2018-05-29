package ar.edu.itba.sia.core;

import ar.edu.itba.sia.model.CharacterComparator;
import ar.edu.itba.sia.model.character.Character;
import ar.edu.itba.sia.model.character.archer.Archer;
import ar.edu.itba.sia.model.character.assassin.Assassin;
import ar.edu.itba.sia.model.character.defender.Defender;
import ar.edu.itba.sia.model.character.warrior.Warrior;
import ar.edu.itba.sia.model.equipment.*;
import ar.edu.itba.sia.utils.Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Raid {

    private static final int SELECTION_CANT = 10;

    private static final int DEFENDER_CLASS_NUMBER = 4;
    private static final int CLASSES = 4;
    private static final int INSTANCES_PER_CLASS = 3;

    private static final double PERFORMANCE_MOD = 0.05;

    public Raid(Parser p) throws ExecutionException, InterruptedException {

        final List<Armor> armors = p.getArmors();
        final List<Boots> boots = p.getBoots();
        final List<Gloves> gloves = p.getGloves();
        final List<Helmet> helmets = p.getHelmets();
        final List<Weapon> weapons = p.getWeapons();

        String[] classes ={"Warrior", "Archer", "Assassin", "Defender"};
        List<String> paths = new ArrayList<>();

        for (String s : classes) {
            for (int i = 1 ; i <= INSTANCES_PER_CLASS ; i++)
                paths.add("raid/" + s + i + ".txt");
        }

        List<GeneticAlgorithm> geneticAlgorithms = new ArrayList<>();

        for (String s : paths) {
            geneticAlgorithms.add(new GeneticAlgorithm(new Parser(s, armors, boots, gloves,
                    helmets, weapons)));
        }

        List<Future<List<Character>>> futures = new ArrayList<>();

        ExecutorService e = Executors.newCachedThreadPool();

        for (GeneticAlgorithm g : geneticAlgorithms) {
            futures.add(e.submit(() -> g.geneticAlgorithm()));
        }

        for (Future<List<Character>> f : futures)
            f.get();

        e.shutdown();

        List<List<Character>> allCandidates = new ArrayList<>();

        for (int i = 0 ; i < CLASSES ; i++)
            allCandidates.add(new ArrayList<>());

        int n = 0;
        int m = 0;
        for (GeneticAlgorithm g : geneticAlgorithms) {
            if (n % INSTANCES_PER_CLASS == 0 && n!= 0)
                m++;

            allCandidates.get(m).addAll(g.getBestIndividuals());
            n++;
        }

        for (List<Character> l : allCandidates)
            l.sort(CharacterComparator.getReverseOrder());

        List<Character> bestCandidates = new ArrayList<>();

        Character bestDefender = null;
        for (int k = 1 ; k <= CLASSES ; k++) {
            List<Character> classCandidates = allCandidates.get(k-1);
            Character bestClassCandidate = classCandidates.get(0);
            Character secondBestClassCandidate = classCandidates.get(1);
            bestCandidates.add(bestClassCandidate.newSon(bestClassCandidate.getHeight(),
                    bestClassCandidate.getEquipment()));

            if (k != DEFENDER_CLASS_NUMBER) {
                bestCandidates.add(bestClassCandidate);
                bestCandidates.add(secondBestClassCandidate);
                bestCandidates.add(secondBestClassCandidate.newSon(secondBestClassCandidate.getHeight(),
                        secondBestClassCandidate.getEquipment()));
            }
            else
                bestDefender = bestClassCandidate;
        }

        List<Character> selected = new ArrayList<>();

        //agarras un defensor
        selected.add(bestDefender);

        bestCandidates.sort(CharacterComparator.getReverseOrder());

        for (int k = 0 ; k < SELECTION_CANT - 1; k++)
            selected.add(bestCandidates.get(k));

        int classCount = 0;
        boolean warrior = false;
        boolean archer = false;
        boolean assassin = false;
        boolean defender = false;

        for (Character c : selected) {

            if (classCount == CLASSES)
                break;

            if (c instanceof Warrior && !warrior) {
                classCount++;
                warrior = true;
            }
            else if (c instanceof Archer && !archer) {
                classCount++;
                archer = true;
            }
            else if (c instanceof Assassin && !assassin) {
                classCount++;
                assassin = true;
            }
            else if (c instanceof Defender && !defender) {
                classCount++;
                defender = true;
            }
        }

        double bestGenPerformance = 0;

        for (Character c : selected)
            bestGenPerformance += c.getFitness();

        bestGenPerformance *= (1 + 3 * PERFORMANCE_MOD);

        List<Character> selectedAllClasses = new ArrayList<>();
        if (classCount != CLASSES) {
            selectedAllClasses.addAll(selected);
            selectedAllClasses.set(selected.size() - 1, bestCandidates.get(selected.size()));

            double newBestGenPerformance = 0;

            for (Character c : selectedAllClasses)
                newBestGenPerformance += c.getFitness();

            newBestGenPerformance *= (1 + CLASSES * PERFORMANCE_MOD);


            if (newBestGenPerformance > bestGenPerformance)
                selected = selectedAllClasses;
        }

        System.out.print("The best team is:\n\n");
        for(Character c : selected) {
            System.out.println(c);
        }
    }
}
