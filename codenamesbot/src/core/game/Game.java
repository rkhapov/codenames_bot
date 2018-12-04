package core.game;

import com.google.inject.Inject;
import core.generators.GeneratorException;
import core.generators.field.IFieldGenerator;
import core.primitives.Color;
import core.primitives.Field;
import core.primitives.GameState;

public class Game implements IGame {

  private final IFieldGenerator fieldGenerator;
  private Field field;
  private GameState state;

  @Inject
  public Game(IFieldGenerator fieldGenerator) {
    this.fieldGenerator = fieldGenerator;
    restart();
  }

  public void restart() {
    try {
      field = fieldGenerator.generate();
      state = GameState.InProgress;
    } catch (GeneratorException e) {
      //skip
    }
  }

  @Override
  public void openCard(String word) {
    field.getCard(word).open();
//    var cards = field.getCards();
//    var blue = 0;
//    var red = 0;
//    for(var card : cards) {
//      if (card.getColor() == Color.Red)
//        red++;
//      if (card.getColor() == Color.Blue)
//        blue++;
  }

  @Override
  public Field getField() {
    return field;
  }

  @Override
  public GameState getState() {
    return state;
  }
}
