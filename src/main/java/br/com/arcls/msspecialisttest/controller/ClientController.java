package br.com.arcls.msspecialisttest.controller;

import br.com.arcls.msspecialisttest.domain.dto.ClientExpensiveBillResponse;
import br.com.arcls.msspecialisttest.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "client")
@RequiredArgsConstructor
@CacheConfig(cacheNames = "clientController")
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    @Cacheable
    public List<ClientExpensiveBillResponse> getExpensiveClientByCardFlag() {
        return clientService.getLastExpensiveBillsFromFlag();
    }

}
