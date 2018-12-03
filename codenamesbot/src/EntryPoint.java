import bot.BotRunner;
import com.google.inject.Guice;
import core.primitives.Field;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import tools.di.BasicModule;

public class EntryPoint {
  public static void main(String[] args) {

    var injector = Guice.createInjector(new BasicModule());
    var field = injector.getInstance(Field.class);

    try {
      BotRunner.run();
    } catch (TelegramApiRequestException e) {
      e.printStackTrace();
    }
  }
}
