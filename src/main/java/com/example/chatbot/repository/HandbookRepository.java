package com.example.chatbot.repository;

import com.example.chatbot.entities.Handbook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HandbookRepository extends JpaRepository<Handbook, Long> {
    List<Handbook> findBySectionContainingIgnoreCase(String keyword);
}
