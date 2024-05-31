package com.example.moviemingle.configs;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfig {

    @Bean
    OkHttpClient okHttpClient(){
        return new OkHttpClient();
    }

}
