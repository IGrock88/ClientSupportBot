package ru.onlineService.logging;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;

public class LoggingConfig {

    public void installLogsConfig()
    {
        String loggingPropertiesFilename = "logging.properties";
        InputStream loggingProperties = LoggingConfig.class.getClassLoader().getResourceAsStream(loggingPropertiesFilename);
        LogManager logManager = LogManager.getLogManager();
        try {
            logManager.readConfiguration(loggingProperties);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            assert loggingProperties != null;
            loggingProperties.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
