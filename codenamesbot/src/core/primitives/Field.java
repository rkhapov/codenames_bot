package core.primitives;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Field {

  private final List<List<Card>> field;
  private final Map<String, Card> wordToCard;
  private final int height;
  private final int width;

  public Field(List<List<Card>> field) {
    this.field = field;
    height = field.size();
    width = field.get(0).size();
    wordToCard = new HashMap<>();

    for (var i = 0; i < height; i++) {
      for (var j = 0; j < width; j++) {
        var card = field.get(i).get(j);

        wordToCard.put(card.getWord(), card);
      }
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
    return new ArrayList<>(wordToCard.values());
  }

  public Card open(String word) {
    var card = getCard(word);

    card.open();

    return card;
  }

  public Card get(int i, int j) {
    return field.get(i).get(j);
  }
}
