package br.com.arcls.msspecialisttest.controller;

import br.com.arcls.msspecialisttest.domain.dto.AuthRequest;
import br.com.arcls.msspecialisttest.domain.dto.LogRequest;
import br.com.arcls.msspecialisttest.domain.dto.UserView;
import br.com.arcls.msspecialisttest.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping(path = "log")
@RequiredArgsConstructor
public class LogController {

    final LogService logService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid LogRequest request) {
        logService.create(request);
    }
}