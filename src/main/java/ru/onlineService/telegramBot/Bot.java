package ru.onlineService.telegramBot;

import com.pengrad.telegrambot.*;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import ru.onlineService.exception.UnCheckedAppException;
import ru.onlineService.logging.LoggerHelper;
import ru.onlineService.telegramBot.chatData.ChatHandler;

import java.util.concurrent.Executors;

public class Bot {

    private String id;
    private ChatHandler chatHandler;
    private TelegramBot telegramBot;
    private String botToken;

    public Bot(String id, String botToken) {
        this.id = id;
        this.botToken = botToken;
        init();
    }

    public String getId() {
        return id;
    }

    public void sendBroadCast(String message){
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

    public TelegramBot getTelegramBot(){
        return telegramBot;
    }

    private void init(){
        if (!Constants.isActiveChatBot){
            return;
        }
        telegramBot = new TelegramBot(botToken);
        chatHandler = new ChatHandler(this);
        Executors.newSingleThreadExecutor().execute(() -> {
            Commands commands = new Commands(chatHandler, this);
            telegramBot.setUpdatesListener(updates -> {

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
