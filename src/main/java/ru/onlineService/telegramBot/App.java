package ru.onlineService.telegramBot;

import ru.onlineService.logging.LoggerHelper;
import ru.onlineService.logging.LoggingConfig;
import ru.onlineService.restApi.StartServer;
import ru.onlineService.telegramBot.config.ConfigProperties;

public class App {


    public void init() {

        try {
            this.configure();
            BotHandler.getInstance();
            StartServer.start();
        } catch (Exception e) {
            LoggerHelper.errorWithSendingBot("Ошибка инициализац:\n" + e.getMessage(), e);
            return;
        }
    }

    private void configure() {
        new LoggingConfig().installLogsConfig();
        ConfigProperties.readConfigFile();
    }
}
