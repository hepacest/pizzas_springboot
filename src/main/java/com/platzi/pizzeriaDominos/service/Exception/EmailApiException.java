package com.platzi.pizzeriaDominos.service.Exception;

public class EmailApiException extends RuntimeException{

    public EmailApiException() {
        super ("Error sending email...");
    }
}
