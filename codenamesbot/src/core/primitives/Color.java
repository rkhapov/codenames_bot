package core.primitives;

public enum Color {
  RED,
  BLUE,
  WHITE,
  BLACK;

  public Color getOpposite() {
    if (this == WHITE || this == BLACK)
      throw new IllegalStateException();
    return this == BLUE ? RED : BLUE;
  }

  public GameState getState() {
    if (this == WHITE || this == BLACK)
      throw new IllegalStateException();
    return this == BLUE ? GameState.BLUE_TEAM_WIN : GameState.RED_TEAM_WIN;
  }
}
