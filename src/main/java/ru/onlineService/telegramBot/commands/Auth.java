package ru.onlineService.telegramBot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import ru.onlineService.telegramBot.Bot;
import ru.onlineService.telegramBot.Constants;
import ru.onlineService.telegramBot.chatData.Chat;
import ru.onlineService.telegramBot.chatData.ChatHandler;

import static ru.onlineService.telegramBot.Commands.AUTH;


public class Auth extends BaseCommand {

    public Auth(ChatHandler chatHandler, Bot bot) {
        super(chatHandler, bot);
    }

    @Override
    public void execute(Update update) {
        String chatId = String.valueOf(update.message().chat().id());
        String[] message = update.message().text().split("\\s+");
        if (message.length < 2) {
            bot.getTelegramBot().execute(new SendMessage(chatId, "Не введен пароль. Введите " + AUTH + " и через пробел пароль."));
            return;
        }
        if (!message[1].equals(Constants.botPass)) {
            bot.getTelegramBot().execute(new SendMessage(chatId, "Пароль не верный"));
            return;
        }
        if (chatHandler.getChat(chatId) != null) {
            bot.getTelegramBot().execute(new SendMessage(chatId, "Вы уже авторизованы"));
            return;
        }

        Chat chat = new Chat(chatId, update.message().chat().username() != null ? update.message().chat().username() : "Группа", bot);
        chatHandler.addChat(chat);
        bot.getTelegramBot().execute(new SendMessage(chatId, "Вход выполнен. Спасибо!"));

    }
}
