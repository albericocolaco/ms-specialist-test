package br.com.arcls.msspecialisttest.domain.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
public class CreateUserRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String fullName;
    @NotBlank
    private String password;
    @NotBlank
    private String rePassword;
    private Set<String> authorities;
}