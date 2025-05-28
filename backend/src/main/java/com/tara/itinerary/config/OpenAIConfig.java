package com.tara.itinerary.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;

@Configuration
public class OpenAIConfig {
    @Value("${openai.api.key}")
    private String openAiApiKey;

    @Bean
    public OpenAIClient openAI() {
        return OpenAIOkHttpClient.builder()
                .apiKey(openAiApiKey)
                .build();
    }
}
