package core.commands;

import core.game.IGame;
import java.awt.image.BufferedImage;
import java.util.List;

public class ExecuteResult {
  private final String message;
  private final List<BufferedImage> images;

  public ExecuteResult(String message) {
    this.message = message;
    images = null;
  }

  public ExecuteResult(String message, List<BufferedImage> images) {
    this.message = message;
    this.images = images;
  }

  public String getMessage() {
    return message;
  }

  public List<BufferedImage> getImages() {
    return images;
  }
}
