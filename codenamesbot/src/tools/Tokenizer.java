package tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tokenizer implements ITokenizer {

  @Override
  public List<String> getTokens(String line) {
    if (line == null || line.length() == 0) {
      return new ArrayList<>();
    }

    return Arrays.asList(line.split(" "));
  }
}
