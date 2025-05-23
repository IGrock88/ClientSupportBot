package ru.onlineService.telegramBot;

import java.util.HashMap;

public class BotHandler {


    private static BotHandler instance;
    private static HashMap<String, Bot> bots = new HashMap<>();

    private BotHandler() {
        init();
    }

    private void init() {
        if (Constants.botTokenOrg != null){
            bots.put(BotIds.ORG.getId(), new Bot(BotIds.ORG.getId(), Constants.botTokenOrg));
        }
        if (Constants.botTokenTech != null){
            bots.put(BotIds.TECH.getId(), new Bot(BotIds.TECH.getId(), Constants.botTokenTech));
        }
    }

    public static BotHandler getInstance() {
        if (instance == null){
            instance = new BotHandler();
        }
        return instance;
    }

    public void sendMessageToBot(String botId, String message){
        getById(botId).sendBroadCast(message);
    }

    public Bot getById(String id){
        Bot bot = bots.get(id);
        if (bot == null) throw new RuntimeException("Бот и ид " + id + " не найден");
        return bot;
    }

}
