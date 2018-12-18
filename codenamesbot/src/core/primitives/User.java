package core.primitives;

public class User {
  private final String name;
  private final Rank rank;
  private final Long chatId;

  public User(String name, Rank rank, Long chatId) {
    this.name = name;
    this.rank = rank;
    this.chatId = chatId;
  }

  public Rank getRank(){
    return rank;
  }

  public String getName() {
    return name;
  }

  public Long getChatId() { return chatId; }
}
