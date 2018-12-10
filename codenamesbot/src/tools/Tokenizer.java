package tools;

import java.util.Arrays;
import java.util.List;

public class Tokenizer implements ITokenizer {

  @Override
  public List<String> getTokens(String line) {
    return Arrays.asList(line.split(" "));
  }
}
