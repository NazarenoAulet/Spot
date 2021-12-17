package com.quasartec.spot.LiveDatas;

public class DataOrException<T,Exception> {
    private T data;
    private Exception exception;

    public void setData(T data) {
        this.data = data;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public T getData() {
        return data;
    }

}
