package core.graphics.field;

import core.graphics.cards.ICardDrawer;
import core.primitives.Field;
import java.awt.Color;
import java.awt.image.BufferedImage;

public abstract class FieldDrawer implements IFieldDrawer {

  private final ICardDrawer cardDrawer;

  protected FieldDrawer(ICardDrawer cardDrawer) {
    this.cardDrawer = cardDrawer;
  }

  @Override
  public BufferedImage getImage(Field field) {
    var maxCardWidth = getMaxCardWidth(field);
    var maxCardHeight = getMaxCardHeight(field);
    var height = field.getHeight();
    var width = field.getWidth();
    var image = new BufferedImage(
        width * maxCardWidth,
        height * maxCardHeight,
        BufferedImage.TYPE_INT_RGB);
    var graphics = image.createGraphics();

    graphics.setColor(Color.BLACK);

    for (var i = 0; i < height; i++) {
      for (var j = 0; j < width; j++) {
        var card = field.get(i, j);
        var cardImage = cardDrawer.getImage(card, maxCardHeight, maxCardWidth);
        var x = j * maxCardWidth;
        var y = i * maxCardHeight;

        graphics.drawImage(cardImage, x, y, null);
        graphics.drawRect(x, y, maxCardWidth, maxCardHeight);
      }
    }

    return image;
  }

  private int getMaxCardHeight(Field field) {
    var max = -1;

    for (var c : field.getCards()) {
      max = Math.max(max, cardDrawer.getImageHeightFor(c));
    }

    return max;
  }

  private int getMaxCardWidth(Field field) {
    var max = -1;

    for (var c : field.getCards()) {
      max = Math.max(max, cardDrawer.getImageWidthFor(c));
    }

    return max;
  }
}
