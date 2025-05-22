package ru.onlineService.jdbc;

import ru.onlineService.logging.LoggerHelper;
import ru.onlineService.telegramBot.Constants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Sqllite
{
    private static Connection connection = null;
    public static Connection getInstanceDb()
    {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + Constants.dbPath);
        }catch (SQLException e){
            LoggerHelper.errorWithSendingBot(e.getMessage(), e);
        }

        return connection;
    }
}
