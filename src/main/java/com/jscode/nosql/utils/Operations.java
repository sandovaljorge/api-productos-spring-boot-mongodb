package com.jscode.nosql.utils;

public class Operations {

    public static String trimBrackets(String message){
        return message.replaceAll("[\\[\\]]", "");
    }
}
