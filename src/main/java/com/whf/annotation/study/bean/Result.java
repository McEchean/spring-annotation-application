package com.whf.annotation.study.bean;

public class Result<T> {
    private String message;
    private Integer success;
    private T data;

    public Result<T> makeSuccessResult(String message) {
        this.setMessage(message);
        this.setSuccess(1);
        return this;
    }

    public Result<T> makeFailedResult(String message) {
        this.setMessage(message);
        this.setSuccess(0);
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "message='" + message + '\'' +
                ", success=" + success +
                ", data=" + data +
                '}';
    }
}
