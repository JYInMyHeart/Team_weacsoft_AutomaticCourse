package com.spring.util;

import java.util.HashMap;
import java.util.Map;

public class ResponseEntity {

    private static MyUtil myUtil = new MyUtil();

    public static final String ERRORS_KEY = "errors";
    //返回的信息
    private final String message;
    //返回的状态码
    private final int code;
    //返回的时间
    private final String timestamp;

    private final Map<String, Object> data = new HashMap();

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public ResponseEntity putDataValue(String key, Object value) {
        data.put(key, value);
        return this;
    }

    private ResponseEntity(int code, String message, String timestamp) {
        this.code = code;
        this.message = message;
        //返回的时间
        this.timestamp = timestamp;
    }

    public static ResponseEntity ok() {
        return new ResponseEntity(200, "Ok", myUtil.getTime());
    }

    public static ResponseEntity notFound() {
        return new ResponseEntity(404, "Not Found", myUtil.getTime());
    }

    public static ResponseEntity badRequest() {
        return new ResponseEntity(400, "Bad Request", myUtil.getTime());
    }

    public static ResponseEntity forbidden() {
        return new ResponseEntity(403, "Forbidden", myUtil.getTime());
    }

    public static ResponseEntity unauthorized() {
        return new ResponseEntity(401, "unauthorized", myUtil.getTime());
    }

    public static ResponseEntity serverInternalError() {
        return new ResponseEntity(500, "Server Internal Error", myUtil.getTime());
    }

    public static ResponseEntity customerError() {
        return new ResponseEntity(1001, "Customer Error", myUtil.getTime());
    }

    public static ResponseEntity logoutError() {
        return new ResponseEntity(1003, "logout Error", myUtil.getTime());
    }

    public static ResponseEntity logoutSuccess() {
        return new ResponseEntity(1002, "logout Success", myUtil.getTime());
    }
}
