package ar.edu.itba.sia.core;

import ar.edu.itba.sia.model.equipment.*;
import ar.edu.itba.sia.utils.Parser;
import ar.edu.itba.sia.utils.equipmentParsers.*;

import javax.management.AttributeNotFoundException;
import java.io.IOException;
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




        /*
        new GeneticAlgorithm(new Parser("configFile1.txt", armors, boots, gloves,
                helmets, weapons)).geneticAlgorithm();
        new GeneticAlgorithm(new Parser("configFile2.txt", armors, boots, gloves,
                helmets, weapons)).geneticAlgorithm();
        new GeneticAlgorithm(new Parser("configFile3.txt", armors, boots, gloves,
                helmets, weapons)).geneticAlgorithm();
        new GeneticAlgorithm(new Parser("configFile4.txt", armors, boots, gloves,
                helmets, weapons)).geneticAlgorithm();
        new GeneticAlgorithm(new Parser("configFile5.txt", armors, boots, gloves,
                helmets, weapons)).geneticAlgorithm();
                */
    }
}
