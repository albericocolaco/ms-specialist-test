package br.com.arcls.msspecialisttest.service;

import br.com.arcls.msspecialisttest.config.security.JwtTokenUtil;
import br.com.arcls.msspecialisttest.domain.mapper.UserViewMapper;
import br.com.arcls.msspecialisttest.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserViewMapper userViewMapper;

    public String generateToken(String userName, String password) throws BadCredentialsException{
        Authentication authenticate = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                userName, password
                        )
                );

        User user = (User) authenticate.getPrincipal();

        return jwtTokenUtil.generateAccessToken(user);
    }
}
