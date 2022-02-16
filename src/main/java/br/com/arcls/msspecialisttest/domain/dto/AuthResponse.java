package br.com.arcls.msspecialisttest.domain.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AuthResponse {
    private String token;
}
