package ru.onlineService.utils;


import ru.onlineService.telegramBot.config.ConfigProperties;

import java.io.File;

public class FileSystem {


    private static String absoluteAppPath = null;

    public static String getAbsoluteAppPath() {
        if (absoluteAppPath == null){
            File jarPath=new File(ConfigProperties.class.getProtectionDomain().getCodeSource().getLocation().getPath());
            absoluteAppPath = jarPath.getParentFile().getAbsolutePath();
        }
        System.out.println(absoluteAppPath);
        return absoluteAppPath;
    }

}
