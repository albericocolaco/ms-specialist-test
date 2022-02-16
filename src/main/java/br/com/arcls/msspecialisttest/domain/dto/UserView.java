package br.com.arcls.msspecialisttest.domain.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserView {
    private String id;
    private String username;
    private String fullName;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
