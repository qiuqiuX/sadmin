package me.iqiuqiu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {     // TODO Deprecated

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedOrigins("http://localhost:8080")
                .allowedHeaders("X-Requested-With", "Content-Type", "Accept", "Origin", "Authorization", "X-Authorization");
        super.addCorsMappings(registry);
    }

}
