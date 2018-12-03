package core.primitives;

import core.tools.NotImplementedException;

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
    throw new NotImplementedException();
  }

  @Override
  public int hashCode() {
    throw new NotImplementedException();
  }
}
