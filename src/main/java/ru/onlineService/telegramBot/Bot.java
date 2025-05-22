package ru.onlineService.telegramBot;

import com.pengrad.telegrambot.*;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import ru.onlineService.exception.UnCheckedAppException;
import ru.onlineService.logging.LoggerHelper;
import ru.onlineService.telegramBot.chatData.ChatHandler;

import java.util.concurrent.Executors;

public class Bot {

    private static ChatHandler chatHandler;
    private static TelegramBot bot;

    public static void sendBroadCast(String message){
        try {
            System.out.println(message);
            if (Constants.isActiveChatBot){
                if (chatHandler == null){
                    throw new UnCheckedAppException("ChatHandler not initialize");
                }
                chatHandler.sendBroadCostMessage(message);
            }
            else {
                System.out.println(message);
            }
        }catch (Throwable e){
            LoggerHelper.error(e.getMessage(), e);
        }

    }

    public static TelegramBot getBot(){
        if (bot == null){
            bot = new TelegramBot(Constants.botToken);
        }
        return bot;
    }

    public void init(){
        if (!Constants.isActiveChatBot){
            return;
        }
        Executors.newSingleThreadExecutor().execute(() -> {
            chatHandler = ChatHandler.getInstance(bot);
            Commands commands = new Commands(chatHandler, bot);
            bot.setUpdatesListener(updates -> {

                try {
                    for (Update update : updates) {
                        if (update.message() != null && update.message().chat() != null && update.message().text() != null && update.message().text().length() > 0) {
                            commands.executeCommand(update);
                        }
                    }
                }catch (Exception e){
                    LoggerHelper.error(e.getMessage(), e);
                }

                return UpdatesListener.CONFIRMED_UPDATES_ALL;
            });
        });

    }
}
