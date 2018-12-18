package core.commands;

import java.awt.image.BufferedImage;
import java.util.List;

public class ExecutionResult {
  private final String message;
  private final List<BufferedImage> images;
  private final List<GroupMessage> groupMessages;

  public ExecutionResult(String message, List<BufferedImage> images, List<GroupMessage> groupMessages) {
    this.message = message;
    this.images = images;
    this.groupMessages = groupMessages;
  }

  public static ExecutionResult create() {
    return new ExecutionResult(null, null, null);
  }

  public static ExecutionResult create(String message) {
    return new ExecutionResult(message, null, null);
  }

  public static ExecutionResult createOnlyImages(List<BufferedImage> images) {
    return new ExecutionResult(null, images, null);
  }

  public static ExecutionResult createImagesWithMessage(String message, List<BufferedImage> images) {
    return new ExecutionResult(message, images, null);
  }

  public static ExecutionResult createOnlyGroupMessage(List<GroupMessage> messages) {
    return new ExecutionResult(null, null, messages);
  }

  public String getMessage() {
    return message;
  }

  public List<BufferedImage> getImages() {
    return images;
  }

  public List<GroupMessage> getGroupMessages() {
    return groupMessages;
  }
}
