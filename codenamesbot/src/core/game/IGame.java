package core.game;

import core.primitives.Color;
import core.primitives.Field;
import core.primitives.GameState;
import java.util.Collection;

public interface IGame {

  void openCard(String word);

  Field getField();

  Color getNextTurnColor();

  GameState getState();
}
