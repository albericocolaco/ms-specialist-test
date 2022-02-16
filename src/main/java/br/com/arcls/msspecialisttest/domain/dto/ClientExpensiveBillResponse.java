package br.com.arcls.msspecialisttest.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
public class ClientExpensiveBillResponse {
    @JsonProperty("operadora")
    private String flag;
    @JsonProperty("cliente")
    private String client;
    @JsonProperty("valorTotal")
    private BigDecimal totalValue;
}
