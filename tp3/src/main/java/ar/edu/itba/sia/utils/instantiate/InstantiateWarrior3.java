package ar.edu.itba.sia.utils.instantiate;

import ar.edu.itba.sia.model.character.Character;
import ar.edu.itba.sia.model.character.warrior.Warrior3;
import ar.edu.itba.sia.model.equipment.*;

public class InstantiateWarrior3 implements Instantiate {
    @Override
    public Character Instantiate(double height, Armor armor, Boots boots, Gloves gloves, Helmet helmet, Weapon weapon) {
        return new Warrior3(height, armor, boots,
                gloves, helmet, weapon);
    }
}
