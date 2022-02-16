package br.com.arcls.msspecialisttest.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class JwtUser implements Serializable {
    private String id;
    private String fullName;
    private List<String> authorities;
}
