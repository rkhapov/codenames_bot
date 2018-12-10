package core.game;

import com.google.inject.Inject;
import core.generators.GeneratorException;
import core.generators.field.IFieldGenerator;
import core.primitives.Card;
import core.primitives.Color;
import core.primitives.Field;
import core.primitives.GameState;
import java.util.List;

public class Game implements IGame {

  private Field field;
  private GameState state;
  private Color whoseTurn;


  @Inject
  public Game(IFieldGenerator fieldGenerator) {
    try {
      this.field = fieldGenerator.generate();
    } catch (GeneratorException e) {
      //skip
    }
  }

  @Override
  public void openCard(String word) {
    var openedCard = field.open(word);
    var cards = field.getCards();
    var color = openedCard.getColor();
    if (color == Color.Black) {
      state = whoseTurn.getOpposite().getState();
      return;
    }

    if (color != whoseTurn) {
      whoseTurn = whoseTurn.getOpposite();
    }

    state = computeState(cards);
  }

  @Override
  public Field getField() {
    return field;
  }

  @Override
  public Color nextTurnColor() {
    return whoseTurn;
  }

  @Override
  public GameState getState() {
    return state;
  }

  private GameState computeState(List<Card> cards) {
    var blue = 0;
    var red = 0;
    for (var card : cards) {
      if (card.getColor() == Color.Red) {
        red++;
      }
      if (card.getColor() == Color.Blue) {
        blue++;
      }
    }

    if (blue == 0) {
      return GameState.BlueTeamWin;
    }
    if (red == 0) {
      return GameState.RedTeamWin;
    }
    return GameState.InProgress;
  }
}
