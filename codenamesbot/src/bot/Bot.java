package bot;

import core.generators.cards.CardsGenerator;
import core.generators.field.FieldGenerator;
import core.generators.words.WordsGenerator;
import core.primitives.Field;
import java.io.File;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import tools.ResourceProvider;

public class Bot extends TelegramLongPollingBot {

  public Bot(DefaultBotOptions options) {
    super(options);
  }

  @Override
  public void onUpdateReceived(Update update) {
    var message = update.getMessage();
    var text = message.getText();
    var id = update.getMessage().getChatId();
    var back = String.format("Congrats! You send: **%s**", text);

    try {
      sendMessage(back, id);
    } catch (TelegramApiException e) {
      e.printStackTrace();
    }
  }

  @Override
  public String getBotUsername() {
    return Constants.Name;
  }

  @Override
  public String getBotToken() {
    return Constants.Token;
  }

  private synchronized void sendMessage(String message, Long chatId) throws TelegramApiException {
    var sendPhoto = new SendPhoto();
    sendPhoto.setPhoto(new File(ResourceProvider.combinePathToResourcesDir("myimg.jpg")));
    sendPhoto.setChatId(chatId);

    execute(sendPhoto);
  }
}
