package ar.edu.itba.sia.utils;

import ar.edu.itba.sia.model.character.Character;
import ar.edu.itba.sia.model.character.archer.*;
import ar.edu.itba.sia.model.character.assasin.*;
import ar.edu.itba.sia.model.character.defender.*;
import ar.edu.itba.sia.model.character.warrior.*;

import ar.edu.itba.sia.model.equipment.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class PopulationGenerator {

    private List<Equipment> armors;
    private List<Equipment> boots;
    private List<Equipment> gloves;
    private List<Equipment> helmets;
    private List<Equipment> weapons;
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

        this.armors = EquipmentParser.parse(armorFile, EquipmentType.ARMOR);
        this.boots = EquipmentParser.parse(bootsFile, EquipmentType.BOOTS);
        this.gloves = EquipmentParser.parse(glovesFile, EquipmentType.GLOVES);
        this.helmets = EquipmentParser.parse(helmetFile, EquipmentType.HELMET);
        this.weapons = EquipmentParser.parse(weaponFile, EquipmentType.WEAPON);

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
                    initialGeneration.add(new Archer1(height, (Armor) armors.get(armorIndex), (Boots) boots.get(bootsIndex),
                            (Gloves) gloves.get(glovesIndex), (Helmet) helmets.get(helmetIndex), (Weapon) weapons.get(weaponIndex)));
                    break;
                case ARCHER2:
                    initialGeneration.add(new Archer2(height, (Armor) armors.get(armorIndex), (Boots) boots.get(bootsIndex),
                            (Gloves) gloves.get(glovesIndex), (Helmet) helmets.get(helmetIndex), (Weapon) weapons.get(weaponIndex)));
                    break;
                case ARCHER3:
                    initialGeneration.add(new Archer3(height, (Armor) armors.get(armorIndex), (Boots) boots.get(bootsIndex),
                            (Gloves) gloves.get(glovesIndex), (Helmet) helmets.get(helmetIndex), (Weapon) weapons.get(weaponIndex)));
                    break;
                case ASSASIN1:
                    initialGeneration.add(new Assassin1(height, (Armor) armors.get(armorIndex), (Boots) boots.get(bootsIndex),
                            (Gloves) gloves.get(glovesIndex), (Helmet) helmets.get(helmetIndex), (Weapon) weapons.get(weaponIndex)));
                    break;
                case ASSASIN2:
                    initialGeneration.add(new Assassin2(height, (Armor) armors.get(armorIndex), (Boots) boots.get(bootsIndex),
                            (Gloves) gloves.get(glovesIndex), (Helmet) helmets.get(helmetIndex), (Weapon) weapons.get(weaponIndex)));
                    break;
                case ASSASIN3:
                    initialGeneration.add(new Assassin3(height, (Armor) armors.get(armorIndex), (Boots) boots.get(bootsIndex),
                            (Gloves) gloves.get(glovesIndex), (Helmet) helmets.get(helmetIndex), (Weapon) weapons.get(weaponIndex)));
                    break;
                case DEFENDER1:
                    initialGeneration.add(new Defender1(height, (Armor) armors.get(armorIndex), (Boots) boots.get(bootsIndex),
                            (Gloves) gloves.get(glovesIndex), (Helmet) helmets.get(helmetIndex), (Weapon) weapons.get(weaponIndex)));
                    break;
                case DEFENDER2:
                    initialGeneration.add(new Defender2(height, (Armor) armors.get(armorIndex), (Boots) boots.get(bootsIndex),
                            (Gloves) gloves.get(glovesIndex), (Helmet) helmets.get(helmetIndex), (Weapon) weapons.get(weaponIndex)));
                    break;
                case DEFENDER3:
                    initialGeneration.add(new Defender3(height, (Armor) armors.get(armorIndex), (Boots) boots.get(bootsIndex),
                            (Gloves) gloves.get(glovesIndex), (Helmet) helmets.get(helmetIndex), (Weapon) weapons.get(weaponIndex)));
                    break;
                case WARRIOR1:
                    initialGeneration.add(new Warrior1(height, (Armor) armors.get(armorIndex), (Boots) boots.get(bootsIndex),
                            (Gloves) gloves.get(glovesIndex), (Helmet) helmets.get(helmetIndex), (Weapon) weapons.get(weaponIndex)));
                    break;
                case WARRIOR2:
                    initialGeneration.add(new Warrior2(height, (Armor) armors.get(armorIndex), (Boots) boots.get(bootsIndex),
                            (Gloves) gloves.get(glovesIndex), (Helmet) helmets.get(helmetIndex), (Weapon) weapons.get(weaponIndex)));
                    break;
                case WARRIOR3:
                    initialGeneration.add(new Warrior3(height, (Armor) armors.get(armorIndex), (Boots) boots.get(bootsIndex),
                            (Gloves) gloves.get(glovesIndex), (Helmet) helmets.get(helmetIndex), (Weapon) weapons.get(weaponIndex)));
                    break;
            }
            cant++;
        }
    }

    public List<Equipment> getArmors() {
        return armors;
    }

    public List<Equipment> getBoots() {
        return boots;
    }

    public List<Equipment> getGloves() {
        return gloves;
    }

    public List<Equipment> getHelmets() {
        return helmets;
    }

    public List<Equipment> getWeapons() {
        return weapons;
    }

    public List<Character> getInitialGeneration() {
        return initialGeneration;
    }
}
