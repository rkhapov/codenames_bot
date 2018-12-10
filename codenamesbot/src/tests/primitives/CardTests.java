package tests.primitives;

import static org.junit.jupiter.api.Assertions.*;

import core.primitives.Card;
import core.primitives.Color;
import org.junit.jupiter.api.Test;

public class CardTests {

  @Test
  public void testConstructor() {
    var card = new Card("NewCard", Color.Blue);
    assertEquals("NewCard", card.getWord());
    assertEquals(Color.Blue, card.getColor());
    assertFalse(card.isOpen());
  }

  @Test
  public void testCreation() {
    var card = Card.create("NewCard", Color.Red);
    assertEquals("NewCard", card.getWord());
    assertEquals(Color.Red, card.getColor());
    assertFalse(card.isOpen());
  }

  @Test
  public void testOpen() {
    var card = Card.create("NewCard", Color.Black);
    assertFalse(card.isOpen());
    card.open();
    assertTrue(card.isOpen());
  }

  @Test
  public void testClose() {
    var card = new Card("New", Color.Blue);
    card.open();
    assertTrue(card.isOpen());
    card.close();
    assertFalse(card.isOpen());
  }

  @Test
  public void testEquals() {
    var card1 = new Card("New", Color.White);
    var card2 = new Card("New", Color.White);
    assertEquals(card1, card1);
    assertEquals(card1, card2);
  }

  @Test
  public void testHashCode() {
    var card1 = new Card("New", Color.White);
    var card2 = new Card("New", Color.White);
    assertEquals(card1.hashCode(), card2.hashCode());
  }
}
