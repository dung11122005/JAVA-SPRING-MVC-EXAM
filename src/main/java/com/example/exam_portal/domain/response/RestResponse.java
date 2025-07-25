package com.example.exam_portal.domain.response;

public class RestResponse<T> {
    private int statusCode;
    private String error;

    private Object message;
    // private Object examName;
    private T data;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    // public Object getExamName() {
    //     return examName;
    // }

    // public void setExamName(Object examName) {
    //     this.examName = examName;
    // }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
