import bot.BotRunner;
import com.google.inject.Guice;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import tools.di.BasicModule;

public class EntryPoint {

  public static void main(String[] args) {
    try {
      var injector = Guice.createInjector(new BasicModule());
      BotRunner.run();
    } catch (TelegramApiRequestException e) {
      e.printStackTrace();
    }
  }
}
