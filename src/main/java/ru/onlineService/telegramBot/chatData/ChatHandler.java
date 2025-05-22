package ru.onlineService.telegramBot.chatData;

import com.pengrad.telegrambot.TelegramBot;

import java.util.HashMap;

public class ChatHandler {

    private HashMap<String, Chat> chatHashMap;
    private TelegramBot bot;
    private static ChatHandler chatHandler;

    public static ChatHandler getInstance(TelegramBot bot) {
        if (chatHandler == null){
            chatHandler = new ChatHandler(bot);
        }
        return chatHandler;
    }

    private ChatHandler(TelegramBot bot) {
        this.bot = bot;
        chatHashMap = ChatDb.getInstance().getAllChats();
    }

    public ChatHandler addChat(Chat chat){
        Chat chatCheck = chatHashMap.get(chat.getId());
        if (chatCheck != null){
            return this;
        }
        chatHashMap.put(chat.getId(), chat);
        ChatDb.getInstance().add(chat);
        return this;
    }

    public ChatHandler removeChat(String chatId){
        chatHashMap.remove(chatId);
        ChatDb.getInstance().remove(chatId);
        return this;
    }

    public ChatHandler sendBroadCostMessage(String message){
        if (chatHashMap.size() == 0){
            return this;
        }
        for (Chat chat: chatHashMap.values()){
            chat.sendMessage(message);
        }
        return this;
    }


    public Chat getChat(String chatId) {
        return chatHashMap.get(chatId);
    }

    public HashMap<String, Chat> getChats(){
        return chatHashMap;
    }
}
