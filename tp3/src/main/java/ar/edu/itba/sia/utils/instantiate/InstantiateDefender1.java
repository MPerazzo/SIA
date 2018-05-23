package ar.edu.itba.sia.utils.instantiate;

import ar.edu.itba.sia.model.character.Character;
import ar.edu.itba.sia.model.character.defender.Defender1;
import ar.edu.itba.sia.model.equipment.*;

public class InstantiateDefender1 implements Instantiate {
    @Override
    public Character Instantiate(double height, Armor armor, Boots boots, Gloves gloves, Helmet helmet, Weapon weapon) {
        return new Defender1(height, armor, boots,
                gloves, helmet, weapon);
    }
}
