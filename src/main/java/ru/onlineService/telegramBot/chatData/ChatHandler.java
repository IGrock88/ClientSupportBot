package ru.onlineService.telegramBot.chatData;

import ru.onlineService.telegramBot.Bot;

import java.util.HashMap;

public class ChatHandler {

    private HashMap<String, Chat> chatHashMap;
    private Bot bot;

    public ChatHandler(Bot bot) {
        this.bot = bot;
        chatHashMap = ChatDb.getInstance().getAllChats(bot);
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

    public ChatHandler removeChat(Chat chat){
        chatHashMap.remove(chat.getId());
        ChatDb.getInstance().remove(chat);
        return this;
    }

    public ChatHandler sendBroadCostMessage(String message){
        if (chatHashMap.isEmpty()){
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
