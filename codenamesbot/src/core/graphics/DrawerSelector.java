package core.graphics;

import com.google.inject.Inject;
import core.graphics.field.IFieldDrawer;
import core.primitives.Rank;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DrawerSelector implements IDrawerSelector {

  private final Map<Rank, IFieldDrawer> rankToDrawer;

  @Inject
  public DrawerSelector(Set<IFieldDrawer> drawers) {
    rankToDrawer = new HashMap<>();

    for (var drawer : drawers) {
      rankToDrawer.put(drawer.getTargetRank(), drawer);
    }
  }

  @Override
  public IFieldDrawer getDrawerForRank(Rank rank) {
    return rankToDrawer.get(rank);
  }
}
