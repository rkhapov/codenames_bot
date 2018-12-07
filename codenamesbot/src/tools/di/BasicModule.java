package tools.di;

import bot.BotAuthenticationData;
import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import core.commands.GetCaptainPictureCommand;
import core.commands.GetPlayerPictureCommand;
import core.commands.ICommand;
import core.commands.OpenCardCommand;
import core.commands.StartNewGameCommand;
import core.commands.factory.CommandFactory;
import core.commands.factory.ICommandFactory;
import core.game.Game;
import core.game.IGame;
import core.generators.cards.CardsGenerator;
import core.generators.cards.ICardsGenerator;
import core.generators.field.FieldGenerator;
import core.generators.field.IFieldGenerator;
import core.generators.words.IWordsGenerator;
import core.generators.words.WordsGenerator;
import core.generators.words.dictionaries.IDictionary;
import core.generators.words.dictionaries.StandardDictionary;
import core.graphics.cards.CaptainCardDrawer;
import core.graphics.cards.PlayerCardDrawer;
import core.graphics.field.CaptainFieldDrawer;
import core.graphics.field.PlayerFieldDrawer;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.DefaultBotOptions.ProxyType;
import org.telegram.telegrambots.meta.ApiContext;
import tools.Utils;
import tools.ResourceProvider;

public class BasicModule extends AbstractModule {

  @Override
  public void configure() {
    bind(IWordsGenerator.class).to(WordsGenerator.class);
    bind(ICardsGenerator.class).to(CardsGenerator.class);
    bind(IFieldGenerator.class).to(FieldGenerator.class);
    bind(IGame.class).to(Game.class);
    bind(ICommandFactory.class).to(CommandFactory.class);

    bind(CaptainCardDrawer.class).asEagerSingleton();
    bind(PlayerCardDrawer.class).asEagerSingleton();
    bind(CaptainFieldDrawer.class).asEagerSingleton();
    bind(PlayerFieldDrawer.class).asEagerSingleton();

    bind(DefaultBotOptions.class).toInstance(getDefaultBotOptions());

    bind(ResourceProvider.class).toInstance(getResourceProvider());

    doMultiBindForCommands();
    doMultiBindForDictionaries();
  }

  private ResourceProvider getResourceProvider() {
    var pathToEntryPointClass =
        ResourceProvider.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    var pathToResourcesDir = Utils.combine(pathToEntryPointClass, "..", "..", "resources");

    return new ResourceProvider(pathToResourcesDir);
  }

  private void doMultiBindForCommands() {
    var binder = Multibinder.newSetBinder(binder(), ICommand.class);

    binder.addBinding().to(OpenCardCommand.class);
    binder.addBinding().to(StartNewGameCommand.class);
    binder.addBinding().to(GetCaptainPictureCommand.class);
    binder.addBinding().to(GetPlayerPictureCommand.class);
  }

  private void doMultiBindForDictionaries() {
    var binder = Multibinder.newSetBinder(binder(), IDictionary.class);

    binder.addBinding().to(StandardDictionary.class);
  }

  private DefaultBotOptions getDefaultBotOptions() {
    ApiContextInitializer.init();
    Authenticator.setDefault(new Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(
            BotAuthenticationData.PROXY_USER_NAME,
            BotAuthenticationData.PROXY_PASSWORD.toCharArray());
      }
    });

    var botOptions = ApiContext.getInstance(DefaultBotOptions.class);
    botOptions.setProxyHost(BotAuthenticationData.PROXY_HOST);
    botOptions.setProxyPort(BotAuthenticationData.PROXY_PORT);
    botOptions.setProxyType(ProxyType.SOCKS5);

    return botOptions;
  }
}
