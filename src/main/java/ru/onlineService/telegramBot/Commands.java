package ru.onlineService.telegramBot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

import ru.onlineService.telegramBot.chatData.Chat;
import ru.onlineService.telegramBot.chatData.ChatHandler;
import ru.onlineService.telegramBot.commands.*;

import java.io.IOException;

public class Commands {

    public static final String AUTH = "/auth";
    public static final String EXIT = "/exit";


    public ChatHandler chatHandler;
    public TelegramBot bot;

    public Commands(ChatHandler chatHandler, TelegramBot bot) {
        this.chatHandler = chatHandler;
        this.bot = bot;
    }

    public void executeCommand(Update update) {
        if (!update.message().text().startsWith("/") || update.message() == null || update.message().chat() == null) {
            return;
        }
        String chatId = String.valueOf(update.message().chat().id());

        if (update.message().text().startsWith("/start") || update.message().text().startsWith("/help")) {
            bot.execute(new SendMessage(chatId, "Здравствуйте. Для подписки на сообщения необходимо авторизоваться\n\n" +
                    "Список комманд: \n" + AUTH + " [пароль] - без квадратных скобок. Авторизация\n" +
                    EXIT + " - выход  "));
            return;
        }

        if (update.message().text().startsWith(AUTH)) {
            new Auth(chatHandler, bot).execute(update);
            return;
        }

        Chat chat = chatHandler.getChat(chatId);
        if (chat == null) {
            bot.execute(new SendMessage(chatId, "Требуется авторизация"));
            return;
        }
        if (update.message().text().startsWith(EXIT)) {
            chatHandler.removeChat(chatId);
            bot.execute(new SendMessage(chatId, "Выход выполнен"));
        } else {
            bot.execute(new SendMessage(chatId, "Не известная команда"));
        }
    }


}
