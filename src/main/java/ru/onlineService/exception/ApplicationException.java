package ru.onlineService.exception;

public class ApplicationException extends Exception {

    private int errorCode = 0;

    public static final int PRINTER_NOT_FOUND = 55;

    public ApplicationException(String msg){
        super(msg);
    }

    public ApplicationException(String msg, int errorCode){
        super(msg);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
