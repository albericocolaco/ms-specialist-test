package br.com.arcls.msspecialisttest.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class LogRequest {
    @NotBlank
    private String flag;
    @NotNull
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime transactionAt;
    @NotBlank
    private String client;
    @NotNull
    @Min(value = 0L, message = "The value must be positive")
    private BigDecimal value;
}
