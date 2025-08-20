package com.example.blog_app_apis.exception;

public class ApiException extends RuntimeException{


    public ApiException(String message){
        super(message);
    }


    public ApiException(){
        super();
    }


}
