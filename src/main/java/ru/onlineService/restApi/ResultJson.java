package ru.onlineService.restApi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;


public class ResultJson {

    protected boolean success = true;
    protected HashMap<String, Object> payload = new HashMap<>();
    protected ArrayList<String> errors = new ArrayList<>();
    protected int errorCode = 0;

    public ResultJson(){

    }

    public ResultJson(boolean success) {
        this.success = success;
    }

    public ResultJson(boolean success, HashMap<String, Object> payload) {
        this.success = success;
        this.payload = payload;
    }

    public boolean isSuccess() {
        return success;
    }

    public ResultJson setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public ResultJson addPayload(Object object){
        payload.put(object.getClass().getSimpleName().toLowerCase(), object);
        return this;
    }

    public ResultJson addPayloadWithIndex(Object object, String index){
        payload.put(index, object);
        return this;
    }

    public <T> ResultJson addPayloadArrayWithIndex(Collection<T> arrayList, String index){
        payload.put(index, arrayList);
        return this;
    }

    public ResultJson setErrors(ArrayList<String> errors) {
        this.errors = errors;
        return this;
    }

    public ResultJson addError(String error){
        this.success = false;
        this.errors.add(error);
        return this;
    }

    public ResultJson setErrorCode(int errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public ArrayList<String> getErrors() {
        return errors;
    }
}
