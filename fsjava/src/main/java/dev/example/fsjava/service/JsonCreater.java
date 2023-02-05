package dev.example.fsjava.service;

import com.google.gson.Gson;

public class JsonCreater {
    public static String create(Object object){return new Gson().toJson(object);}
}
