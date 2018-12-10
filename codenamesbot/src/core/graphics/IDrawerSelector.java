package core.graphics;

import core.graphics.field.IFieldDrawer;
import core.primitives.Rank;

public interface IDrawerSelector {

  IFieldDrawer getDrawerForRank(Rank rank);
}
