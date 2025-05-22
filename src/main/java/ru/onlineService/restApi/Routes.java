package ru.onlineService.restApi;

import com.google.gson.Gson;
import ru.onlineService.restApi.MessagesToBot.ApiMessagesToBot;
import ru.onlineService.telegramBot.Constants;


import static spark.Spark.*;

public class Routes {

    public static final JsonTransformer JSON_TRANSFORMER = new JsonTransformer();


    public void start(){
        if (Constants.authIsActive){
            auth();
        }

        get("/hello", ((request, response) -> {
            return "<h1>HELLO</h1>";
        }));

        path("/api",() -> {
            post("/message/add-broadcast", ApiMessagesToBot::addBroadCastMessage, JSON_TRANSFORMER);
        });

        init();
    }

    private void auth() {
        before((before_req, before_res) -> {
            String token = before_req.headers("Authorization");

            if (token == null || !token.equals(Constants.authToken)) {
                ResultJson resultJson = new ResultJson(false);
                resultJson.addError("Unauthorized");
                resultJson.setErrorCode(403);
                halt(403, new Gson().toJson(resultJson));
            }
        });
    }


}
