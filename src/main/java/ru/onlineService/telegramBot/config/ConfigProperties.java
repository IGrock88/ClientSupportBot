package ru.onlineService.telegramBot.config;

import ru.onlineService.exception.ApplicationException;
import ru.onlineService.logging.LoggerHelper;
import ru.onlineService.utils.FileSystem;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigProperties {


    private static Properties prop = new Properties();

    public static void readConfigFile(){
            InputStream inputStream = null;


        if (tryGetOutsideProperties()) return;


        try {
                String configPath = System.getProperty("config");

                if (configPath == null){
                    configPath = "config.properties";
                }
                inputStream = ConfigProperties.class.getClassLoader().getResourceAsStream(configPath);

                if (inputStream != null) {
                    prop.load(inputStream);
                } else {
                    throw new FileNotFoundException("property file '" + configPath + "' not found in the classpath");
                }

            } catch (Exception e) {
                LoggerHelper.error("Config file not found", e);
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        LoggerHelper.error("Can't close inputStream", e);
                    }
                }
            }

    }

    private static boolean tryGetOutsideProperties() {
        try {
            prop.load(new FileInputStream(FileSystem.getAbsoluteAppPath()+"/config.properties"));
            return true;
        } catch (IOException e1) {
        }
        return false;
    }



    public static String getProperty(String name) throws ApplicationException {
        String property = ConfigProperties.prop.getProperty(name);
        if (property == null){
            throw new ApplicationException("Property " + name + " not found");
        }
        return property;
    }
}
