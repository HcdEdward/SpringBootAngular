package com.example.vo.jsonVO;

public class Result<T> {
    private int code;       //ResultCodeEnum 可以申明为枚举类型
    private String message;
    private  T data;

    public int getCode() {
        return code;
    }

    public Result() {
    }

    public Result setCode(int resultCode) {
        this.code = resultCode;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result setData(T data) {
        this.data = data;
        return this;
    }
}
