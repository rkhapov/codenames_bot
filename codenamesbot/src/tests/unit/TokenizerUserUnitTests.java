package tests.unit;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tools.Tokenizer;

public class TokenizerUserUnitTests {

  private Tokenizer tokenizer;

  @BeforeEach
  public void init() {
    tokenizer = new Tokenizer();
  }

  @Test
  public void getTokens_withNullLine_shouldReturnEmptyList() {
    var sut = tokenizer.getTokens(null);

    assertThat(sut).isEmpty();
  }

  @Test
  public void getTokens_withEmptyString_shouldReturnEmptyList() {
    var sut = tokenizer.getTokens("");

    assertThat(sut).isEmpty();
  }

  @Test
  public void getTokens_withString_shouldReturnListOfSplittedBySpace() {
    var sut = tokenizer.getTokens("sdfsd sdfsd gf");
    var expected = List.of("sdfsd", "sdfsd", "gf");

    assertThat(sut).isEqualTo(expected);
  }
}
