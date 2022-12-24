package my.uum;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

/**
 * This class is to register and link with Telegram API so that Telegram Bots can be run successfully.
 *
 * @author Tan Xue Lee (277407)
 */

public class App {

    DatabaseConnect databaseConnect = null;

    public App() {
        databaseConnect = new DatabaseConnect();
    }

    public static void main(String[] args) {
        App app = new App();
        try {
            TelegramBotsApi telegramBot = new TelegramBotsApi(DefaultBotSession.class);
            telegramBot.registerBot(new TelegramBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
