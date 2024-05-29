package com.example.moviemingle.services.clouds;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


@Service
public class CloudServiceImpl implements CloudService {
    
    @Autowired
    private Cloudinary cloudinary;
    
    @Value("${cloudinary.root-folder}")
    private String rootFolder;
    
    @SneakyThrows
    @Override
    public String upload(MultipartFile file, String name) {
        
        Map<?,?> options = ObjectUtils.asMap(
                "public_id", name,
                "folder", rootFolder,
                "faces", "true",
                "overwrite", "true");
        
        return cloudinary
                .uploader()
                .upload(file.getBytes(), options)
                .get("secure_url")
                .toString();
    }
    
    @SneakyThrows
    @Override
    public void delete(String name){
        cloudinary.uploader().destroy(name, ObjectUtils.emptyMap());
    }
}
