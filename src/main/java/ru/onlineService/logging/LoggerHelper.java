package ru.onlineService.logging;


import ru.onlineService.telegramBot.Bot;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class LoggerHelper {

    private static final Logger LOGGER = Logger.getLogger(LoggerHelper.class.getName());

    public static void log(Level level ,String msg){
        LOGGER.log(level, msg);
    }


    public static String error(String msg, Throwable throwable){
        if (throwable.getStackTrace().length > 0){
            List<String> stackList = new ArrayList<>();
            StackTraceElement[] stack = throwable.getStackTrace();
            for (int i = 0; i < stack.length; i++){
                stackList.add(stack[i].toString());
            }
            msg = throwable.getClass().getName() + ": " + msg + "\n Stacktrace: \n" + String.join("\n", stackList);
        }
        LOGGER.log(Level.SEVERE, msg, throwable);
        return msg;
    }


    public static void info(String msg){
        LOGGER.info(msg);
    }

    public static void logWithSource(String className, String methodName, Level level, String msg) {
        LogRecord record = new LogRecord(level, msg);
        record.setSourceClassName(className);
        record.setSourceMethodName(methodName);
        LOGGER.log(record);
    }


}
