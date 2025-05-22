package ru.onlineService.exception;

public class UnCheckedAppException extends RuntimeException  {


    public UnCheckedAppException() {
    }

    public UnCheckedAppException(String message) {
        super(message);
    }

    public UnCheckedAppException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnCheckedAppException(Throwable cause) {
        super(cause);
    }

    public UnCheckedAppException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
