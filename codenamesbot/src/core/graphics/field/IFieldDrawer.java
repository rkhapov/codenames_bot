package core.graphics.field;

import core.primitives.Field;
import core.primitives.Rank;
import java.awt.image.BufferedImage;

public interface IFieldDrawer {
  BufferedImage getImage(Field field);
  Rank getTargetRank();
}
