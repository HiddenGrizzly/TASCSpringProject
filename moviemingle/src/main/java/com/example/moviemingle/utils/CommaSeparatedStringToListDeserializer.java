package com.example.moviemingle.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class CommaSeparatedStringToListDeserializer extends JsonDeserializer<Set<String>> {

    @Override
    public Set<String> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String value = p.getValueAsString();
        String[] items = value.split(",\\s*");
        return new HashSet<>(Arrays.asList(items));
    }

    public SimpleModule customDeserializationModule() {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Set.class, new CommaSeparatedStringToListDeserializer());
        return module;
    }
}