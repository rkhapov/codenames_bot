package bot;

import core.commands.CommandSelector;
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

    var cmd = CommandSelector.getCommandByString(text);
    var result = cmd.execute();

    try {
      sendMessage(result.getMessage(), id);
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
    var sendMessage = new SendMessage();
    sendMessage.setChatId(chatId);
    sendMessage.setText(message);

    execute(sendMessage);
  }
}
