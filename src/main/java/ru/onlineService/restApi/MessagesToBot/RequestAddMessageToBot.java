package ru.onlineService.restApi.MessagesToBot;



public class RequestAddMessageToBot {

    private String message;

    public String getMessage() {
        return message;
    }

    public RequestAddMessageToBot setMessage(String message) {
        this.message = message;
        return this;
    }
}
