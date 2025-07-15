package com.example.chatbot.controllers;


import com.example.chatbot.entities.QuestionLog;
import com.example.chatbot.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
/**
 * Controller for serving the UI chatbot functionality.
 * Handles rendering the chatbot response and processing user questions.
 * Logging of questions asked
 */
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping("/ask")
    public ResponseEntity<String> askQuestion(@RequestBody Map<String, String> body) {
        String question = body.get("question");
        String response = chatService.fetchAnswer(question);
        return ResponseEntity.ok(response);
    }



    @GetMapping("/questions")
    public ResponseEntity<List<QuestionLog>> getAllQuestionsAsked() {
        return ResponseEntity.ok(chatService.getAllQuestionsAsked());
    }
}