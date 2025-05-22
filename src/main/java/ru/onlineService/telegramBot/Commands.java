package ru.onlineService.telegramBot;

import com.pengrad.telegrambot.Callback;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.*;
import com.pengrad.telegrambot.request.SendMessage;

import com.pengrad.telegrambot.response.SendResponse;
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
        if (update.message().text().startsWith("/menu")){

            Keyboard keyboard = new ReplyKeyboardMarkup(
                    new KeyboardButton[]{
                            new KeyboardButton("Предоставить телефон").requestContact(true).requestPoll(new KeyboardButtonPollType()),
                    }
            );

            SendMessage request = new SendMessage(chatId, "Предоставьте телефон")
                    .replyMarkup(keyboard);
            bot.execute(request, new Callback<SendMessage, SendResponse>() {
                @Override
                public void onResponse(SendMessage request, SendResponse response) {
                    System.out.println(response.message().replyMarkup());
                }

                @Override
                public void onFailure(SendMessage sendMessage, IOException e) {
                    System.out.println(sendMessage);
                }



            });


        }
        if (update.message().text().startsWith("/start") || update.message().text().startsWith("/help")) {
//            bot.execute(new SendMessage(chatId, "Здравствуйте. Вас привествует бот для сотрудников компании Ночной Экспресс. Для подписки на сообщения необходимо авторизоваться\n\n" +
//                    "Список комманд: \n" + AUTH + " [пароль] - без квадратных скобок. Авторизация\n" +
//                    EXIT + " - выход  "));


            Keyboard keyboard = new ReplyKeyboardMarkup(
                    new KeyboardButton[]{
                            new KeyboardButton("Предоставить телефон").requestContact(true).requestPoll(new KeyboardButtonPollType()),
                    }
            );

            SendMessage request = new SendMessage(chatId, "Предоставьте телефон")
                    .replyMarkup(keyboard);
            bot.execute(request, new Callback<SendMessage, SendResponse>() {
                @Override
                public void onResponse(SendMessage request, SendResponse response) {
                    System.out.println(response.message().leftChatMember().toString());
                }

                @Override
                public void onFailure(SendMessage sendMessage, IOException e) {
                    System.out.println(sendMessage);
                }


            });
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
