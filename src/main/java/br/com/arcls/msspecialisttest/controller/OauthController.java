package br.com.arcls.msspecialisttest.controller;

import br.com.arcls.msspecialisttest.config.security.JwtTokenUtil;
import br.com.arcls.msspecialisttest.domain.dto.AuthRequest;
import br.com.arcls.msspecialisttest.domain.dto.CreateUserRequest;
import br.com.arcls.msspecialisttest.domain.dto.UserView;
import br.com.arcls.msspecialisttest.domain.mapper.UserViewMapper;
import br.com.arcls.msspecialisttest.domain.model.Role;
import br.com.arcls.msspecialisttest.domain.model.User;
import br.com.arcls.msspecialisttest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping(path = "login")
@RequiredArgsConstructor
public class OauthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserViewMapper userViewMapper;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserView> login(@RequestBody @Valid AuthRequest request) {
        try {
            Authentication authenticate = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    request.getUsername(), request.getPassword()
                            )
                    );

            User user = (User) authenticate.getPrincipal();

            return ResponseEntity.ok()
                    .header(
                            HttpHeaders.AUTHORIZATION,
                            jwtTokenUtil.generateAccessToken(user)
                    )
                    .body(userViewMapper.toUserView(user));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("register")
    @RolesAllowed(Role.USER_ADMIN)
    public UserView register(@RequestBody @Valid CreateUserRequest request) {
        return userService.create(request);
    }

}
