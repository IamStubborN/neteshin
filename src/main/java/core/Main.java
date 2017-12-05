package core;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;
public class Main {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        Weather weather = new Weather();
        try {
            telegramBotsApi.registerBot(new DelPiero());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}
