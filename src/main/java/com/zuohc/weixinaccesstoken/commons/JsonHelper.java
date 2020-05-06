package com.zuohc.weixinaccesstoken.commons;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.omg.CORBA.ObjectHelper;
import org.springframework.stereotype.Component;

public class JsonHelper {

    public static ObjectMapper objectMapper= new ObjectMapper();

    public static String ToJson(Object object){
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static Object ToOject(String s,Class c){
        try {
            return objectMapper.readValue(s,c);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
