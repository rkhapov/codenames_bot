package bot;

import com.google.inject.Guice;
import core.commands.CommandResult;
import core.commands.CommandSelector;
import core.game.IGame;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import javax.imageio.ImageIO;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {

  private IGame game;

  public Bot(DefaultBotOptions options, IGame game) {
    super(options);
    this.game = game;
  }

  @Override
  public void onUpdateReceived(Update update) {
    var message = update.getMessage();
    var text = message.getText();
    var id = update.getMessage().getChatId();
    var parts = text.split(" ");

    var cmd = CommandSelector.getCommandByString(parts[0]);
    var result = cmd != null ? cmd.execute(game, Arrays.asList(parts))
        : new CommandResult("Unknown command", null);

    try {
      if (result.getMessage() != null) {
        sendMessage(result.getMessage(), id);
      }
      if (result.getImages() != null) {
        for (var img : result.getImages()) {
          sendPhoto(id, img);
        }
      }
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

  private synchronized void sendPhoto(Long chatId, BufferedImage image)
      throws TelegramApiException {
    var sendPhoto = new SendPhoto();
    sendPhoto.setChatId(chatId);

    sendPhoto.setPhoto("aa", bufferedImgToInputStream(image));

    execute(sendPhoto);
  }

  private synchronized void sendMessage(String message, Long chatId) throws TelegramApiException {
    var sendMessage = new SendMessage();
    sendMessage.setChatId(chatId);
    sendMessage.setText(message);

    execute(sendMessage);
  }

  private static InputStream bufferedImgToInputStream(BufferedImage image) {
    var os = new ByteArrayOutputStream();
    try {
      ImageIO.write(image, "jpg", os);
    } catch (IOException e) {
      //impossible
    }
    return new ByteArrayInputStream(os.toByteArray());
  }
}
