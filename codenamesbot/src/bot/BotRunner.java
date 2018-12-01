package bot;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.DefaultBotOptions.ProxyType;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

public class BotRunner {

  public static void run() throws TelegramApiRequestException {
    ApiContextInitializer.init();
    Authenticator.setDefault(new Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(
            Constants.ProxyUserName,
            Constants.ProxyPassword.toCharArray());
      }
    });

    var botOptions = ApiContext.getInstance(DefaultBotOptions.class);
    botOptions.setProxyHost(Constants.ProxyHost);
    botOptions.setProxyPort(Constants.ProxyPort);
    botOptions.setProxyType(ProxyType.SOCKS5);

    new TelegramBotsApi().registerBot(new Bot(botOptions));
  }
}
