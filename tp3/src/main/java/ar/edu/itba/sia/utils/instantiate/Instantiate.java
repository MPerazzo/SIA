package ar.edu.itba.sia.utils.instantiate;

import ar.edu.itba.sia.model.character.Character;
import ar.edu.itba.sia.model.equipment.*;

public interface Instantiate {
   Character Instantiate(double height, Armor armor, Boots boots,
                         Gloves gloves, Helmet helmet, Weapon weapon);
}
