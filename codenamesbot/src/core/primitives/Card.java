package core.primitives;

public class Card {
  private final String word;
  private final Color color;

  public Card(String word, Color color) {
    this.word = word;
    this.color = color;
  }

  public String getWord() {
    return word;
  }

  public Color getColor() {
    return color;
  }

  @Override
  public boolean equals(Object obj) {
    throw new tools.NotImplementedException();
  }

  @Override
  public int hashCode() {
    throw new tools.NotImplementedException();
  }
}
