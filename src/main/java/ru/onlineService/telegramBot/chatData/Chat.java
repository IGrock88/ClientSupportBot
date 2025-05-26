package ru.onlineService.telegramBot.chatData;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import ru.onlineService.telegramBot.Bot;

public class Chat {

    private String id;
    private String userName;

    private final transient Bot bot;

    public String getUserName() {
        return userName;
    }

    public String getId() {
        return id;
    }

    public Bot getBot() {
        return bot;
    }

    public Chat(String id, String userName, Bot bot) {
        this.id = id;
        this.userName = userName;
        this.bot = bot;
    }

    public void sendMessage(String text){
        bot.getTelegramBot().execute(new SendMessage(id, text));
    }


    @Override
    public String toString() {
        return "Chat{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                '}';
    }
}
