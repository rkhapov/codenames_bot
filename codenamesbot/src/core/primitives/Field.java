package core.primitives;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Field {

  private final List<Card> cards;
  private final Map<String, Card> wordToCard;
  private final int height;
  private final int width;

  public Field(List<Card> field, int height, int width) {
    this.cards = field;
    this.height = height;
    this.width = width;
    wordToCard = new HashMap<>();

    for (var i = 0; i < field.size(); i++){
      wordToCard.put(field.get(i).getWord(), field.get(i));
    }
  }

  public Card getCard(String word) {
    return wordToCard.get(word);
  }

  public int getHeight() {
    return height;
  }

  public int getWidth() {
    return width;
  }

  public List<Card> getCards() {
    return cards;
  }

  public Card open(String word) {
    var card = getCard(word);

    card.open();

    return card;
  }

  public Card get(int row, int column) {
    return cards.get(row * width + column);
  }
}
