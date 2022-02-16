package br.com.arcls.msspecialisttest.fixture;

import br.com.arcls.msspecialisttest.domain.dto.CardFlagEnum;
import br.com.arcls.msspecialisttest.domain.dto.LogRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class LogRequestFixture {

    public static LogRequest getLogRequest() {
        final LogRequest request = new LogRequest();
        request.setClient("client001");
        request.setFlag(CardFlagEnum.AMERICANEXPRESS.toString());
        request.setValue(BigDecimal.valueOf(100.99));
        request.setTransactionAt(LocalDateTime.now());
        return request;
    }
}
