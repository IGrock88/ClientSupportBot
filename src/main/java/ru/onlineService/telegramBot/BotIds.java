package ru.onlineService.telegramBot;

public enum BotIds {
    TECH("tech_support"),
    ORG("org_support");

    private String id;

    BotIds(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
