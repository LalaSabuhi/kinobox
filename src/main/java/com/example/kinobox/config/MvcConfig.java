package com.example.kinobox.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    private static final String UPLOAD_DIR = "images";
    private static final String UPLOAD_DIR_VIDEO = "videos";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        exposeDirectory(UPLOAD_DIR,registry);
        exposeDirectory(UPLOAD_DIR_VIDEO,registry);
    }

    private void exposeDirectory(String uploadDir, ResourceHandlerRegistry registry) {
        Path path = Paths.get(uploadDir);
        registry.addResourceHandler("/"+uploadDir+"/**").addResourceLocations("file:"+path.toAbsolutePath()+"/");
    }
}
