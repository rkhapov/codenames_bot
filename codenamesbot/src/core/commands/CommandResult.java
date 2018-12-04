package core.commands;

import java.awt.image.BufferedImage;
import java.util.List;

public class CommandResult {
  private final String message;
  private final List<BufferedImage> images;

  public CommandResult(String message, List<BufferedImage> images) {
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
