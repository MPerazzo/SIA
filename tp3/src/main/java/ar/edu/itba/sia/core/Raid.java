package ar.edu.itba.sia.core;

import ar.edu.itba.sia.model.character.Character;
import ar.edu.itba.sia.model.character.archer.Archer;
import ar.edu.itba.sia.model.character.assassin.Assassin;
import ar.edu.itba.sia.model.character.defender.Defender;
import ar.edu.itba.sia.model.character.warrior.Warrior;
import ar.edu.itba.sia.model.equipment.*;
import ar.edu.itba.sia.utils.Parser;
import ar.edu.itba.sia.utils.equipmentParsers.*;

import javax.management.AttributeNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Raid {

    public Raid(String armorFile, String bootsFile, String glovesFile, String helmetFile,
                     String weaponFile) throws IOException, ExecutionException, InterruptedException, AttributeNotFoundException {

        final List<Armor> armors = ArmorParser.parse(armorFile);
        final List<Boots> boots = BootsParser.parse(bootsFile);
        final List<Gloves> gloves = GlovesParser.parse(glovesFile);
        final List<Helmet> helmets = HelmetParser.parse(helmetFile);
        final List<Weapon> weapons = WeaponParser.parse(weaponFile);


        ExecutorService e = Executors.newCachedThreadPool();

        GeneticAlgorithm g1 = new GeneticAlgorithm(new Parser("configFile1.txt", armors, boots, gloves,
                helmets, weapons));

        GeneticAlgorithm g2 = new GeneticAlgorithm(new Parser("configFile2.txt", armors, boots, gloves,
                helmets, weapons));

        GeneticAlgorithm g3 = new GeneticAlgorithm(new Parser("configFile3.txt", armors, boots, gloves,
                helmets, weapons));

        GeneticAlgorithm g4 = new GeneticAlgorithm(new Parser("configFile4.txt", armors, boots, gloves,
                helmets, weapons));

        GeneticAlgorithm g5 = new GeneticAlgorithm(new Parser("configFile5.txt", armors, boots, gloves,
                helmets, weapons));

        GeneticAlgorithm g6 = new GeneticAlgorithm(new Parser("configFile6.txt", armors, boots, gloves,
                helmets, weapons));

        GeneticAlgorithm g7 = new GeneticAlgorithm(new Parser("configFile7.txt", armors, boots, gloves,
                helmets, weapons));

        GeneticAlgorithm g8 = new GeneticAlgorithm(new Parser("configFile8.txt", armors, boots, gloves,
                helmets, weapons));

        GeneticAlgorithm g9 = new GeneticAlgorithm(new Parser("configFile9.txt", armors, boots, gloves,
                helmets, weapons));

        GeneticAlgorithm g10 = new GeneticAlgorithm(new Parser("configFile10.txt", armors, boots, gloves,
                helmets, weapons));

        GeneticAlgorithm g11 = new GeneticAlgorithm(new Parser("configFile11.txt", armors, boots, gloves,
                helmets, weapons));

        GeneticAlgorithm g12 = new GeneticAlgorithm(new Parser("configFile12.txt", armors, boots, gloves,
                helmets, weapons));

        Future<Void> f1 = e.submit(() -> g1.geneticAlgorithm());
        Future<Void> f2 = e.submit(() -> g2.geneticAlgorithm());
        Future<Void> f3 = e.submit(() -> g3.geneticAlgorithm());
        Future<Void> f4 = e.submit(() -> g4.geneticAlgorithm());
        Future<Void> f5 = e.submit(() -> g5.geneticAlgorithm());
        Future<Void> f6 = e.submit(() -> g6.geneticAlgorithm());
        Future<Void> f7 = e.submit(() -> g7.geneticAlgorithm());
        Future<Void> f8 = e.submit(() -> g8.geneticAlgorithm());
        Future<Void> f9 = e.submit(() -> g9.geneticAlgorithm());
        Future<Void> f10 = e.submit(() -> g10.geneticAlgorithm());
        Future<Void> f11 = e.submit(() -> g11.geneticAlgorithm());
        Future<Void> f12 = e.submit(() -> g12.geneticAlgorithm());


        f1.get();
        f2.get();
        f3.get();
        f4.get();
        f5.get();
        f6.get();
        f7.get();
        f8.get();
        f9.get();
        f10.get();
        f11.get();
        f12.get();

        e.shutdown();

        List<Character> candidatesWarriors = new ArrayList<>();
        List<Character> candidatesArchers = new ArrayList<>();
        List<Character> candidatesAssassins = new ArrayList<>();
        List<Character> candidatesDefenders = new ArrayList<>();

        candidatesWarriors.addAll(g1.getBestIndividuals());
        candidatesWarriors.addAll(g2.getBestIndividuals());
        candidatesWarriors.addAll(g3.getBestIndividuals());
        candidatesArchers.addAll(g4.getBestIndividuals());
        candidatesArchers.addAll(g5.getBestIndividuals());
        candidatesArchers.addAll(g6.getBestIndividuals());
        candidatesAssassins.addAll(g7.getBestIndividuals());
        candidatesAssassins.addAll(g8.getBestIndividuals());
        candidatesAssassins.addAll(g9.getBestIndividuals());
        candidatesDefenders.addAll(g10.getBestIndividuals());
        candidatesDefenders.addAll(g11.getBestIndividuals());
        candidatesDefenders.addAll(g12.getBestIndividuals());

        candidatesWarriors.sort((c1, c2) -> {
            if (c1.getFitness() > c2.getFitness())
                return -1;
            else if (c1.getFitness() < c2.getFitness())
                return 1;
            else
                return 0;
        });

        candidatesArchers.sort((c1, c2) -> {
            if (c1.getFitness() > c2.getFitness())
                return -1;
            else if (c1.getFitness() < c2.getFitness())
                return 1;
            else
                return 0;
        });

        candidatesAssassins.sort((c1, c2) -> {
            if (c1.getFitness() > c2.getFitness())
                return -1;
            else if (c1.getFitness() < c2.getFitness())
                return 1;
            else
                return 0;
        });

        candidatesDefenders.sort((c1, c2) -> {
            if (c1.getFitness() > c2.getFitness())
                return -1;
            else if (c1.getFitness() < c2.getFitness())
                return 1;
            else
                return 0;
        });

        List<Character> bestWarriors = new ArrayList<>();
        List<Character> bestArchers = new ArrayList<>();
        List<Character> bestAssassins = new ArrayList<>();

        Character bestWarrior = candidatesWarriors.get(0);
        Character secondBestWarrior = candidatesWarriors.get(1);
        bestWarriors.add(bestWarrior);
        bestWarriors.add(bestWarrior.newSon(bestWarrior.getHeight(), bestWarrior.getEquipment()));
        bestWarriors.add(secondBestWarrior);
        bestWarriors.add(secondBestWarrior.newSon(secondBestWarrior.getHeight(), secondBestWarrior.getEquipment()));

        Character bestArcher = candidatesArchers.get(0);
        Character secondBestArcher = candidatesArchers.get(1);
        bestArchers.add(bestArcher);
        bestArchers.add(bestArcher.newSon(bestArcher.getHeight(), bestArcher.getEquipment()));
        bestWarriors.add(secondBestArcher);
        bestWarriors.add(secondBestArcher.newSon(secondBestArcher.getHeight(), secondBestArcher.getEquipment()));

        Character bestAssassin = candidatesAssassins.get(0);
        Character secondBestAssassin = candidatesAssassins.get(1);
        bestAssassins.add(bestAssassin);
        bestAssassins.add(bestAssassin.newSon(bestAssassin.getHeight(), bestAssassin.getEquipment()));
        bestAssassins.add(secondBestAssassin);
        bestAssassins.add(secondBestAssassin.newSon(secondBestAssassin.getHeight(), secondBestAssassin.getEquipment()));

        Character bestDefender = candidatesDefenders.get(0);
        Character bestDefenderCopy = bestDefender.newSon(bestDefender.getHeight(), bestDefender.getEquipment());

        List<Character> selected = new ArrayList<>();
        //agarras un defensor
        selected.add(bestDefender);

        List<Character> candidates = new ArrayList<>();

        candidates.addAll(bestArchers);
        candidates.addAll(bestAssassins);
        candidates.addAll(bestWarriors);
        candidates.add(bestDefenderCopy);

        //seleccionas 9
        candidates.sort((c1, c2) -> {
            if (c1.getFitness() > c2.getFitness())
                return -1;
            else if (c1.getFitness() < c2.getFitness())
                return 1;
            else
                return 0;
        });

        List<Character> bestGen = new ArrayList<>();

        for (int i = 0 ; i < 9 ; i++)
            bestGen.add(candidates.get(i));

        int classCount = 0;
        boolean warrior = false;
        boolean archer = false;
        boolean assassin = false;
        boolean defender = false;
        for (Character c : bestGen) {

            if (classCount == 4)
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

        for (Character c : bestGen)
            bestGenPerformance += c.getFitness();

        bestGenPerformance *= 1.15;

        if (classCount != 4)
            bestGen.set(bestGen.size() - 1, candidates.get(bestGen.size()));

        double newBestGenPerformance = 0;

        for (Character c : bestGen)
            newBestGenPerformance += c.getFitness();

        newBestGenPerformance *= 1.20;

        bestGen.sort((c1, c2) -> {
            if (c1.getFitness() > c2.getFitness())
                return -1;
            else if (c1.getFitness() < c2.getFitness())
                return 1;
            else
                return 0;
        });


        if (bestGenPerformance > newBestGenPerformance)
            bestGen.set(bestGen.size() - 1, candidates.get(bestGen.size() - 1));

        selected.addAll(bestGen);
        for(Character c : bestGen) {
            System.out.println(c.getFitness());
        }
        int a;
    }
}
