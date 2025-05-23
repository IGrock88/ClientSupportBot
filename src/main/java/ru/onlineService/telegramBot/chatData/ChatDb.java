package ru.onlineService.telegramBot.chatData;

import com.pengrad.telegrambot.TelegramBot;
import ru.onlineService.jdbc.Sqllite;
import ru.onlineService.logging.LoggerHelper;
import ru.onlineService.telegramBot.Bot;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class ChatDb {

    private static ChatDb instance = null;

    public static ChatDb getInstance() {
        if (instance == null){
            instance = new ChatDb();
        }
        return instance;
    }

    private ChatDb() {
    }

    public void add(Chat chat) {
        try {
            PreparedStatement preparedStatement = Sqllite.getInstanceDb().prepareStatement("INSERT INTO chat VALUES(?, ?)");
            preparedStatement.setString(1, chat.getId());
            preparedStatement.setString(2, chat.getUserName());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public HashMap<String, Chat> getAllChats(Bot bot) {
        HashMap<String, Chat> result = new HashMap<>();
        try {
            ResultSet rs = Sqllite.getInstanceDb().createStatement().executeQuery("SELECT * FROM chat WHERE botId = " + bot.getId());
            while (rs.next()) {
                Chat chat = new Chat(rs.getString("id"), rs.getString("userName"), bot);
                result.put(chat.getId(), chat);
            }
        } catch (SQLException e) {
            LoggerHelper.error(e.getMessage(), e);
        }
        finally {
            try {
                Sqllite.getInstanceDb().close();
            } catch (SQLException e) {
                LoggerHelper.error(e.getMessage(), e);
            }
        }

        return result;
    }

    public void remove(String chatId){
        try {
            PreparedStatement preparedStatement = Sqllite.getInstanceDb().prepareStatement("DELETE FROM chat WHERE id = ?");
            preparedStatement.setString(1, chatId);
            preparedStatement.execute();

        } catch (SQLException e) {
            LoggerHelper.error(e.getMessage(), e);
        }
        finally {
            try {
                Sqllite.getInstanceDb().close();
            } catch (SQLException e) {
                LoggerHelper.error(e.getMessage(), e);
            }
        }
    }


}
