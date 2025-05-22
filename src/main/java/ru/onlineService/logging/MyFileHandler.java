package ru.onlineService.logging;

import java.io.IOException;
import java.util.logging.FileHandler;

public class MyFileHandler extends FileHandler {
    public MyFileHandler() throws IOException, SecurityException {
    }

    public MyFileHandler(String pattern) throws IOException, SecurityException {
        super(pattern);
    }

    public MyFileHandler(String pattern, boolean append) throws IOException, SecurityException {
        super(pattern, append);
    }

    public MyFileHandler(String pattern, int limit, int count) throws IOException, SecurityException {
        super(pattern, limit, count);
    }

    public MyFileHandler(String pattern, int limit, int count, boolean append) throws IOException, SecurityException {
        super(pattern, limit, count, append);
    }

    public MyFileHandler(String pattern, long limit, int count, boolean append) throws IOException {
        super(pattern, limit, count, append);
    }




}
