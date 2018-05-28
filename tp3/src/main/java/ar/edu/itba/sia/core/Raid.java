package ar.edu.itba.sia.core;

import ar.edu.itba.sia.model.equipment.*;
import ar.edu.itba.sia.utils.Parser;
import ar.edu.itba.sia.utils.equipmentParsers.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Raid {

    public void raid(String armorFile, String bootsFile, String glovesFile, String helmetFile,
                     String weaponFile) throws IOException {

        List<Armor> armors = ArmorParser.parse(armorFile);
        List<Boots> boots = BootsParser.parse(bootsFile);
        List<Gloves> gloves = GlovesParser.parse(glovesFile);
        List<Helmet> helmets = HelmetParser.parse(helmetFile);
        List<Weapon> weapons = WeaponParser.parse(weaponFile);

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




    }
}
