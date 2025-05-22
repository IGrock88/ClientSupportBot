package ru.onlineService.telegramBot.chatData;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;

public class Chat {

    private String id;
    private String userName;

    private transient TelegramBot bot;

    public String getUserName() {
        return userName;
    }

    public String getId() {
        return id;
    }

    public Chat setBot(TelegramBot bot) {
        this.bot = bot;
        return this;
    }

    public Chat(String id, String userName, TelegramBot bot) {
        this.id = id;
        this.userName = userName;
        this.bot = bot;
    }

    public void sendMessage(String text){
        bot.execute(new SendMessage(id, text));
    }


    @Override
    public String toString() {
        return "Chat{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                '}';
    }
}
