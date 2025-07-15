package com.example.chatbot.services;

import com.example.chatbot.channel.HttpChannel;
import com.example.chatbot.entities.Handbook;
import com.example.chatbot.entities.QuestionLog;
import com.example.chatbot.repository.HandbookRepository;
import com.example.chatbot.repository.QuestionLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for serving  chatbot functionality.
 * Handles rendering the chatbot response and processing user questions.
 * Logging of questions asked
 */

@Service
public class ChatService implements ChatBotInterface{
    @Autowired
    private HandbookRepository handbookRepository;

    @Autowired
    private HttpChannel httpChannel;

    @Autowired
    private QuestionLogRepository logRepository;

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${open.ai.gpt.url}")
    private String openAiGptUrl;

    @Value("${open.ai.api.key}")
    private String openAiApiKey;

    @Value("${open.ai.api.version}")
    private String openAiApiVersion;

    @Value("${open.ai.model.deployment}")
    private String deployment;


    public String fetchAnswer(String question) {
        List<Handbook> matches = handbookRepository.findBySectionContainingIgnoreCase(question);
        String context = matches.stream()
                .map(Handbook::getContent)
                .collect(Collectors.joining("\n"));

        String answer = httpChannel.getAzureOpenAiResponse(question, context);
        logQuestionAnswer(question, answer);
        return answer;
    }

    private void logQuestionAnswer(String question, String answer) {
        QuestionLog log = new QuestionLog();
        log.setQuestion(question);
        log.setAnswer(answer);
        log.setTimestamp(LocalDateTime.now());
        logRepository.save(log);
    }

    public List<QuestionLog> getAllQuestionsAsked() {
        return logRepository.findAll();
    }

}