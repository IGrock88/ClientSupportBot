package ru.onlineService.restApi;

import ru.onlineService.exception.ApplicationException;
import ru.onlineService.telegramBot.config.ConfigProperties;

import static spark.Spark.port;

public class StartServer {

    public static void start(){

        try {
            String portNumber = ConfigProperties.getProperty("restApiPort");
            port(Integer.parseInt(portNumber));
            new Routes().start();

        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }



}
