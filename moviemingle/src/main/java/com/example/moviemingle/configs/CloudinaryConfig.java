package com.example.moviemingle.configs;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {
    
    @Value("${cloudinary.name}")
    private String cloudName;
    
    @Value("${cloudinary.api-key}")
    private String cloudApiKey;
    
    @Value("${cloudinary.api-secret}")
    private String cloudApiSecret;
    
    @Value("${cloudinary.secure}")
    private String cloudSecure;
    
    @Bean
    public Cloudinary cloudinary() {
        String path = "cloudinary";
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", cloudApiKey,
                "api_secret", cloudApiSecret,
                "secure", cloudSecure));
    }

}
