package core.game;

import core.primitives.Card;
import core.primitives.Color;
import core.primitives.Field;
import core.primitives.GameState;
import java.util.Collection;

public interface IGame {

  Card openCard(String word);

  Field getField();

  Color getNextTurnColor();

  boolean hasCard(String word);

  GameState getState();
}
