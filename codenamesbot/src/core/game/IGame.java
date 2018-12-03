package core.game;

import core.primitives.Color;
import core.primitives.GameState;
import java.util.Collection;

public interface IGame {

  Color getTurnOrder();

  void selectCard(String word);

  GameState getState();
}
