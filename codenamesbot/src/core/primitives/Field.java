package core.primitives;

import com.google.inject.Inject;
import core.Constants;
import core.generators.GeneratorException;
import core.generators.cards.ICardsGenerator;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.font.FontRenderContext;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import tools.Pair;
import tools.ResourceProvider;

public class Field {

  private static final Font serif = new Font("Serif", Font.PLAIN, 30);
  private static final FontMetrics serifMetrics;

  static {
    var fooGraphics = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB).getGraphics();
    serifMetrics = fooGraphics.getFontMetrics(serif);
  }

  private List<List<Card>> field;
  private Map<String, Card> wordToCard;
  private int height;
  private int width;

  @Inject
  public Field(ICardsGenerator cardsGenerator) {
    try {
      var p = generate(cardsGenerator,
          Constants.FieldHeight, Constants.FieldWidth,
          Constants.BlueWordsCount, Constants.RedWordsCount);

      field = p.first();
      wordToCard = p.second();
      height = Constants.FieldHeight;
      width = Constants.FieldWidth;

    } catch (GeneratorException e) {
      //todo: make clean
      e.printStackTrace();
      System.exit(1);
    }
  }

  public Card getCard(String word) {
    return wordToCard.get(word);
  }

  public List<Card> getCards() {
    return new ArrayList<>(wordToCard.values());
  }

  public Card open(String word) {
    var card = getCard(word);

    card.open();

    return card;
  }

  public BufferedImage getImageForPlayers() {
    var image = new BufferedImage(
        width * getMaxCardWidth(), height * getMaxCardHeight(),
        BufferedImage.TYPE_INT_RGB);
    var graphics = image.createGraphics();
    System.out.println(image.getHeight());
    System.out.println(image.getWidth());

    for (var i = 0; i < height; i++) {
      for (var j = 0; j < width; j++) {
        var cardImage = getCardImage(field.get(i).get(j));
        graphics.drawImage(cardImage, j * getMaxCardWidth(), i * getMaxCardHeight(), null);
        System.out.println(field.get(i).get(j).getWord());

        try {
          ImageIO.write(cardImage, "jpg", new File(ResourceProvider.combinePathToResourcesDir(field.get(i).get(j).getWord() + ".jpg")));
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }

    try {
      ImageIO
          .write(image, "jpg", new File(ResourceProvider.combinePathToResourcesDir("myimg.jpg")));
    } catch (IOException e) {
      e.printStackTrace();
    }

    return image;
  }

  private BufferedImage getCardImage(Card card) {
    var height = getMaxCardHeight();
    var width = getMaxCardWidth();

    var image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    var graphics = image.createGraphics();

    graphics.setColor(getBackgroundColorForCard(card));
    graphics.fillRect(0, 0, width, height);
    graphics.setColor(getTextColorForCard(card));
    graphics.setFont(serif);
    graphics.drawString(card.getWord(), serifMetrics.getHeight(), serifMetrics.getHeight());

    return image;
  }

  private int getCardWidth(Card card) {
    return serifMetrics.stringWidth(card.getWord()) * 2;
  }

  private int getCardHeight(Card card) {
    return serifMetrics.getHeight() * 2;
  }

  private int getMaxCardWidth() {
    var max = -1;

    for (var c: wordToCard.values()) {
      max = Math.max(max, getCardWidth(c));
    }

    return max;
  }

  private int getMaxCardHeight() {
    return getCardHeight(null);
  }

  private Color getBackgroundColorForCard(Card card) {
    var color = card.getColor();

    if (color == core.primitives.Color.Black) {
      return Color.DARK_GRAY;
    }

    if (color == core.primitives.Color.White) {
      return Color.LIGHT_GRAY;
    }

    if (color == core.primitives.Color.Red) {
      return Color.RED;
    }

    return Color.BLUE;
  }

  private Color getTextColorForCard(Card card) {
    var color = card.getColor();

    if (color == core.primitives.Color.Black) {
      return Color.LIGHT_GRAY;
    }

    if (color == core.primitives.Color.White) {
      return Color.BLACK;
    }

    if (color == core.primitives.Color.Red) {
      return Color.BLACK;
    }

    return Color.BLACK;
  }

  private Pair<List<List<Card>>, Map<String, Card>> generate(
      ICardsGenerator cardsGenerator,
      int height, int width, int blue, int red) throws GeneratorException {
    var field = new ArrayList<List<Card>>(height);
    var cards = cardsGenerator.getCards(height * width, blue, red);
    var wordToCard = new HashMap<String, Card>();
    var iter = cards.iterator();

    for (var i = 0; i < height; i++) {
      field.add(new ArrayList<>(width));

      for (var j = 0; j < width; j++) {
        var card = iter.next();

        field.get(i).add(card);
        wordToCard.put(card.getWord(), card);
      }
    }

    return Pair.create(field, wordToCard);
  }
}
