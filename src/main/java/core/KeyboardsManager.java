package core;

import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KeyboardsManager {
    HashMap<String, ReplyKeyboard> allKeyboards = new HashMap<>();
    HashMap<String, String[]> callbackKeyboards = new HashMap<>();

    public KeyboardsManager() {
        initAllKeyboard();
    }

    public void initAllKeyboard() {
        // No keyboard
        ReplyKeyboardRemove keyboardRemove = new ReplyKeyboardRemove();
        allKeyboards.put("Убрать", keyboardRemove);

        // New keyboard Расписание
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton().setText("Городские автобусы").setCallbackData("city_buses"));
        rowInline.add(new InlineKeyboardButton().setText("Межгород").setCallbackData("intercity_buses"));
        rowsInline.add(rowInline);
        markupInline.setKeyboard(rowsInline);
        allKeyboards.put("Расписание", markupInline);
        callbackKeyboards.put("city_buses", new String[] {"Городские автобусы", "В каком направлении вы хотите поехать?"});
        callbackKeyboards.put("intercity_buses", new String[] {"Межгород", "Введите название города откуда вы уезжаете."});

        // New keyboard Городские автобусы
        markupInline = new InlineKeyboardMarkup();
        rowsInline = new ArrayList<>();
        rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton().setText("Ближайшие на ХАЭС").setCallbackData("coming_to_KHNPP"));
        rowInline.add(new InlineKeyboardButton().setText("Ближайшие в город").setCallbackData("coming_to_town"));
        rowsInline.add(rowInline);
        rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton().setText("На Кривин").setCallbackData("coming_to_Krivin"));
        rowInline.add(new InlineKeyboardButton().setText("Скачать расписание").setCallbackData("download_schedule"));
        rowsInline.add(rowInline);
        markupInline.setKeyboard(rowsInline);
        allKeyboards.put("Городские автобусы", markupInline);
        callbackKeyboards.put("coming_to_KHNPP", new String[] {"Ближайшие на ХАЭС", "Вот 3 ближайшие автобуса на ХАЭС"});
        callbackKeyboards.put("coming_to_town", new String[] {"Ближайшие в город", "Вот 3 ближайшие автобуса в город"});
        callbackKeyboards.put("coming_to_Krivin", new String[] {"В Кривин", "Вот расписание автобусов в Кривин"});
        callbackKeyboards.put("download_schedule", new String[] {"Скачать расписание ХАЭС", "Выберите формат для скачивания"});


        // New keyboard Скачать расписание
        markupInline = new InlineKeyboardMarkup();
        rowsInline = new ArrayList<>();
        rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton().setText("В формате .jpeg").setCallbackData("download_jpeg"));
        rowInline.add(new InlineKeyboardButton().setText("В формате .doc").setCallbackData("download_doc"));
        rowsInline.add(rowInline);
        markupInline.setKeyboard(rowsInline);
        allKeyboards.put("Скачать расписание ХАЭС", markupInline);
        callbackKeyboards.put("download_jpeg", new String[] {"В формате .jpeg", "Вы выбрали скачивание в формате .jpeg"});
        callbackKeyboards.put("download_doc", new String[] {"В формате .doc", "Вы выбрали скачивание в формате .doc"});

        // New keyboard Новости
        markupInline = new InlineKeyboardMarkup();
        rowsInline = new ArrayList<>();
        rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton().setText("Новости Нетешин").setCallbackData("news_Neteshin"));
        rowInline.add(new InlineKeyboardButton().setText("Новости области").setCallbackData("news_Khmelnickiy"));
        rowInline.add(new InlineKeyboardButton().setText("Новости Украины").setCallbackData("news_Ukraine"));
        rowsInline.add(rowInline);
        markupInline.setKeyboard(rowsInline);
        allKeyboards.put("Новости", markupInline);
        callbackKeyboards.put("news_Neteshin", new String[] {"Новости Нетешин", "Новости с сайта Нетешинского гор. совета"});
        callbackKeyboards.put("news_Khmelnickiy", new String[] {"Новости области", "Новости с сайта ukr.net по области"});
        callbackKeyboards.put("news_Ukraine", new String[] {"Новости Украины", "Новости с сайта ukr.net в Украине"});

        // New keyboard Погода
        markupInline = new InlineKeyboardMarkup();
        rowsInline = new ArrayList<>();
        rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton().setText("На сегодня").setCallbackData("weather_today"));
        rowInline.add(new InlineKeyboardButton().setText("На неделю").setCallbackData("weather_week"));
        rowsInline.add(rowInline);
        markupInline.setKeyboard(rowsInline);
        allKeyboards.put("Погода", markupInline);
        callbackKeyboards.put("weather_today", new String[] {"На сегодня", "Погода на сегодняшний день."});
        callbackKeyboards.put("weather_week", new String[] {"На неделю", "Погода на текущую неделю."});

        // New keyboard Заведения
        markupInline = new InlineKeyboardMarkup();
        rowsInline = new ArrayList<>();
        rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton().setText("Топ 10").setCallbackData("top_10"));
        rowInline.add(new InlineKeyboardButton().setText("Поиск").setCallbackData("search"));
        rowsInline.add(rowInline);
        markupInline.setKeyboard(rowsInline);
        allKeyboards.put("Заведения", markupInline);
        callbackKeyboards.put("top_10", new String[] {"Топ 10", "Топ 10 заведений нашего города."});
        callbackKeyboards.put("search", new String[] {"Поиск", "Введите название заведения."});

        // New keyboard Кинотеатр
        markupInline = new InlineKeyboardMarkup();
        rowsInline = new ArrayList<>();
        rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton().setText("Афиша").setCallbackData("poster"));
        rowInline.add(new InlineKeyboardButton().setText("Сеансы").setCallbackData("sessions"));
        rowsInline.add(rowInline);
        markupInline.setKeyboard(rowsInline);
        allKeyboards.put("Кинотеатр", markupInline);
        callbackKeyboards.put("poster", new String[] {"Афиша", "Афиша кинотеатра Zephyr."});
        callbackKeyboards.put("sessions", new String[] {"Сеансы", "Сеансы на сегодня"});

        // New keyboard Газеты
        markupInline = new InlineKeyboardMarkup();
        rowsInline = new ArrayList<>();
        rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton().setText("Объявления").setCallbackData("ads"));
        rowInline.add(new InlineKeyboardButton().setText("Перспектива").setCallbackData("perspektiva"));
        rowsInline.add(rowInline);
        markupInline.setKeyboard(rowsInline);
        allKeyboards.put("Газеты", markupInline);
        callbackKeyboards.put("ads", new String[] {"Объявления", "Газета Нетешинские объявления."});
        callbackKeyboards.put("perspektiva", new String[] {"Перспектива", "Газета перспектива."});
    }

    public ReplyKeyboard choiceKeyboard(String key) {
        return allKeyboards.get(key);
    }

    public HashMap<String, String[]> getCallbackKeyboards() {
        return callbackKeyboards;
    }
}
