package ru.onlineService.telegramBot;

import ru.onlineService.exception.ApplicationException;
import ru.onlineService.telegramBot.config.ConfigProperties;

public class Constants {

    public static boolean isActiveChatBot;
    public static String authToken;
    public static boolean authIsActive;
    public static String botTokenTech;
    public static String botTokenOrg;
    public static String dbPath;
    public static String botPass;


    static {
        try {
            isActiveChatBot = Boolean.parseBoolean(ConfigProperties.getProperty("isActiveChatBot"));
        }catch (ApplicationException e){
            e.printStackTrace();
            isActiveChatBot = false;
        }
        try {
            authToken = ConfigProperties.getProperty("authToken");
        }catch (ApplicationException e){
            authToken = null;
        }
        try {
            authIsActive = Boolean.parseBoolean(ConfigProperties.getProperty("authIsActive"));
        }catch (ApplicationException e){
            authIsActive = false;
        }
        try {
            botTokenTech = ConfigProperties.getProperty("botTokenTech");
        }catch (ApplicationException e){
            botTokenTech = null;
            isActiveChatBot = false;
        }

        try {
            botTokenOrg = ConfigProperties.getProperty("botTokenOrg");
        }catch (ApplicationException e){
            botTokenOrg = null;
        }

        try {
            dbPath = ConfigProperties.getProperty("dbPath");
        }catch (ApplicationException e){
            dbPath = null;
        }

        try {
            botPass = ConfigProperties.getProperty("botPass");
        }catch (ApplicationException e){
            botPass = null;
        }

    }


}
