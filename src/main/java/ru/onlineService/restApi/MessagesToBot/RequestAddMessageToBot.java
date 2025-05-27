package ru.onlineService.restApi.MessagesToBot;



public class RequestAddMessageToBot {


    private String botId;
    private RequestMessage request;

    public String getBotId() {
        return botId;
    }

    public RequestMessage getRequest() {
        return request;
    }

    public String getBotMessage(){
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("Бот ").append(getBotId()).append("\n--------------------------------\n");
        if (request.getPhone() != null){
            messageBuilder.append("Телефон: ").append(request.getPhone()).append("\n");
        }
        if (request.getEmail() != null){
            messageBuilder.append("Email: ").append(request.getEmail()).append("\n");
        }

        if (request.getName() != null){
            messageBuilder.append("Имя: ").append(request.getName()).append("\n");
        }
        messageBuilder.append("Есть в базе: ").append(request.isUserExist() ? "Да" : "Нет").append("\n");
        messageBuilder.append("Авторизован: ").append(request.isAuth() ? "Да" : "Нет").append("\n");

        if (request.getProfileLink() != null){
            messageBuilder.append("Ссылка на профиль: ").append(request.getProfileLink()).append("\n");
        }

        if (request.getCategory() != null){
            messageBuilder.append("Тема: ").append(request.getCategory()).append("\n");
        }

        messageBuilder.append("Сообщение:\n").append(request.getMessage()).append("\n--------------------------------\n");

        if (request.getMessageLink() != null){
            messageBuilder.append(request.getMessageLink());
        }

        return messageBuilder.toString();
    }
}
