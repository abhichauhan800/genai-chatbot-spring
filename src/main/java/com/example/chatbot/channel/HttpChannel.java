package com.example.chatbot.channel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class HttpChannel {
    @Value("${open.ai.gpt.url}")
    private String openAiGptUrl;

    @Value("${open.ai.api.key}")
    private String openAiApiKey;

    @Value("${open.ai.api.version}")
    private String openAiApiVersion;

    @Value("${open.ai.model.deployment}")
    private String deployment;

    private final RestTemplate restTemplate = new RestTemplate();

    public String getAzureOpenAiResponse(String question, String context) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(openAiGptUrl + "/openai/deployments/" + deployment + "/chat/completions")
                .queryParam("api-version", openAiApiVersion);

        URI url = builder.build(false).toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.add("api-key", openAiApiKey);

        Map<String, Object> request = new HashMap<>();
        request.put("temperature", 0.7);
        request.put("messages", List.of(Map.of("role", "user", "content", "Context:\n" + context + "\nQuestion: " + question)));

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);

        try {
            ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);
            List<Map<String, Object>> choices = (List<Map<String, Object>>) response.getBody().get("choices");
            Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
            return message.get("content").toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Error calling GPT API.";
    }
}