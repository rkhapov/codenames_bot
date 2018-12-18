package bot;

import com.google.inject.Inject;
import core.commands.ExecutionResult;
import core.commands.invoker.ICommandInvoker;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {

  private final ICommandInvoker commandInvoker;

  @Inject
  public Bot(DefaultBotOptions options, ICommandInvoker commandFactory) {
    super(options);
    this.commandInvoker = commandFactory;
  }

  @Override
  public void onUpdateReceived(Update update) {
    var message = update.getMessage();
    var text = message.getText();
    var id = update.getMessage().getChatId();

    var result = commandInvoker.execute(text, update.getMessage().getChat().getUserName(), id);

    sendResult(id, result);
  }

  private void sendResult(Long id, ExecutionResult result) {
    try {
      if (result.getMessage() != null) {
        sendMessage(result.getMessage(), id);
      }

      if (result.getImages() != null) {
        for (var img : result.getImages()) {
          sendPhoto(id, img);
        }
      }

      if (result.getGroupMessages() != null) {
        for (var msg: result.getGroupMessages()) {
          for (var u : msg.getReceivers()) {
            sendMessage(msg.getMessage(), u.getChatId());
          }
        }
      }
    } catch (TelegramApiException e) {
      e.printStackTrace();
    }
  }

  @Override
  public String getBotUsername() {
    return BotAuthenticationData.NAME;
  }

  @Override
  public String getBotToken() {
    return BotAuthenticationData.TOKEN;
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
