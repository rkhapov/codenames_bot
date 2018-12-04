package core.commands;

import core.game.IGame;
import java.awt.image.BufferedImage;
import java.util.List;

public class CommandResult {
  private final String message;
  private final List<BufferedImage> images;
  private final IGame newGame;

  public CommandResult(String message, List<BufferedImage> images) {
    this.message = message;
    this.images = images;
    this.newGame = null;
  }

  public CommandResult(String message, List<BufferedImage> images, IGame newGame) {
    this.message = message;
    this.images = images;
    this.newGame = newGame;
  }

  public String getMessage() {
    return message;
  }

  public List<BufferedImage> getImages() {
    return images;
  }

  public IGame getNewGame() {
    return newGame;
  }
}
