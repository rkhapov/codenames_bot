package tests;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tools.Tokenizer;

public class TokenizerUserTests {

  private Tokenizer tokenizer;

  @BeforeEach
  public void init() {
    tokenizer = new Tokenizer();
  }

  @Test
  public void getTokens_withNullLine_shouldReturnEmptyList() {
    var sut = tokenizer.getTokens(null);

    ;
  }


}
