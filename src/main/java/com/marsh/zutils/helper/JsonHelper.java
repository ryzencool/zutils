package com.marsh.zutils.helper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.marsh.zutils.date.*;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
public class JsonHelper {

    public ObjectMapper mapper() {
        var mapper = new ObjectMapper();
        var module = new SimpleModule();
        module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
        module.addDeserializer(LocalDate.class, new LocalDateDeserializer());
        module.addDeserializer(LocalTime.class, new LocalTimeDeserializer());
        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
        module.addSerializer(LocalDate.class, new LocalDateSerializer());
        module.addSerializer(LocalTime.class, new LocalTimeSerializer());
        mapper.registerModule(module);
        return mapper;
    }

    @SneakyThrows
    public ObjectNode toObjectNode(String value) {
        return mapper().readValue(value, ObjectNode.class);
    }

    @SneakyThrows
    public <T> String toJson(T value){

        return mapper().writeValueAsString(value);
    }


    @SneakyThrows
    public <T> T toObject(String value, Class<T> claz) {
        return mapper().readValue(value, claz);
    }

    @SneakyThrows
    public <T> T toGenericObject(String value, TypeReference<T> typeReference) {
        return mapper().readValue(value, typeReference);
    }

}
