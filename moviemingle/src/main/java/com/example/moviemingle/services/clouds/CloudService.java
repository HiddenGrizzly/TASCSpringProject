package com.example.moviemingle.services.clouds;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CloudService {
    
    String upload(MultipartFile file, String name);
  
    void delete(String name) throws IOException;
    
}
