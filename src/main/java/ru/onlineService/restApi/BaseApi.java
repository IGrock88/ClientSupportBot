package ru.onlineService.restApi;

import ru.onlineService.exception.EmptyJsonException;
import ru.onlineService.gson.GsonHelper;

abstract public class BaseApi {

    protected static  <T> T fromJson(String json, Class<T> classOfT) throws EmptyJsonException {
        if (json.length() == 0){
            throw new EmptyJsonException("Тело запроса пустое, метод требует отправки json в теле запроса");
        }
        return GsonHelper.getGson().fromJson(json, classOfT);
    }

}
