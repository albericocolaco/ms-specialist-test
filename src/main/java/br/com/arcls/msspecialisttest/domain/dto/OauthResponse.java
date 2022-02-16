package br.com.arcls.msspecialisttest.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class OauthResponse implements Serializable {
    private String token;
}
