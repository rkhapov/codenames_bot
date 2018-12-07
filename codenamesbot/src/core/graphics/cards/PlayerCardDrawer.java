package core.graphics.cards;

import core.primitives.Card;
import core.primitives.Rank;
import java.awt.Color;

public class PlayerCardDrawer extends CardDrawer {

  @Override
  protected Color getBackgroundColor(Card card) {
    if (card.isOpen())
      return getStandardBackgroundColor(card);

    return Color.GRAY;
  }

  @Override
  protected Color getTextColor(Card card) {
    if (card.isOpen())
      return getStandardTextColor(card);

    return Color.BLACK;
  }
}
