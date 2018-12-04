package core.graphics;

import core.primitives.Card;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.image.BufferedImage;

public class CardDrawer {

  private final static Font font;
  private static final FontMetrics metrics;

  static {
    font = new Font("Serif", Font.PLAIN, 30);
    metrics = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB).getGraphics().getFontMetrics(font);
  }

  public static int getCardHeight(Card card) {
    return metrics.getHeight() * 2;
  }

  public static int getCardWidth(Card card) {
    return metrics.stringWidth(card.getWord()) * 2;
  }

  public static BufferedImage getCardImageForCaptain(Card card, int height, int width) {
    return getCardImage(card, height, width, true);
  }

  public static BufferedImage getCardImageForPlayer(Card card, int height, int weight){
    return getCardImage(card, height, weight, false);
  }

  private static BufferedImage getCardImage(Card card, int height, int width, boolean isCaptain){
    height = Math.max(height, getCardHeight(card));
    width = Math.max(width, getCardWidth(card));

    var image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    var graphics = image.getGraphics();

    graphics.setColor(getBackgroundColorForCard(card, isCaptain));
    graphics.fillRect(0, 0, width, height);
    graphics.setColor(getTextColorForCard(card, isCaptain));
    graphics.setFont(font);
    graphics.drawString(card.getWord(), metrics.getHeight(), metrics.getHeight());

    return image;
  }


  private static Color getBackgroundColorForCard(Card card, boolean isCaptain) {
    var color = card.getColor();

    if (!isCaptain && !card.isOpen()) {
      return Color.LIGHT_GRAY;
    }

    if (color == core.primitives.Color.Black) {
      return Color.DARK_GRAY;
    }

    if (color == core.primitives.Color.White) {
      return Color.LIGHT_GRAY;
    }

    if (color == core.primitives.Color.Red) {
      return Color.RED;
    }

    return new Color(0x42aaff);
  }

  private static Color getTextColorForCard(Card card, boolean isCaptain) {
    var color = card.getColor();

    if(!isCaptain && !card.isOpen()){
      return Color.BLACK;
    }

    if (color == core.primitives.Color.Black) {
      return Color.LIGHT_GRAY;
    }

    if (color == core.primitives.Color.White) {
      return Color.BLACK;
    }

    if (color == core.primitives.Color.Red) {
      return Color.BLACK;
    }

    return Color.LIGHT_GRAY;
  }
}
