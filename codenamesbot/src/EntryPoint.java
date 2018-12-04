import bot.BotRunner;
import com.google.inject.Guice;
import core.game.IGame;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import tools.di.BasicModule;

public class EntryPoint {

  public static void main(String[] args) {
    try {
      var injector = Guice.createInjector(new BasicModule());
      var game = injector.getInstance(IGame.class);
      BotRunner.run(game);
    } catch (TelegramApiRequestException e) {
      e.printStackTrace();
    }
  }
}
