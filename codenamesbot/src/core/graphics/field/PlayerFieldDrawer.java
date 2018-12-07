package core.graphics.field;

import com.google.inject.Inject;
import core.graphics.cards.ICardDrawer;
import core.graphics.cards.PlayerCardDrawer;
import core.primitives.Rank;

public class PlayerFieldDrawer extends FieldDrawer {

  @Inject
  public PlayerFieldDrawer(PlayerCardDrawer cardDrawer) {
    super(cardDrawer);
  }
}
