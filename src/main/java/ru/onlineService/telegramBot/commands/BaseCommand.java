package ru.onlineService.telegramBot.commands;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import ru.onlineService.telegramBot.Bot;
import ru.onlineService.telegramBot.chatData.ChatHandler;


abstract public class BaseCommand {

    protected ChatHandler chatHandler;
    protected Bot bot;

    public BaseCommand(ChatHandler chatHandler, Bot bot) {
        this.chatHandler = chatHandler;
        this.bot = bot;
    }

    public abstract void execute(Update update);


}
