import bot.BotRunner;
import com.google.inject.Guice;
import core.generators.GeneratorException;
import core.generators.cards.ICardsGenerator;
import core.generators.words.IWordsGenerator;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import tools.di.BasicModule;

public class EntryPoint {
  public static void main(String[] args) {
    try {
      var injector = Guice.createInjector(new BasicModule());
      var gen = injector.getInstance(ICardsGenerator.class);

      for (var c: gen.getCards(20, 6, 7)) {
        System.out.println(c);
      }

//      BotRunner.run();
    }
    catch (Exception e) {

    }
//    catch (TelegramApiRequestException e) {
//      e.printStackTrace();
//    }
  }
}
