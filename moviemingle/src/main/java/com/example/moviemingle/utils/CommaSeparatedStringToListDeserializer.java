package com.example.moviemingle.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class CommaSeparatedStringToListDeserializer extends JsonDeserializer<List<String>> {

    @Override
    public List<String> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String value = p.getValueAsString();
        return Arrays.asList(value.split(",\\s*"));
    }

    public static SimpleModule getModule() {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(List.class, new CommaSeparatedStringToListDeserializer());
        return module;
    }
}
