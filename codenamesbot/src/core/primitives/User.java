package core.primitives;

public class User {
  private final String name;
  private final Rank rank;
  private final Long chatId;
  private final Color color;

  public User(String name, Rank rank, Long chatId, Color color) {
    this.name = name;
    this.rank = rank;
    this.chatId = chatId;
    this.color = color;
  }

  public Rank getRank(){
    return rank;
  }

  public String getName() {
    return name;
  }

  public Long getChatId() { return chatId; }

  public Color getColor() {
    return color;
  }
}
