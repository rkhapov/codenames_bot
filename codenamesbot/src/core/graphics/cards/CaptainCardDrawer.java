package core.graphics.cards;

import core.primitives.Card;
import core.primitives.Rank;
import java.awt.Color;

public class CaptainCardDrawer extends CardDrawer {

  @Override
  protected Color getBackgroundColor(Card card) {
    return getStandardBackgroundColor(card);
  }

  @Override
  protected Color getTextColor(Card card) {
    return getStandardTextColor(card);
  }
}
