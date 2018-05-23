package ar.edu.itba.sia.utils;

import ar.edu.itba.sia.model.character.Character;
import ar.edu.itba.sia.model.character.archer.*;
import ar.edu.itba.sia.model.character.assasin.*;
import ar.edu.itba.sia.model.character.defender.*;
import ar.edu.itba.sia.model.character.warrior.*;

import ar.edu.itba.sia.model.equipment.*;
import ar.edu.itba.sia.utils.equipmentParsers.*;

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

        int armorIndex;
        int bootsIndex;
        int glovesIndex;
        int helmetIndex;
        int weaponIndex;
        double height;
        int cant = 0;

        while (cant < population) {
            armorIndex = ThreadLocalRandom.current().nextInt(0, armorsSize);
            bootsIndex = ThreadLocalRandom.current().nextInt(0, bootsSize);
            glovesIndex = ThreadLocalRandom.current().nextInt(0, glovesSize);
            helmetIndex = ThreadLocalRandom.current().nextInt(0, helmetsSize);
            weaponIndex = ThreadLocalRandom.current().nextInt(0, weaponsSize);
            height = ThreadLocalRandom.current().nextDouble(ConfigurationManager.HEIGHT_START, ConfigurationManager.HEIGHT_END);
            switch (type) {
                case ARCHER1:
                    initialGeneration.add(new Archer1(height, armors.get(armorIndex), boots.get(bootsIndex),
                             gloves.get(glovesIndex), helmets.get(helmetIndex), weapons.get(weaponIndex)));
                    break;
                case ARCHER2:
                    initialGeneration.add(new Archer2(height, armors.get(armorIndex), boots.get(bootsIndex),
                            gloves.get(glovesIndex), helmets.get(helmetIndex), weapons.get(weaponIndex)));
                    break;
                case ARCHER3:
                    initialGeneration.add(new Archer3(height, armors.get(armorIndex), boots.get(bootsIndex),
                            gloves.get(glovesIndex), helmets.get(helmetIndex), weapons.get(weaponIndex)));
                    break;
                case ASSASIN1:
                    initialGeneration.add(new Assassin1(height, armors.get(armorIndex), boots.get(bootsIndex),
                            gloves.get(glovesIndex), helmets.get(helmetIndex), weapons.get(weaponIndex)));
                    break;
                case ASSASIN2:
                    initialGeneration.add(new Assassin2(height, armors.get(armorIndex), boots.get(bootsIndex),
                            gloves.get(glovesIndex), helmets.get(helmetIndex), weapons.get(weaponIndex)));
                    break;
                case ASSASIN3:
                    initialGeneration.add(new Assassin3(height, armors.get(armorIndex), boots.get(bootsIndex),
                            gloves.get(glovesIndex), helmets.get(helmetIndex), weapons.get(weaponIndex)));
                    break;
                case DEFENDER1:
                    initialGeneration.add(new Defender1(height, armors.get(armorIndex), boots.get(bootsIndex),
                            gloves.get(glovesIndex), helmets.get(helmetIndex), weapons.get(weaponIndex)));
                    break;
                case DEFENDER2:
                    initialGeneration.add(new Defender2(height, armors.get(armorIndex), boots.get(bootsIndex),
                            gloves.get(glovesIndex), helmets.get(helmetIndex), weapons.get(weaponIndex)));
                    break;
                case DEFENDER3:
                    initialGeneration.add(new Defender3(height, armors.get(armorIndex), boots.get(bootsIndex),
                            gloves.get(glovesIndex), helmets.get(helmetIndex), weapons.get(weaponIndex)));
                    break;
                case WARRIOR1:
                    initialGeneration.add(new Warrior1(height, armors.get(armorIndex), boots.get(bootsIndex),
                            gloves.get(glovesIndex), helmets.get(helmetIndex), weapons.get(weaponIndex)));
                    break;
                case WARRIOR2:
                    initialGeneration.add(new Warrior2(height, armors.get(armorIndex), boots.get(bootsIndex),
                            gloves.get(glovesIndex), helmets.get(helmetIndex), weapons.get(weaponIndex)));
                    break;
                case WARRIOR3:
                    initialGeneration.add(new Warrior3(height, armors.get(armorIndex), boots.get(bootsIndex),
                            gloves.get(glovesIndex), helmets.get(helmetIndex), weapons.get(weaponIndex)));
                    break;
            }
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
