package ru.onlineService.config;

import org.junit.jupiter.api.BeforeAll;
import ru.onlineService.telegramBot.config.ConfigProperties;

public class ConfigPropertiesTest {

    @BeforeAll
    static void startTest(){
        ConfigProperties.readConfigFile();
    }



}
