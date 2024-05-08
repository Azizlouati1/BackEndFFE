package com.cni.elearning.config;

import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration(proxyBeanMethods = false)
public class GeminiConfiguration {
    @Bean
    public VertexAI vertexAI() throws IOException {
        return new VertexAI("dazzling-botany-422007-i2","europe-west9");
    }
    @Bean
    public GenerativeModel generativeModel(VertexAI vertexAI){
        return new GenerativeModel("gemini-1.0-pro-vision-001",vertexAI);
    }
}
