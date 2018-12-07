package core.graphics.field;

import core.primitives.Field;
import java.awt.image.BufferedImage;

public interface IFieldDrawer {
  BufferedImage getImage(Field field);
}
