package tests;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import core.graphics.DrawerSelector;
import core.graphics.field.CaptainFieldDrawer;
import core.graphics.field.PlayerFieldDrawer;
import core.primitives.Rank;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DrawerSelectorUnitTests {

  private DrawerSelector selector;
  private PlayerFieldDrawer playerFieldDrawer;
  private CaptainFieldDrawer captainFieldDrawer;

  @BeforeEach
  public void init() {
    playerFieldDrawer = mock(PlayerFieldDrawer.class);
    captainFieldDrawer = mock(CaptainFieldDrawer.class);

    when(playerFieldDrawer.getTargetRank()).thenReturn(Rank.PLAYER);
    when(captainFieldDrawer.getTargetRank()).thenReturn(Rank.CAPTAIN);

    selector = new DrawerSelector(Set.of(playerFieldDrawer, captainFieldDrawer));
  }

  @Test
  public void getDrawerForRank_withPlayerRank_shouldReturnPlayerDrawer() {
    var sut = selector.getDrawerForRank(Rank.PLAYER);

    assertThat(sut).isEqualTo(playerFieldDrawer);
  }

  @Test
  public void getDrawerForRank_withCaptainRank_shouldReturnCaptainDrawer() {
    var sut = selector.getDrawerForRank(Rank.CAPTAIN);

    assertThat(sut).isEqualTo(captainFieldDrawer);
  }
}
