package br.com.arcls.msspecialisttest.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class OauthRequest implements Serializable {
    private String username;
    private String password;
}
