package com.example.chatbot.entities;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name = "HANDBOOK")
@Data
public class Handbook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String section;

    @Column(columnDefinition = "TEXT")
    private String content;
}