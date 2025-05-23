package ru.onlineService.restApi.MessagesToBot;

import java.util.ArrayList;
import java.util.List;

public class ApiMessageValidator {


    public static List<String> validate(RequestAddMessageToBot requestAddMessageToBot){
        List<String> errors = new ArrayList<>();

        if (requestAddMessageToBot.getBotId() == null || requestAddMessageToBot.getBotId().isEmpty()) {
            errors.add("Не передан идентификатор бота");
        }

        if (requestAddMessageToBot.getRequest() == null) {
            errors.add("Не передан объект RequestMessage");
        } else {
            RequestMessage requestMessage = requestAddMessageToBot.getRequest();
            if (requestMessage.getMessage() == null || requestMessage.getMessage().isEmpty()) {
                errors.add("Не передано сообщение");
            }
            if (requestMessage.getPhone() == null || requestMessage.getPhone().isEmpty()) {
                errors.add("Не передан номер телефона");
            }
        }

        return errors;
    }


}
