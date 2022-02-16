package br.com.arcls.msspecialisttest.controller;

import br.com.arcls.msspecialisttest.domain.dto.AuthRequest;
import br.com.arcls.msspecialisttest.domain.dto.AuthResponse;
import br.com.arcls.msspecialisttest.domain.dto.CreateUserRequest;
import br.com.arcls.msspecialisttest.domain.dto.UserView;
import br.com.arcls.msspecialisttest.service.AuthService;
import br.com.arcls.msspecialisttest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping(path = "login")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthRequest request) {
        try {
            final String token = authService.generateToken(request.getUsername(), request.getPassword());
            final AuthResponse response = new AuthResponse();
            response.setToken(token);
            return ResponseEntity.ok().body(response);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("register")
    public UserView register(@RequestBody @Valid CreateUserRequest request) {
        return userService.create(request);
    }

}
