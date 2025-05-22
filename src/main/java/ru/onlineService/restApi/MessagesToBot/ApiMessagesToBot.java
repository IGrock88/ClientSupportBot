package ru.onlineService.restApi.MessagesToBot;

import com.google.gson.JsonSyntaxException;
import ru.onlineService.gson.GsonHelper;
import ru.onlineService.logging.LoggerHelper;
import ru.onlineService.restApi.ResultJson;
import ru.onlineService.telegramBot.Bot;
import spark.Request;
import spark.Response;

public class ApiMessagesToBot {



    /**
     * Роут для добавления собщений боту.
     * method: post
     * url: /check/add
     * request: {
     *  "message": "message to bot"
     * }
     * response: {
     *     "success": true,
     *     "payload": {},
     *     "errors": [],
     *     "errorCode": 0
     * }
     */
    public static ResultJson addBroadCastMessage(Request request, Response response){
        response.type("application/json");
        ResultJson resultJson = new ResultJson(true);
        try {
            RequestAddMessageToBot requestAddMessageToBot = GsonHelper.getGson().fromJson(request.body(), RequestAddMessageToBot.class);
            if (requestAddMessageToBot.getMessage() == null || requestAddMessageToBot.getMessage().length() == 0){
                resultJson.setSuccess(false);
                resultJson.addError("Не передано сообщение");
            }
            else {
                Bot.sendBroadCast(requestAddMessageToBot.getMessage());
            }
        } catch (JsonSyntaxException e){
            String error = "Синтаксическая ошибка в теле запроса json: " + e.getMessage();
            resultJson.setSuccess(false).addError(error).setErrorCode(-1);
        }catch (Exception e){
            response.status(502);
            resultJson.setSuccess(false).addError("Не предвиденная ошибка").setErrorCode(-1);
            LoggerHelper.error(e.getMessage(), e);
        }
        return resultJson;
    }
}
