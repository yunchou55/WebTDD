package com.example.ou.webtdd;

/**
 * Created by ou on 2018/9/20.
 */

public class InvalidTemperatureException extends RuntimeException {
    public InvalidTemperatureException(String msg){
        super(msg);
    }
}
