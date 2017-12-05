package core;

import org.telegram.telegrambots.api.methods.send.SendDocument;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.File;
import java.util.HashMap;


public class DelPiero extends TelegramLongPollingBot {

    String token;
    String botName;
    private KeyboardsManager keyboards;
    private boolean isGroupChat;
    private HashMap<String, String[]> commands;


    public DelPiero() {
        TokenReader reader = new TokenReader();
        if (System.getProperty("os.name").equals("Mac OS X")) {
            reader.readConfigs("/Users/andreyprikhodko/job4j/file.txt");
        } else {
        reader.readConfigs("D:\\!job4j\\file.txt");
        }
        this.token = reader.getToken();
        this.botName = reader.getBotName();
        init();
    }

    @Override
    public void onUpdateReceived(Update update) {
        SendMessage sendMessage = new SendMessage()
                .setParseMode("HTML")
                .setChatId(update.getMessage().getChatId())
                .setText("<img src=\"http://i.stack.imgur.com/SBv4T.gif\" title=\"this slowpoke moves\" />");
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        startLogic(update);
    }

    public void startLogic(Update update) {
        if (update.hasMessage()) {
            isGroupChat = update.getMessage().isGroupMessage();
            if (!isGroupChat) {
                if (commands.containsKey(update.getMessage().getText())) {
                    String[] data = commands.get(update.getMessage().getText());
                    sendReplyMessage(update, data[0], data[1]);
                }
            } else {
                String command = update.getMessage().getText().split("@")[0];
                if (commands.containsKey(command)) {
                    String[] data = commands.get(command);
                    sendReplyMessage(update, data[0], data[1]);
                }
            }
        }
        if (update.hasCallbackQuery()) {
            sendInlineMessage(update);
        }
    }


        @Override
        public String getBotUsername () {
            return botName;
        }

        @Override
        public String getBotToken () {
            return token;
        }

    public void init() {
        commands = new HashMap<>();
        commands.put("/start", new String[]{"Привет! Меня зовут Дел. Чем могу помочь?", "Убрать"});
        commands.put("/schedule", new String[]{"Куда желаете отправиться?", "Расписание"});
        commands.put("/news", new String[]{"Какие новости вы бы хотели прочитать?", "Новости"});
        commands.put("/weather", new String[]{"Какая погода вас интересует?", "Погода"});
        commands.put("/cafe", new String[]{"Выбирете пункт меню.", "Заведения"});
        commands.put("/zephyr", new String[]{"Выбирете пункт меню.", "Кинотеатр"});
        commands.put("/cafe", new String[]{"Выбирете пункт меню.", "Заведения"});
        commands.put("/newspapers", new String[]{"Какую газету вы бы хотели прочитать?", "Газеты"});
        this.keyboards = new KeyboardsManager();
    }

    public void sendReplyMessage(Update update, String answer, String keyboard) {
        SendMessage message = new SendMessage()
                .setChatId(update.getMessage().getChatId())
                .setText(answer)
                .enableMarkdown(false);
        if (keyboard != null) {
            message.setReplyMarkup(keyboards.choiceKeyboard(keyboard));
        } else {
            message.setReplyMarkup(new ReplyKeyboardMarkup());
        }
        try {
            execute(message);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendInlineMessage(Update update) {
        String call_data = update.getCallbackQuery().getData();
        long message_id = update.getCallbackQuery().getMessage().getMessageId();
        long chat_id = update.getCallbackQuery().getMessage().getChatId();
        String[] data = keyboards.getCallbackKeyboards().get(call_data);
        EditMessageText new_message = new EditMessageText()
                .setChatId(chat_id)
                .setMessageId((int) message_id)
                .enableMarkdown(false)
                .setText(data[1]);
        if (data[0] != null) {
            new_message.setReplyMarkup((InlineKeyboardMarkup) keyboards.choiceKeyboard(data[0]));
        }
        run(call_data, chat_id);
        try {
            execute(new_message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void run(String call_data, Long chatId) {
        switch (call_data) {
            case "download_jpeg":
                if (System.getProperty("os.name").equals("Mac OS X")) {
                    sendLocalPhoto("/Users/andreyprikhodko/job4j/telegram/График автобусов.jpg", chatId);
                } else {
                    sendLocalPhoto("D:\\!job4j\\telegram\\График автобусов.jpg", chatId);
                }
                break;
            case "download_doc":
                if (System.getProperty("os.name").equals("Mac OS X")) {
                    sendLocalFile("/Users/andreyprikhodko/job4j/telegram/График автобусов.doc", chatId);
                } else {
                    sendLocalFile("D:\\!job4j\\telegram\\График автобусов.doc", chatId);
                }
        }

    }

    public void sendLocalFile(String filePath, Long chatId) {
        SendDocument sendDocument = new SendDocument();
        sendDocument.setChatId(chatId);
        File file = new File(filePath);
        sendDocument.setNewDocument(file);
        sendDocument.setCaption("hehe");
        try {
            Message message = sendDocument(sendDocument);
            System.out.println(message.toString());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendLocalPhoto(String filePath, Long chatId) {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatId);
        File file = new File(filePath);
        sendPhoto.setNewPhoto(file);
        try {
            sendPhoto(sendPhoto);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
