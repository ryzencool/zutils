package com.marsh.zutils.util;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Optional;

public class JsonUtil {

    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static Optional<String> toJson(Object object) {
        try {
            return Optional.ofNullable(OBJECT_MAPPER.writeValueAsString(object));
        } catch (JsonProcessingException e) {
            // ignore
        }
        return Optional.empty();
    }


    public static <T> Optional<T> toObject(String json, Class<T> clz) {
        try {
            return Optional.ofNullable(OBJECT_MAPPER.readValue(json, clz));
        } catch (IOException e) {
            return Optional.empty();
        }
    }
}
