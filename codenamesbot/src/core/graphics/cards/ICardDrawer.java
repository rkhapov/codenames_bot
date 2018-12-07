package core.graphics.cards;

import core.primitives.Card;
import java.awt.image.BufferedImage;

public interface ICardDrawer {

  int getImageHeightFor(Card card);

  int getImageWidthFor(Card card);

  BufferedImage getImage(Card card, int minHeight, int minWidth);
}