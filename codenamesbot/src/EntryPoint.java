import bot.Bot;
import com.google.inject.Guice;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import tools.di.BasicModule;

public class EntryPoint {

  public static void main(String[] args) {
    try {
      var injector = Guice.createInjector(new BasicModule());
      var bot = injector.getInstance(Bot.class);

      new TelegramBotsApi().registerBot(bot);
    } catch (TelegramApiRequestException e) {
      e.printStackTrace();
    }
  }
}
