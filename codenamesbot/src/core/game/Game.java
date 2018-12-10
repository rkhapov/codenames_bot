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

    whoseTurn = Color.RED;
    state = GameState.IN_PROGRESS;
  }

  @Override
  public Card openCard(String word) {
    var openedCard = field.open(word);
    var cards = field.getCards();
    var color = openedCard.getColor();
    if (color == Color.BLACK) {
      state = whoseTurn.getOpposite().getState();
      return openedCard;
    }

    if (color != whoseTurn) {
      whoseTurn = whoseTurn.getOpposite();
    }

    state = computeState(cards);
    return openedCard;
  }

  public boolean hasCard(String word) {
    return field.hasCard(word);
  }

  @Override
  public Field getField() {
    return field;
  }

  @Override
  public Color getNextTurnColor() {
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
      if (card.getColor() == Color.RED) {
        red++;
      }
      if (card.getColor() == Color.BLUE) {
        blue++;
      }
    }

    if (blue == 0) {
      return GameState.BLUE_TEAM_WIN;
    }
    if (red == 0) {
      return GameState.RED_TEAM_WIN;
    }
    return GameState.IN_PROGRESS;
  }
}
