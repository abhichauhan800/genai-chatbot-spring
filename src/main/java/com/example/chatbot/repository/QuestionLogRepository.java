package com.example.chatbot.repository;

import com.example.chatbot.entities.QuestionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionLogRepository extends JpaRepository<QuestionLog, Long> {
}
