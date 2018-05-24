package ar.edu.itba.sia.utils;

import ar.edu.itba.sia.model.character.Character;

import ar.edu.itba.sia.model.equipment.*;
import ar.edu.itba.sia.utils.equipmentParsers.*;
import ar.edu.itba.sia.utils.instantiate.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class PopulationGenerator {

    private List<Armor> armors;
    private List<Boots> boots;
    private List<Gloves> gloves;
    private List<Helmet> helmets;
    private List<Weapon> weapons;
    private List<Character> initialGeneration;

    public PopulationGenerator(final int population, final CharacterType type, final String armorFile,
                               final String bootsFile, final String glovesFile,
                               final String helmetFile, final String weaponFile) throws IOException {

        this.initialGeneration = new LinkedList<>();

        generate(population, type, armorFile, bootsFile, glovesFile, helmetFile, weaponFile);
    }

    private void generate(final int population, CharacterType type, final String armorFile,
                                           final String bootsFile, final String glovesFile,
                                           final String helmetFile, final String weaponFile) throws IOException {

        this.armors = ArmorParser.parse(armorFile);
        this.boots = BootsParser.parse(bootsFile);
        this.gloves = GlovesParser.parse(glovesFile);
        this.helmets = HelmetParser.parse(helmetFile);
        this.weapons = WeaponParser.parse(weaponFile);

        int armorsSize = armors.size();
        int bootsSize = boots.size();
        int glovesSize = gloves.size();
        int helmetsSize = helmets.size();
        int weaponsSize = weapons.size();

        Instantiate instantiator = null;

        switch (type) {
            case ARCHER1:
                instantiator = new InstantiateArcher1();
                break;
            case ARCHER2:
                instantiator = new InstantiateArcher2();
                break;
            case ARCHER3:
                instantiator = new InstantiateArcher3();
                break;
            case ASSASIN1:
                instantiator = new InstantiateAssassin1();
                break;
            case ASSASIN2:
                instantiator = new InstantiateAssassin2();
                break;
            case ASSASIN3:
                instantiator = new InstantiateAssassin3();
                break;
            case DEFENDER1:
                instantiator = new InstantiateDefender1();
                break;
            case DEFENDER2:
                instantiator = new InstantiateDefender2();
                break;
            case DEFENDER3:
                instantiator = new InstantiateDefender3();
                break;
            case WARRIOR1:
                instantiator = new InstantiateWarrior1();
                break;
            case WARRIOR2:
                instantiator = new InstantiateWarrior2();
                break;
            case WARRIOR3:
                instantiator = new InstantiateWarrior3();
                break;
        }

        int cant = 0;
        while (cant < population) {
            int armorIndex = ThreadLocalRandom.current().nextInt(0, armorsSize);
            int bootsIndex = ThreadLocalRandom.current().nextInt(0, bootsSize);
            int glovesIndex = ThreadLocalRandom.current().nextInt(0, glovesSize);
            int helmetIndex = ThreadLocalRandom.current().nextInt(0, helmetsSize);
            int weaponIndex = ThreadLocalRandom.current().nextInt(0, weaponsSize);
            double height = ThreadLocalRandom.current().nextDouble(ConfigurationManager.HEIGHT_START, ConfigurationManager.HEIGHT_END);

            initialGeneration.add(instantiator.Instantiate(height, armors.get(armorIndex), boots.get(bootsIndex),
                    gloves.get(glovesIndex), helmets.get(helmetIndex), weapons.get(weaponIndex)));
            cant++;
        }
    }

    public List<Armor> getArmors() {
        return armors;
    }

    public List<Boots> getBoots() {
        return boots;
    }

    public List<Gloves> getGloves() {
        return gloves;
    }

    public List<Helmet> getHelmets() {
        return helmets;
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    public List<Character> getInitialGeneration() {
        return initialGeneration;
    }
}
