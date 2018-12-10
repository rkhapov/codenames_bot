package core.graphics.cards;

import core.primitives.Card;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.image.BufferedImage;

public abstract class CardDrawer implements ICardDrawer {

  protected final Font font;
  protected final FontMetrics metrics;

  public CardDrawer() {
    font = new Font("Serif", Font.PLAIN, 30);
    metrics = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB).getGraphics().getFontMetrics(font);
  }

  @Override
  public int getImageHeightFor(Card card) {
    return metrics.getHeight() * 2;
  }

  @Override
  public int getImageWidthFor(Card card) {
    return metrics.stringWidth(card.getWord()) * 2;
  }

  protected abstract Color getBackgroundColor(Card card);

  protected abstract Color getTextColor(Card card);

  public BufferedImage getImage(Card card, int minHeight, int minWidth) {
    return buildCardImage(card, minHeight, minWidth, getBackgroundColor(card), getTextColor(card));
  }

  private BufferedImage buildCardImage(
      Card card, int height, int width,
      Color backgroundColor, Color textColor) {
    height = Math.max(height, getImageHeightFor(card));
    width = Math.max(width, getImageWidthFor(card));

    var image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    var graphics = image.getGraphics();

    graphics.setColor(backgroundColor);
    graphics.fillRect(0, 0, width, height);
    graphics.setColor(textColor);
    graphics.setFont(font);
    graphics.drawString(card.getWord(), metrics.getHeight(), metrics.getHeight());

    return image;
  }

  protected Color getStandardBackgroundColor(Card card) {
    var color = card.getColor();

    if (color == core.primitives.Color.BLACK) {
      return Color.DARK_GRAY;
    }

    if (color == core.primitives.Color.WHITE) {
      return Color.LIGHT_GRAY;
    }

    if (color == core.primitives.Color.RED) {
      return Color.RED;
    }

    return new Color(0x42aaff);
  }

  protected Color getStandardTextColor(Card card) {
    var color = card.getColor();

    if (color == core.primitives.Color.BLACK) {
      return Color.LIGHT_GRAY;
    }

    if (color == core.primitives.Color.WHITE) {
      return Color.BLACK;
    }

    if (color == core.primitives.Color.RED) {
      return Color.BLACK;
    }

    return Color.LIGHT_GRAY;
  }
}
