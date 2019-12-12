package com.example.demo.Error;

public class MyError extends RuntimeException {
    private  String message;

    public MyError( String message1) {
        this.message = message1;
    }
    public MyError( IError iError) {
        this.message = iError.getError();
    }


    @Override
    public String getMessage() {
        return message;
    }
}
