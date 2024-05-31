package com.example.moviemingle.utils;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@JacksonAnnotationsInside
@JsonDeserialize(using = CommaSeparatedStringToListDeserializer.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface CommaSeparatedStringToList {
}