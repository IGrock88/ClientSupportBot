package ru.onlineService.restApi.MessagesToBot;

public class RequestMessage {

    private String message;
    private String phone;
    private String email;
    private String name;
    private String category;
    private boolean isUserExist;
    private boolean isAuth;
    private String profileLink;
    private String messageLink;

    public String getMessage() {
        return message;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public boolean isUserExist() {
        return isUserExist;
    }

    public boolean isAuth() {
        return isAuth;
    }

    public String getProfileLink() {
        return profileLink;
    }

    public String getMessageLink() {
        return messageLink;
    }
}
