package core.generators;

import core.tools.CoreException;

public class GeneratorException extends CoreException {
  public GeneratorException() {
    super("Generator error");
  }

  public GeneratorException(String message) {
    super(message);
  }
}
