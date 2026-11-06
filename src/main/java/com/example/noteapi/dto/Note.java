package com.example.noteapi.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Note {
    private String id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
}
