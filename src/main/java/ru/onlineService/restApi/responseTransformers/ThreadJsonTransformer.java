package ru.onlineService.restApi.responseTransformers;

import ru.onlineService.gson.GsonHelper;
import spark.ResponseTransformer;

public class ThreadJsonTransformer  implements ResponseTransformer {


    @Override
    public String render(Object model) {
        return GsonHelper.getGson().toJson(model
        );
    }
}
