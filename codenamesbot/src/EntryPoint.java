import bot.BotRunner;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

public class EntryPoint {
  public static void main(String[] args) {
    try {
      BotRunner.run();
    } catch (TelegramApiRequestException e) {
      e.printStackTrace();
    }
  }
}
