package core.primitives;

public enum Color {
  Red,
  Blue,
  White,
  Black;

  public Color getOpposite() {
    if (this == White || this == Black)
      throw new IllegalStateException();
    return this == Blue ? Red : Blue;
  }

  public GameState getState() {
    if (this == White || this == Black)
      throw new IllegalStateException();
    return this == Blue ? GameState.BlueTeamWin : GameState.RedTeamWin;
  }
}
