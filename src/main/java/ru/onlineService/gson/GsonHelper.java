package ru.onlineService.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.onlineService.gson.deserialisers.StringWithQuotDeserializer;


public class GsonHelper {

    private static Gson gson = new GsonBuilder()
            .registerTypeAdapter(String.class, new StringWithQuotDeserializer())
            .serializeNulls()
            .create();

    public static Gson getGson() {
        return gson;
    }
}
