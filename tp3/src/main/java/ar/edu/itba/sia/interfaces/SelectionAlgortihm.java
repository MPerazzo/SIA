package ar.edu.itba.sia.interfaces;


import ar.edu.itba.sia.model.character.*;
import ar.edu.itba.sia.model.character.Character;

import java.util.List;

public interface SelectionAlgortihm {
    List<Character> select(List<Character> characters);
}
