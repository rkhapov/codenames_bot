package core.graphics;

import core.primitives.Field;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class FieldDrawer {

  public static BufferedImage getImageForCaptains(Field field) {
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
        var cardImage = CardDrawer.getCardImageForCaptain(card, maxCardHeight, maxCardWidth);
        var x = j * maxCardWidth;
        var y = i * maxCardHeight;

        graphics.drawImage(cardImage, x, y, null);
        graphics.drawRect(x, y, maxCardWidth, maxCardHeight);
      }
    }

    return image;
  }

  private static int getMaxCardHeight(Field field) {
    var max = -1;

    for (var c : field.getCards()) {
      max = Math.max(max, CardDrawer.getCardHeight(c));
    }

    return max;
  }

  private static int getMaxCardWidth(Field field) {
    var max = -1;

    for (var c : field.getCards()) {
      max = Math.max(max, CardDrawer.getCardWidth(c));
    }

    return max;
  }
}
