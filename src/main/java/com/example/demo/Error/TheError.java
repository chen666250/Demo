package com.example.demo.Error;



public enum  TheError implements IError {
    QUESTION_NOT_FOUND("\"没有这个问题\"");
    TheError(String message) {
        this.message = message;
    }
    private String message;
    @Override
    public String getError() {
        return message;
    }
}
