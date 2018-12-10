package core.graphics.field;

import com.google.inject.Inject;
import core.graphics.cards.CaptainCardDrawer;
import core.primitives.Rank;

public class CaptainFieldDrawer extends FieldDrawer {

  @Inject
  public CaptainFieldDrawer(CaptainCardDrawer cardDrawer) {
    super(cardDrawer);
  }

  @Override
  public Rank getTargetRank() {
    return Rank.CAPTAIN;
  }
}
