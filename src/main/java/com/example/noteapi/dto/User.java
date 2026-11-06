package com.example.noteapi.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private long userId;
    private String userName;
    private String password;
    private LocalDateTime createdAt;
}
