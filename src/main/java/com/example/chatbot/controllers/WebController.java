package com.example.chatbot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
/**
 * Controller for serving the UI chatbot functionality.
 * Handles rendering the chatbot page and processing user questions.
 */
@Controller
public class WebController {

    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * Displays the main chatbot interface.
     */
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("question", "");
        model.addAttribute("response", "");
        return "chat";
    }
    /**
     * Processes the form submission and calls the ChatService to get an answer.
     */
    @PostMapping("/chat-ask")
    public String ask(@RequestParam("question") String question, Model model) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("question", question);

        String apiUrl = "http://localhost:8080/api/ask";
        String response = restTemplate.postForObject(apiUrl, requestBody, String.class);

        model.addAttribute("question", question);
        model.addAttribute("response", response);
        return "chat";
    }
}

