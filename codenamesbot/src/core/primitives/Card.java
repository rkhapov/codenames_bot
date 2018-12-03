package core.primitives;

public class Card {

  private final String word;
  private final Color color;
  private boolean isOpen;

  public Card(String word, Color color) {
    this.word = word;
    this.color = color;
    isOpen = false;
  }

  public static Card create(String word, Color color) {
    return new Card(word, color);
  }

  public String getWord() {
    return word;
  }

  public Color getColor() {
    return color;
  }

  public void open() {
    isOpen = true;
  }

  public void close() {
    isOpen = false;
  }

  public boolean isOpen() {
    return isOpen;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }

    if (obj == this) {
      return true;
    }

    if (obj instanceof Card) {
      var card = (Card) obj;

      return word.equals(card.word) && color.equals(card.color);
    }

    return false;
  }

  @Override
  public int hashCode() {
    return (word.hashCode() * 1033) ^ color.hashCode();
  }

  @Override
  public String toString() {
    return String.format("Card %s %s", color, word);
  }
}
