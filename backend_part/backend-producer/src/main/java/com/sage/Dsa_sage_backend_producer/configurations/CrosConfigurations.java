package com.sage.Dsa_sage_backend_producer.configurations;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CrosConfigurations implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry cors)
    {
        cors
                .addMapping("/**")
                .allowCredentials(true)
                .allowedMethods("GET","POST","PUT","DELETE")
                .maxAge(3600)
                .allowedOrigins("http://localhost:5173")
                .allowedHeaders("*");

    }
}
