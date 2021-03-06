package ar.edu.itba.sia.utils;

import ar.edu.itba.sia.core.RandomSeeded;
import ar.edu.itba.sia.model.character.Character;

import ar.edu.itba.sia.model.equipment.*;
import ar.edu.itba.sia.utils.enums.CharacterType;
import ar.edu.itba.sia.utils.equipmentParsers.*;
import ar.edu.itba.sia.utils.instantiate.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class PopulationGenerator {

    private List<Armor> armors;
    private List<Boots> boots;
    private List<Gloves> gloves;
    private List<Helmet> helmets;
    private List<Weapon> weapons;
    private List<Character> initialGeneration;

    public PopulationGenerator(final int population, final CharacterType type, final String armorFile,
                               final String bootsFile, final String glovesFile,
                               final String helmetFile, final String weaponFile,
                               RandomSeeded r) throws IOException {

        this.initialGeneration = new LinkedList<>();

        generate(population, type, armorFile, bootsFile, glovesFile, helmetFile, weaponFile, r);
    }

    public PopulationGenerator(int population, CharacterType type, List<Armor> armors,
                               List<Boots> boots, List<Gloves> gloves, List<Helmet> helmets, List<Weapon> weapons,
                               RandomSeeded r) {

        this.initialGeneration = new LinkedList<>();

        this.armors = armors;
        this.boots = boots;
        this.gloves = gloves;
        this.helmets = helmets;
        this.weapons = weapons;

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
            case ASSASSIN1:
                instantiator = new InstantiateAssassin1();
                break;
            case ASSASSIN2:
                instantiator = new InstantiateAssassin2();
                break;
            case ASSASSIN3:
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
            int armorIndex = r.nextInt(0, armorsSize);
            int bootsIndex = r.nextInt(0, bootsSize);
            int glovesIndex = r.nextInt(0, glovesSize);
            int helmetIndex = r.nextInt(0, helmetsSize);
            int weaponIndex = r.nextInt(0, weaponsSize);
            double height = r.nextDouble(ConfigurationManager.HEIGHT_START, ConfigurationManager.HEIGHT_END);

            initialGeneration.add(instantiator.Instantiate(height, armors.get(armorIndex), boots.get(bootsIndex),
                    gloves.get(glovesIndex), helmets.get(helmetIndex), weapons.get(weaponIndex)));
            cant++;
        }
    }

    private void generate(final int population, CharacterType type, final String armorFile,
                                           final String bootsFile, final String glovesFile,
                                           final String helmetFile, final String weaponFile,
                                            final RandomSeeded r) throws IOException {

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
            case ASSASSIN1:
                instantiator = new InstantiateAssassin1();
                break;
            case ASSASSIN2:
                instantiator = new InstantiateAssassin2();
                break;
            case ASSASSIN3:
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
            int armorIndex = r.nextInt(0, armorsSize);
            int bootsIndex = r.nextInt(0, bootsSize);
            int glovesIndex = r.nextInt(0, glovesSize);
            int helmetIndex = r.nextInt(0, helmetsSize);
            int weaponIndex = r.nextInt(0, weaponsSize);
            double height = r.nextDouble(ConfigurationManager.HEIGHT_START, ConfigurationManager.HEIGHT_END);

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
